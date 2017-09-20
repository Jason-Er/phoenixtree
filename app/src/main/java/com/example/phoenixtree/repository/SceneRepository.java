package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.phoenixtree.dataservice.entity.SceneEntity;
import com.example.phoenixtree.dataservice.local.SceneEntityDao;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.Scene;
import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.remote.WebService;
import com.example.phoenixtree.util.Fake;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 9/1/2017.
 */
@Singleton
public class SceneRepository {
    private final String TAG = SceneRepository.class.getName();
    private final WebService webservice;
    private final AppExecutors appExecutors;
    private final SceneEntityDao sceneEntityDao;

    final MutableLiveData<Resource<Scene>> liveData = new MutableLiveData<>();

    @Inject
    public SceneRepository(WebService webservice, SceneEntityDao sceneEntityDao, AppExecutors appExecutors) {
        this.webservice = webservice;
        this.appExecutors = appExecutors;
        this.sceneEntityDao = sceneEntityDao;
    }

    public LiveData<Resource<Scene>> loadScene(long sceneId) {
        liveData.setValue(Resource.success(Fake.propagateScene()));
        Log.i(TAG, "getScene()");
        return liveData;
    }

    /*
    public void testScene() {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                SceneEntity sceneEntity = new SceneEntity();
                sceneEntity.id = 1;
                sceneEntity.playId = 1;
                sceneEntityDao.save(sceneEntity);
                Log.i(TAG, "testScene()");
            }
        });
    }
    */
}
