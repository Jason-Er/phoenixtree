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
    public final LiveData<Resource<Keyframe>> keyframe;

    private KeyframeProcessor keyframeProcessor = new KeyframeProcessor();
    private AudioProcessor audioProcessor = new AudioProcessor();

    @Inject
    public SceneViewModel(final SceneRepository repository) {
        keyframe = Transformations.switchMap(sceneId, new Function<Long, LiveData<Resource<Keyframe>>>() {
            @Override
            public LiveData<Resource<Keyframe>> apply(Long input) {
                if(input == null) {
                    return AbsentLiveData.create();
                } else {
                    return Transformations.map(repository.loadScene(input), new Function<Resource<Scene4PW>, Resource<Keyframe>>() {
                        @Override
                        public Resource<Keyframe> apply(Resource<Scene4PW> sceneResource) {
                            Resource resource = null;
                            switch (sceneResource.status) {
                                case SUCCESS:
                                    resource = Resource.success(Fake.propagateKeyframe());
                                    break;
                                case ERROR:
                                    resource = Resource.error(sceneResource.message , new Keyframe());
                                    break;
                                case LOADING:
                                    resource = Resource.loading(new Keyframe());
                                    break;
                            }
                            return resource;
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
