package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.entity.StageLineEntity;
import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.dataservice.entity.StageRoleEntity;
import com.example.phoenixtree.dataservice.entity.StageSceneEntity;
import com.example.phoenixtree.dataservice.local.StageEntityDao;
import com.example.phoenixtree.dataservice.local.StageLineEntityDao;
import com.example.phoenixtree.dataservice.local.StagePlayEntityDao;
import com.example.phoenixtree.dataservice.local.StageRoleEntityDao;
import com.example.phoenixtree.dataservice.local.StageSceneEntityDao;
import com.example.phoenixtree.dataservice.local.UserEntityDao;
import com.example.phoenixtree.dataservice.remote.ApiResponse;
import com.example.phoenixtree.dataservice.remote.WebService;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.StagePlay;
import com.example.phoenixtree.model.StageScene;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 10/16/2017.
 */
@Singleton
public class StagePlayRepository {
    private final String TAG = StagePlayRepository.class.getName();
    private final WebService webservice;
    private final AppExecutors appExecutors;
    private final StagePlayEntityDao playEntityDao;
    private final StageSceneEntityDao sceneEntityDao;
    private final StageLineEntityDao lineEntityDao;
    private final StageRoleEntityDao roleEntityDao;
    private final StageEntityDao stageEntityDao;
    private final UserEntityDao userEntityDao;

    private MediatorLiveData<StagePlay> stagePlayLiveData;

    @Inject
    public StagePlayRepository(WebService webservice,
                             StageRoleEntityDao roleEntityDao,
                             UserEntityDao userEntityDao,
                             StagePlayEntityDao playEntityDao,
                             StageSceneEntityDao sceneEntityDao,
                             StageLineEntityDao lineEntityDao,
                             StageEntityDao stageEntityDao,
                             AppExecutors appExecutors) {
        this.webservice = webservice;
        this.appExecutors = appExecutors;
        this.stageEntityDao = stageEntityDao;
        this.roleEntityDao = roleEntityDao;
        this.userEntityDao = userEntityDao;
        this.playEntityDao = playEntityDao;
        this.sceneEntityDao = sceneEntityDao;
        this.lineEntityDao = lineEntityDao;
    }

    public LiveData<Resource<StagePlay>> loadStagePlay(final long playId) {
        return new NetworkBoundResource<StagePlay, StagePlay>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull StagePlay item) {
                userEntityDao.save(item.director);
                StagePlayEntity stagePlayEntity = (StagePlayEntity) item;
                playEntityDao.save(stagePlayEntity);
                // TODO: 9/22/2017 need batch saving
                for(StageRoleEntity role: item.cast) {
                    roleEntityDao.save(role);
                }
                // TODO: 9/22/2017 need batch saving
                for(StageScene scene: item.scenes) {
                    StageSceneEntity sceneEntity = (StageSceneEntity) scene;
                    sceneEntityDao.save(sceneEntity);
                    // TODO: 9/22/2017 need batch saving
                    for(StageLineEntity line: scene.stageLines) {
                        lineEntityDao.save(line);
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable StagePlay data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<StagePlay> loadFromDb() {
                Log.i(TAG, "loadFromDb");
                if(stagePlayLiveData == null) {
                    stagePlayLiveData = new MediatorLiveData<>();
                }
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<StagePlay>> createCall() {
                return null;
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<StagePlayEntity>>> loadStagePlayInfo(final long pageNo) {
        return new NetworkBoundResource<List<StagePlayEntity>, List<StagePlayEntity>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<StagePlayEntity> item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<StagePlayEntity> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<StagePlayEntity>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<StagePlayEntity>>> createCall() {
                return null;
            }
        }.asLiveData();
    }
}
