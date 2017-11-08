package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.phoenixtree.model.StageScene;
import com.example.phoenixtree.repository.StageSceneRepository;
import com.example.phoenixtree.util.AbsentLiveData;
import com.example.phoenixtree.model.keyframe.Keyframe;
import com.example.phoenixtree.model.Resource;
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

    private final KeyframeProcessor keyframeP;
    private final AudioProcessor audioP;

    private LiveData<StageScene> sceneLiveData;
    @Inject
    public SceneViewModel(final StageSceneRepository repository,
                          KeyframeProcessor keyframeProcessor,
                          AudioProcessor audioProcessor) {
        keyframeP = keyframeProcessor;
        audioP = audioProcessor;
        keyframe = Transformations.switchMap(sceneId, new Function<Long, LiveData<Keyframe>>() {
            @Override
            public LiveData<Keyframe> apply(Long input) {
                if(input == null) {
                    return AbsentLiveData.create();
                } else {
                    sceneLiveData = repository.loadScene(input);
                    return Transformations.switchMap(sceneLiveData, new Function<StageScene, LiveData<Keyframe>>() {
                        @Override
                        public LiveData<Keyframe> apply(StageScene scene) {
                            keyframeP.setScene(scene);
                            return keyframeP.firstFrame();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void play() {
        keyframeP.play();
        audioP.play();
    }

    @Override
    public void pause() {
        keyframeP.pause();
        audioP.pause();
    }

    @Override
    public void resume() {
        keyframeP.resume();
        audioP.resume();
    }

    @Override
    public void stop() {
        keyframeP.stop();
        audioP.stop();
    }

    public void setSceneId(long sceneId) {
        if (Objects.equals(this.sceneId.getValue(), sceneId)) {
            return;
        }
        this.sceneId.setValue(sceneId);
    }
}
