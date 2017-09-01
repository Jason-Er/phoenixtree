package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.phoenixtree.Model.Resource;
import com.example.phoenixtree.Model.Scene;
import com.example.phoenixtree.dataservice.remote.Webservice;
import com.example.phoenixtree.util.Fake;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 9/1/2017.
 */
@Singleton
public class SceneRepository {
    private final Webservice webservice;
    private final Executor executor;

    final MutableLiveData<Resource<Scene>> liveData = new MutableLiveData<>();

    @Inject
    public SceneRepository(Webservice webservice, Executor executor) {
        this.webservice = webservice;
        this.executor = executor;

    }

    public LiveData<Resource<Scene>> getScene(long sceneId) {
        liveData.setValue(Resource.success(Fake.propagateScene()));
        return liveData;
    }
}
