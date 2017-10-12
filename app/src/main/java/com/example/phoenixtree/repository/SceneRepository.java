package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phoenixtree.dataservice.entity.LineEntity;
import com.example.phoenixtree.dataservice.entity.SceneEntity;
import com.example.phoenixtree.dataservice.local.LineEntityDao;
import com.example.phoenixtree.dataservice.local.SceneEntityDao;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.Scene4PW;
import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.util.Fake;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 9/1/2017.
 */
@Singleton
public class SceneRepository {
    private final String TAG = SceneRepository.class.getName();
    private final AppExecutors appExecutors;
    private final SceneEntityDao sceneEntityDao;
    private final LineEntityDao lineEntityDao;

    @Inject
    public SceneRepository(SceneEntityDao sceneEntityDao,
                           LineEntityDao lineEntityDao,
                           AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.sceneEntityDao = sceneEntityDao;
        this.lineEntityDao = lineEntityDao;


    }

    public LiveData<Resource<Scene4PW>> loadScene(long sceneId) {
        final MediatorLiveData<Resource<Scene4PW>> liveData = new MediatorLiveData<>();
        /*
        final LiveData<SceneEntity> sceneEntityLiveData = sceneEntityDao.retrieveByIdLive(sceneId);
        liveData.addSource(sceneEntityLiveData, new Observer<SceneEntity>() {
            @Override
            public void onChanged(@Nullable SceneEntity sceneEntity) {
                liveData.removeSource(sceneEntityLiveData);
                final LiveData<List<LineEntity>> listLiveData = lineEntityDao.retrieveAllBySceneIdLive(sceneEntity.id);
                liveData.addSource(listLiveData, new Observer<List<LineEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<LineEntity> lineEntities) {

                    }
                })

            }
        });
        */
        liveData.setValue(Resource.success(Fake.propagateScene()));
        Log.i(TAG, "getScene()");
        return liveData;
    }

}
