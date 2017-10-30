package com.example.phoenixtree.view;

import android.support.v4.app.FragmentManager;

import com.example.phoenixtree.R;
import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.view.browse.BrowseFragment;
import com.example.phoenixtree.view.main.MainActivity;
import com.example.phoenixtree.view.participate.ParticipateFragment;

import javax.inject.Inject;

/**
 * Created by ej on 9/4/2017.
 */

public class FragmentNavigation {
    private final int containerId;
    private final FragmentManager fragmentManager;
    @Inject
    public FragmentNavigation(MainActivity mainActivity) {
        this.containerId = R.id.main_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToBrowse() {
        BrowseFragment fragment = new BrowseFragment();
        String tag = "browse";
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToParticipate(long stagePlayId) {
        ParticipateFragment fragment = ParticipateFragment.create(stagePlayId);
        String tag = "participate" + stagePlayId;
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

}
