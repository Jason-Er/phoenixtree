package com.example.phoenixtree.view.sceneNavigation;

import android.support.annotation.NonNull;

import com.example.phoenixtree.model.StageScene;
import com.example.phoenixtree.viewmodel.StagePlayViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ej on 10/30/2017.
 */

public class SceneNavigation {

    private final StagePlayViewModel viewModel;
    private List<StageScene> stageScenes;
    private int currentOrdinal;
    @Inject
    public SceneNavigation(StagePlayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setStageScenes(@NonNull List<StageScene> stageScenes) {
        this.stageScenes = stageScenes;
    }

    public void navigateToFirst() {
        currentOrdinal = 0;
        navigateTo(0);
    }

    public void navigateToLast() {
        currentOrdinal = stageScenes.size()-1;
        navigateTo(stageScenes.size()-1);
    }

    public void navigateToNext() {
        if( currentOrdinal + 1 < stageScenes.size() ){
            currentOrdinal ++;
            navigateTo(currentOrdinal);
        }
    }

    public void navigateToPre() {
        if( currentOrdinal - 1 >= 0 ) {
            currentOrdinal --;
            navigateTo(currentOrdinal);
        }
    }

    public void navigateTo(int ordinal) {
        StageScene scene = stageScenes.get(ordinal);
        viewModel.setSceneId(scene.id);
    }
}
