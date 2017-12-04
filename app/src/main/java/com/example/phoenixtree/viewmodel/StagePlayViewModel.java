package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.StagePlay;
import com.example.phoenixtree.model.StageScene;
import com.example.phoenixtree.model.Status;
import com.example.phoenixtree.model.keyframe.Keyframe;
import com.example.phoenixtree.repository.StagePlayRepository;
import com.example.phoenixtree.repository.StageSceneRepository;
import com.example.phoenixtree.util.AbsentLiveData;
import com.example.phoenixtree.util.PanelInterface;
import com.example.phoenixtree.util.processor.AudioProcessor;
import com.example.phoenixtree.util.processor.KeyframeProcessor;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by ej on 8/21/2017.
 */

public class StagePlayViewModel extends ViewModel implements PanelInterface {

    private final MutableLiveData<Long> playId = new MutableLiveData<>();
    private final MutableLiveData<Long> sceneId = new MutableLiveData<>();

    public final LiveData<Resource<StagePlay>> play;
    public final LiveData<StageScene> scene;
    public final LiveData<Keyframe> keyframe;
    private final MutableLiveData<Keyframe> keyframeMutableLiveData = new MediatorLiveData<>();

    private final KeyframeProcessor keyframeP;
    private final AudioProcessor audioP;

    @Inject
    public StagePlayViewModel(final StagePlayRepository playRepository,
                              final StageSceneRepository sceneRepository,
                              KeyframeProcessor keyframeProcessor,
                              AudioProcessor audioProcessor) {

        keyframeP = keyframeProcessor;
        audioP = audioProcessor;

        play = Transformations.switchMap(playId, new Function<Long, LiveData<Resource<StagePlay>>>() {
            @Override
            public LiveData<Resource<StagePlay>> apply(Long input) {
                if(playId == null) {
                    return AbsentLiveData.create();
                } else {
                    return playRepository.loadPlay(input);
                }
            }
        });

        scene = Transformations.switchMap(sceneId, new Function<Long, LiveData<StageScene>>() {
                    @Override
                    public LiveData<StageScene> apply(Long input) {
                        if(sceneId == null) {
                            return AbsentLiveData.create();
                        } else {
                            return sceneRepository.loadScene(input);
                        }
                    }
                });
        /*
        Transformations.switchMap(play, new Function<Resource<StagePlay>, LiveData<Object>>() {
            @Override
            public LiveData<Object> apply(Resource<StagePlay> input) {
                switch (input.status) {
                    case SUCCESS:
                        StagePlay play = input.data;
                        keyframeP.setStage(play.stage);
                        break;
                }
                return null;
            }
        });
        */
                keyframe = Transformations.switchMap(sceneId, new Function<Long, LiveData<Keyframe>>() {
                    @Override
                    public LiveData<Keyframe> apply(Long input) {
                        Resource<StagePlay> resource = play.getValue();
                        if (resource != null && resource.status == Status.SUCCESS) {
                            StagePlay play = resource.data;
                            for (StageScene scene : play.scenes) {
                                if (scene.id == input) {
                                    keyframeP.setStage(play.stage);
                                    keyframeP.setScene(scene);
                                    return keyframeP.firstFrame();
                                }
                            }
                        }
                        keyframeMutableLiveData.setValue(null);
                        return keyframeMutableLiveData;
                    }
                });

    }

    public void setPlayId(long playId) {
        if (Objects.equals(this.playId.getValue(), playId)) {
            return;
        }
        this.playId.setValue(playId);
    }

    public void setSceneId(long sceneId) {
        if (Objects.equals(this.sceneId.getValue(), sceneId)) {
            return;
        }
        this.sceneId.setValue(sceneId);
    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {

    }
}
