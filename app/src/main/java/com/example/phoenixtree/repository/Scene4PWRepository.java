package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phoenixtree.dataservice.entity.LineEntity;
import com.example.phoenixtree.dataservice.entity.SceneEntity;
import com.example.phoenixtree.dataservice.local.LineEntityDao;
import com.example.phoenixtree.dataservice.local.SceneEntityDao;
import com.example.phoenixtree.dataservice.remote.ApiResponse;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.Scene4PW;
import com.example.phoenixtree.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 9/1/2017.
 */
@Singleton
public class Scene4PWRepository {
    private final String TAG = Scene4PWRepository.class.getName();
    private final AppExecutors appExecutors;
    private final SceneEntityDao sceneEntityDao;
    private final LineEntityDao lineEntityDao;

    private MediatorLiveData<Scene4PW> scene4PWLiveData;

    @Inject
    public Scene4PWRepository(SceneEntityDao sceneEntityDao,
                              LineEntityDao lineEntityDao,
                              AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.sceneEntityDao = sceneEntityDao;
        this.lineEntityDao = lineEntityDao;
    }

    public LiveData<Resource<Scene4PW>> loadScene(final long sceneId) {
        return new NetworkBoundResource<Scene4PW, Scene4PW>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull Scene4PW item) {

            }

            @Override
            protected boolean shouldFetch(@javax.annotation.Nullable Scene4PW data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<Scene4PW> loadFromDb() {
                Log.i(TAG, "loadFromDb");
                if(scene4PWLiveData == null) {
                    scene4PWLiveData = new MediatorLiveData<>();
                }
                final Scene4PW scene4PW = new Scene4PW();
                final LiveData<SceneEntity> sceneEntityLiveData = sceneEntityDao.retrieveByIdLive(sceneId);
                scene4PWLiveData.addSource(sceneEntityLiveData, new Observer<SceneEntity>() {
                    @Override
                    public void onChanged(@Nullable SceneEntity sceneEntity) {
                        scene4PWLiveData.removeSource(sceneEntityLiveData);
                        if(sceneEntity != null) {
                            // properties copy
                            scene4PW.id = sceneEntity.id;
                            scene4PW.playId = sceneEntity.playId;
                            scene4PW.setting = sceneEntity.setting;
                            scene4PW.actOrdinal = sceneEntity.actOrdinal;
                            scene4PW.atrise = sceneEntity.atrise;
                            scene4PW.ordinal = sceneEntity.ordinal;

                            final LiveData<List<LineEntity>> lineEntityLiveData = lineEntityDao.retrieveAllBySceneIdLive(sceneId);
                            scene4PWLiveData.addSource(lineEntityLiveData, new Observer<List<LineEntity>>() {
                                @Override
                                public void onChanged(@Nullable List<LineEntity> lineEntities) {
                                    scene4PWLiveData.removeSource(lineEntityLiveData);
                                    scene4PW.lines = lineEntities;
                                    scene4PWLiveData.setValue(scene4PW);
                                }
                            });
                        } else {
                            scene4PWLiveData.setValue(null);
                        }
                    }
                });
                return scene4PWLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Scene4PW>> createCall() {
                return null;
            }
        }.asLiveData();
    }

}
