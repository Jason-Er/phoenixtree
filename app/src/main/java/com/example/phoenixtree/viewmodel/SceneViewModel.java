package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.util.AbsentLiveData;
import com.example.phoenixtree.util.Fake;
import com.example.phoenixtree.util.processor.Keyframe;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.Scene4PW;
import com.example.phoenixtree.repository.SceneRepository;
import com.example.phoenixtree.util.processor.AudioProcessor;
import com.example.phoenixtree.util.processor.KeyframeProcessor;
import com.example.phoenixtree.util.PanelInterface;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneViewModel extends ViewModel implements PanelInterface {

    private final String TAG = SceneViewModel.class.getName();

    private final MutableLiveData<Long> sceneId = new MutableLiveData<>();
    public final LiveData<Keyframe> keyframe;

    private final KeyframeProcessor keyframeProcessor;
    private final AudioProcessor audioProcessor;

    private LiveData<Resource<Scene4PW>> sceneLiveData;
    @Inject
    public SceneViewModel(final SceneRepository repository, KeyframeProcessor keyframeProcessor, AudioProcessor audioProcessor) {
        this.keyframeProcessor = keyframeProcessor;
        this.audioProcessor = audioProcessor;
        keyframe = Transformations.switchMap(sceneId, new Function<Long, LiveData<Keyframe>>() {
            @Override
            public LiveData<Keyframe> apply(Long input) {
                if(input == null) {
                    return AbsentLiveData.create();
                } else {
                    sceneLiveData = repository.loadScene(input);
                    return Transformations.switchMap(sceneLiveData, new Function<Resource<Scene4PW>, LiveData<Keyframe>>() {
                        @Override
                        public LiveData<Keyframe> apply(Resource<Scene4PW> scene4PWResource) {
                            MutableLiveData<Keyframe> keyframeLiveData = new MutableLiveData<Keyframe>();
                            Scene4PW scene4PW = scene4PWResource.data;
                            return keyframeLiveData;
                        }
                    });
                }
            }
        });
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

    public void setSceneId(long sceneId) {
        if (Objects.equals(this.sceneId.getValue(), sceneId)) {
            return;
        }
        this.sceneId.setValue(sceneId);
    }
}
