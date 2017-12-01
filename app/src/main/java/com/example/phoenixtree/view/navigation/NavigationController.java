package com.example.phoenixtree.view.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;

import com.example.phoenixtree.R;
import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;
import com.example.phoenixtree.view.browse.BrowseFragment;
import com.example.phoenixtree.view.main.MainActivity;
import com.example.phoenixtree.view.participate.ParticipateFragment;

import javax.inject.Inject;

/**
 * Created by ej on 11/30/2017.
 */
@PerActivity
public class NavigationController {

    private final int containerId;
    private final FragmentManager fragmentManager;
    private final String BROWSE = "browse";
    private final String PARTICIPATE = "participate";

    private final MenuSwitchInterface playerNavigation;
    private final MenuSwitchInterface writerNavigation;
    private final MenuSwitchInterface directorNavigation;

    private MenuSwitchInterface menuSwitch;

    private NavigationView navigationView;

    @Inject
    public NavigationController(
            MainActivity mainActivity,
            @Type("player") MenuSwitchInterface playerNavigation,
            @Type("writer") MenuSwitchInterface writerNavigation,
            @Type("director") MenuSwitchInterface directorNavigation) {

        this.containerId = R.id.main_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();

        this.playerNavigation = playerNavigation;
        this.writerNavigation = writerNavigation;
        this.directorNavigation = directorNavigation;
        menuSwitch = playerNavigation;
    }

    public void switchToPlayerNavigation() {
        menuSwitch = playerNavigation;
    }

    public void switchToWriterNavigation() {
        menuSwitch = writerNavigation;
    }

    public void switchToDirectorNavigation() {
        menuSwitch = directorNavigation;
    }

    public void navigateToBrowse() {
        BrowseFragment fragment = new BrowseFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, BROWSE)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        menuSwitch.switchToBrowse(navigationView);
    }

    public void navigateToParticipate(long stagePlayId) {
        ParticipateFragment fragment = ParticipateFragment.create(stagePlayId);
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, PARTICIPATE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        menuSwitch.switchToParticipate(navigationView);
    }

    public void navigateToCompose(long stagePlayId) {
        menuSwitch.switchToCompose(navigationView);
    }

    public void setNavigationView(@NonNull NavigationView navigationView) {
        this.navigationView = navigationView;
    }
}
