package com.example.phoenixtree.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phoenixtree.util.processor.Keyframe;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.Scene;
import com.example.phoenixtree.repository.SceneRepository;
import com.example.phoenixtree.util.processor.AudioProcessor;
import com.example.phoenixtree.util.processor.KeyframeProcessor;
import com.example.phoenixtree.util.PanelInterface;

import javax.inject.Inject;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneViewModel extends ViewModel implements PanelInterface {

    private final String TAG = SceneViewModel.class.getName();

    private final MediatorLiveData<Resource<Keyframe>> keyframe = new MediatorLiveData<>();
    private KeyframeProcessor keyframeProcessor = new KeyframeProcessor();
    private AudioProcessor audioProcessor = new AudioProcessor();
    private SceneRepository repository;

    @Inject
    public SceneViewModel(SceneRepository repository) {
        this.repository = repository;
    }
    public void getScene(long sceneId, final LifecycleOwner owner) {
        repository.loadScene(sceneId).observe(owner, new Observer<Resource<Scene>>() {
            @Override
            public void onChanged(@Nullable Resource<Scene> sceneResource) {
                switch (sceneResource.status) {
                    case SUCCESS:
                        keyframeProcessor.init(sceneResource.data, keyframe);
                        break;
                    case ERROR:
                        keyframe.setValue(Resource.error(sceneResource.message , new Keyframe()));
                        break;
                    case LOADING:
                        keyframe.setValue(Resource.loading(new Keyframe()));
                        break;
                }
                Log.i(TAG, "getScene()");
            }
        });
    }
    public LiveData<Resource<Keyframe>> getKeyframe() {
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
