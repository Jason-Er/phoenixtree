package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.entity.LineEntity;
import com.example.phoenixtree.dataservice.entity.StageLineEntity;
import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.dataservice.entity.StageRoleEntity;
import com.example.phoenixtree.dataservice.entity.StageSceneEntity;
import com.example.phoenixtree.dataservice.entity.UserEntity;
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
import com.example.phoenixtree.util.CallBackInterface;

import java.util.ArrayList;
import java.util.Iterator;
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
    private MediatorLiveData<List<StagePlayEntity>> stagePlayInfoListLiveData;

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

            private void traverseScenelist(final Iterator iterator, final MediatorLiveData<StagePlay> stagePlayLiveData, final CallBackInterface callback) {
                if(iterator.hasNext()) {
                    final StageScene stageScene = (StageScene) iterator.next();
                    final LiveData<List<StageLineEntity>> linelistLiveData = lineEntityDao.retrieveAllByStageSceneIdLive(stageScene.id);
                    stagePlayLiveData.addSource(linelistLiveData, new Observer<List<StageLineEntity>>() {
                        @Override
                        public void onChanged(@android.support.annotation.Nullable List<StageLineEntity> lineEntities) {
                            stagePlayLiveData.removeSource(linelistLiveData);
                            stageScene.stageLines = lineEntities;
                            traverseScenelist(iterator, stagePlayLiveData, callback);
                        }
                    });
                } else {
                    callback.callback();
                }
            }

            @NonNull
            @Override
            protected LiveData<StagePlay> loadFromDb() {
                Log.i(TAG, "loadFromDb");
                if(stagePlayLiveData == null) {
                    stagePlayLiveData = new MediatorLiveData<>();
                }

                final StagePlay stagePlay = new StagePlay();
                final LiveData<StagePlayEntity> playEntityLiveData = playEntityDao.retrieveByIdLive(playId);
                stagePlayLiveData.addSource(playEntityLiveData, new Observer<StagePlayEntity>() {
                    @Override
                    public void onChanged(@android.support.annotation.Nullable StagePlayEntity playEntity) {
                        stagePlayLiveData.removeSource(playEntityLiveData);
                        if(playEntity != null) {
                            stagePlay.id = playEntity.id;
                            stagePlay.name = playEntity.name;
                            stagePlay.directorId = playEntity.directorId;
                            final LiveData<UserEntity> userEntityLiveData = userEntityDao.retrieveByIdLive(stagePlay.directorId);
                            stagePlayLiveData.addSource(userEntityLiveData, new Observer<UserEntity>() {
                                @Override
                                public void onChanged(@android.support.annotation.Nullable UserEntity userEntity) {
                                    stagePlayLiveData.removeSource(userEntityLiveData);
                                    stagePlay.director = userEntity;
                                    final LiveData<List<StageRoleEntity>> roleListLiveData = roleEntityDao.retrieveAllByStagePlayIdLive(playId);
                                    stagePlayLiveData.addSource(roleListLiveData, new Observer<List<StageRoleEntity>>() {
                                        @Override
                                        public void onChanged(@android.support.annotation.Nullable List<StageRoleEntity> roleEntities) {
                                            stagePlayLiveData.removeSource(roleListLiveData);
                                            stagePlay.cast = roleEntities;
                                            final LiveData<List<StageSceneEntity>> scenelistLiveData = sceneEntityDao.retrieveAllByStagePlayIdLive(playId);
                                            stagePlayLiveData.addSource(scenelistLiveData, new Observer<List<StageSceneEntity>>() {
                                                @Override
                                                public void onChanged(@android.support.annotation.Nullable List<StageSceneEntity> sceneEntities) {
                                                    stagePlayLiveData.removeSource(scenelistLiveData);
                                                    List<StageScene> stageSceneList = new ArrayList<>();
                                                    for (StageSceneEntity sceneEntity : sceneEntities) {
                                                        StageScene stageScene = new StageScene();
                                                        // properties copy
                                                        stageScene.id = sceneEntity.id;
                                                        stageScene.stagePlayId = sceneEntity.stagePlayId;
                                                        stageScene.setting = sceneEntity.setting;
                                                        stageScene.actOrdinal = sceneEntity.actOrdinal;
                                                        stageScene.atrise = sceneEntity.atrise;
                                                        stageScene.ordinal = sceneEntity.ordinal;
                                                        stageSceneList.add(stageScene);
                                                    }
                                                    stagePlay.scenes = stageSceneList;

                                                    Iterator iterator = stageSceneList.iterator();
                                                    traverseScenelist(iterator, stagePlayLiveData, new CallBackInterface() {
                                                        @Override
                                                        public void callback() {
                                                            stagePlayLiveData.setValue(stagePlay);
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        } else {
                            stagePlayLiveData.setValue(null);
                        }
                    }
                });
                return stagePlayLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<StagePlay>> createCall() {
                Log.i(TAG, "createCall");
                return webservice.loadStagePlay(playId);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<StagePlayEntity>>> loadStagePlayInfo(final long page, final long size) {
        return new NetworkBoundResource<List<StagePlayEntity>, List<StagePlayEntity>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<StagePlayEntity> item) {
                // TODO: 9/22/2017 need batch saving
                for(StagePlayEntity stagePlayEntity: item) {
                    playEntityDao.save(stagePlayEntity);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<StagePlayEntity> data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<List<StagePlayEntity>> loadFromDb() {
                Log.i(TAG, "loadFromDb");
                // TODO: 10/23/2017 load page from local
                if(stagePlayInfoListLiveData == null) {
                    stagePlayInfoListLiveData = new MediatorLiveData<>();
                }
                stagePlayInfoListLiveData.setValue(null);
                return stagePlayInfoListLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<StagePlayEntity>>> createCall() {
                Log.i(TAG, "createCall");
                return webservice.loadStagePlayInfo(page, size);
            }
        }.asLiveData();
    }

}
