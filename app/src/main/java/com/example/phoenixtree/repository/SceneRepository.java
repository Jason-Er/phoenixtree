package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.phoenixtree.Model.Resource;
import com.example.phoenixtree.Model.Scene;
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

    final MutableLiveData<Resource<Scene>> liveData = new MutableLiveData<>();

    @Inject
    public SceneRepository(WebService webservice, AppExecutors appExecutors) {
        this.webservice = webservice;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<Scene>> loadScene(long sceneId) {
        liveData.setValue(Resource.success(Fake.propagateScene()));
        Log.i(TAG, "getScene()");
        return liveData;
    }
}
