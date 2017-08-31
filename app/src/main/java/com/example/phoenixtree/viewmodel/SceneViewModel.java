package com.example.phoenixtree.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.Model.Keyframe;
import com.example.phoenixtree.Model.Scene;
import com.example.phoenixtree.util.KeyframeProcessor;
import com.example.phoenixtree.util.PanelInterface;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneViewModel extends ViewModel implements PanelInterface {
    private String sceneId;
    private MediatorLiveData<Scene> scene;
    private MediatorLiveData<Keyframe> keyframe;
    private PanelInterface keyframeProcessor;
    private PanelInterface audioProcessor;

    public SceneViewModel() {
        scene = new MediatorLiveData<>();
        keyframe = new MediatorLiveData<>();
    }
    public LiveData<Scene> load(String sceneId) {
        this.sceneId = sceneId;
        //scene.addSource();
        return scene;
    }
    public LiveData<Keyframe> getKeyframe() {
        return keyframe;
    }

    @Override
    public void play() {
        keyframeProcessor.play();
        audioProcessor.play();
    }

    @Override
    public void pause() {
        keyframeProcessor.pause();
        audioProcessor.pause();
    }

    @Override
    public void resume() {
        keyframeProcessor.resume();
        audioProcessor.resume();
    }

    @Override
    public void stop() {
        keyframeProcessor.stop();
        audioProcessor.stop();
    }
}
