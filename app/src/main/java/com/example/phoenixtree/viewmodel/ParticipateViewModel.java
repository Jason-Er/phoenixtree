package com.example.phoenixtree.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.Model.Play;

import javax.inject.Inject;

/**
 * Created by ej on 8/21/2017.
 */

public class ParticipateViewModel extends ViewModel {
    private String playId;
    private LiveData<Play> play;

    public void init(String playId) {
        this.playId = playId;
    }
    public LiveData<Play> getPlay() {
        return play;
    }

    @Inject
    public ParticipateViewModel() {

    }
}
