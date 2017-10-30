package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.entity.StageLineEntity;
import com.example.phoenixtree.dataservice.entity.StageSceneEntity;
import com.example.phoenixtree.dataservice.local.StageLineEntityDao;
import com.example.phoenixtree.dataservice.local.StageSceneEntityDao;
import com.example.phoenixtree.dataservice.remote.ApiResponse;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.StageScene;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 10/30/2017.
 */
@Singleton
public class StageSceneRepository {

    private final String TAG = StageSceneRepository.class.getName();
    private final AppExecutors appExecutors;
    private final StageSceneEntityDao sceneEntityDao;
    private final StageLineEntityDao lineEntityDao;

    private MediatorLiveData<StageScene> sceneMediatorLiveData;

    @Inject
    public StageSceneRepository(StageSceneEntityDao sceneEntityDao,
                                StageLineEntityDao lineEntityDao,
                                AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.sceneEntityDao = sceneEntityDao;
        this.lineEntityDao = lineEntityDao;
    }

    public LiveData<Resource<StageScene>> loadScene(final long sceneId) {
        return new NetworkBoundResource<StageScene, StageScene>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull StageScene item) {

            }

            @Override
            protected boolean shouldFetch(@javax.annotation.Nullable StageScene data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<StageScene> loadFromDb() {
                Log.i(TAG, "loadFromDb");
                if(sceneMediatorLiveData == null) {
                    sceneMediatorLiveData = new MediatorLiveData<>();
                }
                final StageScene scene = new StageScene();
                final LiveData<StageSceneEntity> sceneEntityLiveData = sceneEntityDao.retrieveByIdLive(sceneId);
                sceneMediatorLiveData.addSource(sceneEntityLiveData, new Observer<StageSceneEntity>() {
                    @Override
                    public void onChanged(@Nullable StageSceneEntity sceneEntity) {
                        sceneMediatorLiveData.removeSource(sceneEntityLiveData);
                        if(sceneEntity != null) {
                            // properties copy
                            scene.id = sceneEntity.id;
                            scene.stagePlayId = sceneEntity.stagePlayId;
                            scene.setting = sceneEntity.setting;
                            scene.actOrdinal = sceneEntity.actOrdinal;
                            scene.atrise = sceneEntity.atrise;
                            scene.ordinal = sceneEntity.ordinal;

                            final LiveData<List<StageLineEntity>> lineEntityLiveData = lineEntityDao.retrieveAllByStageSceneIdLive(sceneId);
                            sceneMediatorLiveData.addSource(lineEntityLiveData, new Observer<List<StageLineEntity>>() {
                                @Override
                                public void onChanged(@Nullable List<StageLineEntity> lineEntities) {
                                    sceneMediatorLiveData.removeSource(lineEntityLiveData);
                                    scene.stageLines = lineEntities;
                                    sceneMediatorLiveData.setValue(scene);
                                }
                            });
                        } else {
                            sceneMediatorLiveData.setValue(null);
                        }
                    }
                });
                return sceneMediatorLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<StageScene>> createCall() {
                return null;
            }
        }.asLiveData();
    }
}
