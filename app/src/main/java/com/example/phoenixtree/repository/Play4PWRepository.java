package com.example.phoenixtree.repository;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
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
import com.example.phoenixtree.util.AbsentLiveData;

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

            @NonNull
            @Override
            protected LiveData<Play4PW> loadFromDb() {
                /*
                Log.i(TAG, "loadFromDb");
                final MediatorLiveData<Resource<Play4PW>> liveData = new MediatorLiveData<>();
                LiveData<PlayEntity> playEntity = playEntityDao.retrieveByIdLive(playId);
                Transformations.map()

                LiveData<SceneEntity> sceneEntity = Transformations.switchMap(playEntity, new Function<PlayEntity, LiveData<SceneEntity>>() {
                    @Override
                    public LiveData<SceneEntity> apply(PlayEntity input) {
                        return null;
                    }
                });
                */
                // LiveData<Play4PW> play4PWLiveData = new MutableLiveData<Play4PW>();
                return AbsentLiveData.create();
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
