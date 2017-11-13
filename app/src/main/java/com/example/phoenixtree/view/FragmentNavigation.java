package com.example.phoenixtree.view;

import android.os.Bundle;
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

@PerActivity
public class FragmentNavigation {
    private final String NAVIGATION_KEY = "navigation-key";
    private final int containerId;
    private final FragmentManager fragmentManager;

    private final String BROWSE = "browse";
    private final String PARTICIPATE = "participate";
    private String currentFragmentName = BROWSE;

    @Inject
    public FragmentNavigation(MainActivity mainActivity) {
        this.containerId = R.id.main_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateTo(String desFragment) {
        switch ( desFragment ) {
            case BROWSE:
                navigateToBrowse();
                break;
            case PARTICIPATE:
                navigateToParticipate();
                break;
        }
    }

    public void navigateToBrowse() {
        BrowseFragment fragment = new BrowseFragment();
        currentFragmentName = BROWSE;
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, BROWSE)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToParticipate() {
        ParticipateFragment fragment = new ParticipateFragment();
        currentFragmentName = PARTICIPATE;
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, PARTICIPATE)
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }

    public void navigateToParticipate(long stagePlayId) {
        ParticipateFragment fragment = ParticipateFragment.create(stagePlayId);
        String tag = "participate" + stagePlayId;
        currentFragmentName = PARTICIPATE;
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void restore(Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.getString(NAVIGATION_KEY) != null) {
            navigateTo( savedInstanceState.getString(NAVIGATION_KEY) );
        } else {
            navigateToBrowse();
        }
    }

    public void storeState(Bundle savedInstanceState) {
        savedInstanceState.putString(NAVIGATION_KEY, currentFragmentName);
    }

}
