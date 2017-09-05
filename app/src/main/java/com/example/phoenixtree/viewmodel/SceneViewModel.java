package com.example.phoenixtree.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.Model.Keyframe;
import com.example.phoenixtree.Model.Scene;
import com.example.phoenixtree.repository.SceneRepository;
import com.example.phoenixtree.util.Fake;
import com.example.phoenixtree.util.PanelInterface;

import javax.inject.Inject;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneViewModel extends ViewModel implements PanelInterface {
    private long sceneId;
    private MediatorLiveData<Scene> scene = new MediatorLiveData<>();
    private final MutableLiveData<Keyframe> keyframe = new MediatorLiveData<>();
    private PanelInterface keyframeProcessor;
    private PanelInterface audioProcessor;
    private SceneRepository repository;

    @Inject
    public SceneViewModel(/* SceneRepository repository */) {
        // this.repository = repository;
    }
    public void load(long sceneId) {
        this.sceneId = sceneId;
        /*
        repository.getScene(sceneId).observe(this, sceneResource -> {

        });
        */
        keyframe.setValue(Fake.propagateKeyframe());
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
