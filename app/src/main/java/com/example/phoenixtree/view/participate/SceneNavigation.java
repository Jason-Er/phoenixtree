package com.example.phoenixtree.view.participate;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.example.phoenixtree.R;
import com.example.phoenixtree.model.StageScene;
import com.example.phoenixtree.view.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ej on 10/30/2017.
 */

class SceneNavigation {
    private final int containerId;
    private final FragmentManager fragmentManager;
    private List<StageScene> stageScenes;
    private int currentOrdinal;
    @Inject
    public SceneNavigation(MainActivity mainActivity) {
        this.containerId = R.id.fragment_participate_frame;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
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
        SceneFragment fragment = SceneFragment.create(scene.id);
        String tag = "sceneFragment" + scene.id;
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .commitAllowingStateLoss();
    }
}
