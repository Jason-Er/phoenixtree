package com.example.phoenixtree.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.Model.Scene;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneViewModel extends ViewModel {
    private String sceneId;
    private LiveData<Scene> scene;

    public void init(String sceneId) {
        this.sceneId = sceneId;
    }
    public LiveData<Scene> getScene() {
        return scene;
    }
}
