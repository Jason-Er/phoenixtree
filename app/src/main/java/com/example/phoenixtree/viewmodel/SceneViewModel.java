package com.example.phoenixtree.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.Model.Scene;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneViewModel extends ViewModel {
    private String sceneId;
    private MediatorLiveData<Scene> scene;

    public SceneViewModel() {
        scene = new MediatorLiveData<>();
    }
    public LiveData<Scene> load(String sceneId) {
        this.sceneId = sceneId;
        //scene.addSource();
        return scene;
    }
    public LiveData<Scene> getScene() {
        return scene;
    }
}
