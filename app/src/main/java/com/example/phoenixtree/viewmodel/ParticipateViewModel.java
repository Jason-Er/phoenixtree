package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.StagePlay;
import com.example.phoenixtree.repository.StagePlayRepository;
import com.example.phoenixtree.util.AbsentLiveData;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by ej on 8/21/2017.
 */

public class ParticipateViewModel extends ViewModel {

    private final MutableLiveData<Long> playId = new MutableLiveData<>();
    public final LiveData<Resource<StagePlay>> play;

    @Inject
    public ParticipateViewModel(final StagePlayRepository repository) {
        play = Transformations.switchMap(playId, new Function<Long, LiveData<Resource<StagePlay>>>() {
            @Override
            public LiveData<Resource<StagePlay>> apply(Long input) {
                if(playId == null) {
                    return AbsentLiveData.create();
                } else {
                    return repository.loadPlay(input);
                }
            }
        });
    }

    public void setPlayId(long playId) {
        if (Objects.equals(this.playId.getValue(), playId)) {
            return;
        }
        this.playId.setValue(playId);
    }
}
