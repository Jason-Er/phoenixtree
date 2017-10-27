package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.entity.LineEntity;
import com.example.phoenixtree.dataservice.entity.PlayEntity;
import com.example.phoenixtree.dataservice.entity.RoleEntity;
import com.example.phoenixtree.dataservice.entity.SceneEntity;
import com.example.phoenixtree.dataservice.entity.UserEntity;
import com.example.phoenixtree.dataservice.local.LineEntityDao;
import com.example.phoenixtree.dataservice.local.PlayEntityDao;
import com.example.phoenixtree.dataservice.local.RoleEntityDao;
import com.example.phoenixtree.dataservice.local.SceneEntityDao;
import com.example.phoenixtree.dataservice.local.UserEntityDao;
import com.example.phoenixtree.dataservice.remote.ApiResponse;
import com.example.phoenixtree.dataservice.remote.WebService;
import com.example.phoenixtree.model.Play4PW;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.Scene4PW;
import com.example.phoenixtree.util.callbackInterface.PlainCallBack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 9/21/2017.
 */
@Singleton
public class Play4PWRepository {
    private final String TAG = Play4PWRepository.class.getName();
    private final WebService webservice;
    private final AppExecutors appExecutors;
    private final PlayEntityDao playEntityDao;
    private final SceneEntityDao sceneEntityDao;
    private final LineEntityDao lineEntityDao;
    private final RoleEntityDao roleEntityDao;
    private final UserEntityDao userEntityDao;
    private MediatorLiveData<Play4PW> play4PWLiveData;

    @Inject
    public Play4PWRepository(WebService webservice,
                             RoleEntityDao roleEntityDao,
                             UserEntityDao userEntityDao,
                             PlayEntityDao playEntityDao,
                             SceneEntityDao sceneEntityDao,
                             LineEntityDao lineEntityDao,
                             AppExecutors appExecutors) {
        this.webservice = webservice;
        this.appExecutors = appExecutors;
        this.roleEntityDao = roleEntityDao;
        this.userEntityDao = userEntityDao;
        this.playEntityDao = playEntityDao;
        this.sceneEntityDao = sceneEntityDao;
        this.lineEntityDao = lineEntityDao;
    }

    public LiveData<Resource<Play4PW>> loadPlay(final long playId) {
        return new NetworkBoundResource<Play4PW, Play4PW>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Play4PW item) {
                Log.i(TAG, "saveCallResult");
                userEntityDao.save(item.playwright);
                PlayEntity playEntity = (PlayEntity) item;
                playEntityDao.save(playEntity);
                // TODO: 9/22/2017 need batch saving
                for(RoleEntity role: item.cast) {
                    roleEntityDao.save(role);
                }
                // TODO: 9/22/2017 need batch saving
                for(Scene4PW scene: item.scenes) {
                    SceneEntity sceneEntity = (SceneEntity) scene;
                    sceneEntityDao.save(sceneEntity);
                    // TODO: 9/22/2017 need batch saving
                    for(LineEntity line: scene.lines) {
                        lineEntityDao.save(line);
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Play4PW data) {
                Log.i(TAG, "shouldFetch");
                return data == null;
            }

            private void traverseScenelist(final Iterator iterator, final MediatorLiveData<Play4PW> play4PWLiveData, final PlainCallBack callback) {
                if(iterator.hasNext()) {
                    final Scene4PW scene4PW = (Scene4PW) iterator.next();
                    final LiveData<List<LineEntity>> linelistLiveData = lineEntityDao.retrieveAllBySceneIdLive(scene4PW.id);
                    play4PWLiveData.addSource(linelistLiveData, new Observer<List<LineEntity>>() {
                        @Override
                        public void onChanged(@android.support.annotation.Nullable List<LineEntity> lineEntities) {
                            play4PWLiveData.removeSource(linelistLiveData);
                            scene4PW.lines = lineEntities;
                            traverseScenelist(iterator, play4PWLiveData, callback);
                        }
                    });
                } else {
                    callback.callback();
                }
            }

            @NonNull
            @Override
            protected LiveData<Play4PW> loadFromDb() {

                Log.i(TAG, "loadFromDb");
                if(play4PWLiveData == null) {
                    play4PWLiveData = new MediatorLiveData<>();
                }

                final Play4PW play4PW = new Play4PW();
                final LiveData<PlayEntity> playEntityLiveData = playEntityDao.retrieveByIdLive(playId);
                play4PWLiveData.addSource(playEntityLiveData, new Observer<PlayEntity>() {
                    @Override
                    public void onChanged(@android.support.annotation.Nullable PlayEntity playEntity) {
                        play4PWLiveData.removeSource(playEntityLiveData);
                        if(playEntity != null) {
                            play4PW.id = playEntity.id;
                            play4PW.name = playEntity.name;
                            play4PW.playwrightId = playEntity.playwrightId;
                            final LiveData<UserEntity> userEntityLiveData = userEntityDao.retrieveByIdLive(play4PW.playwrightId);
                            play4PWLiveData.addSource(userEntityLiveData, new Observer<UserEntity>() {
                                @Override
                                public void onChanged(@android.support.annotation.Nullable UserEntity userEntity) {
                                    play4PWLiveData.removeSource(userEntityLiveData);
                                    play4PW.playwright = userEntity;
                                    final LiveData<List<RoleEntity>> roleListLiveData = roleEntityDao.retrieveAllByPlayIdLive(playId);
                                    play4PWLiveData.addSource(roleListLiveData, new Observer<List<RoleEntity>>() {
                                        @Override
                                        public void onChanged(@android.support.annotation.Nullable List<RoleEntity> roleEntities) {
                                            play4PWLiveData.removeSource(roleListLiveData);
                                            play4PW.cast = roleEntities;
                                            final LiveData<List<SceneEntity>> scenelistLiveData = sceneEntityDao.retrieveAllByPlayIdLive(playId);
                                            play4PWLiveData.addSource(scenelistLiveData, new Observer<List<SceneEntity>>() {
                                                @Override
                                                public void onChanged(@android.support.annotation.Nullable List<SceneEntity> sceneEntities) {
                                                    play4PWLiveData.removeSource(scenelistLiveData);
                                                    List<Scene4PW> scene4PWList = new ArrayList<>();
                                                    for (SceneEntity sceneEntity : sceneEntities) {
                                                        Scene4PW scene4PW = new Scene4PW();
                                                        // properties copy
                                                        scene4PW.id = sceneEntity.id;
                                                        scene4PW.playId = sceneEntity.playId;
                                                        scene4PW.setting = sceneEntity.setting;
                                                        scene4PW.actOrdinal = sceneEntity.actOrdinal;
                                                        scene4PW.atrise = sceneEntity.atrise;
                                                        scene4PW.ordinal = sceneEntity.ordinal;
                                                        scene4PWList.add(scene4PW);
                                                    }
                                                    play4PW.scenes = scene4PWList;

                                                    Iterator iterator = scene4PWList.iterator();
                                                    traverseScenelist(iterator, play4PWLiveData, new PlainCallBack() {
                                                        @Override
                                                        public void callback() {
                                                            play4PWLiveData.setValue(play4PW);
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        } else {
                            play4PWLiveData.setValue(null);
                        }
                    }
                });

                return play4PWLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Play4PW>> createCall() {
                Log.i(TAG, "createCall");
                return webservice.loadPlay(playId);
            }
        }.asLiveData();
    }
}