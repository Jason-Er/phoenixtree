package com.example.phoenixtree.view.drawerNavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.phoenixtree.R;
import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;
import com.example.phoenixtree.util.commonInterface.StagePlayInfo;
import com.example.phoenixtree.view.browse.BrowseFragment;
import com.example.phoenixtree.view.compose.ComposeFragment;
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
    private final String COMPOSE = "compose";

    private final MenuSwitchInterface playerMenuSwitch;
    private final MenuSwitchInterface writerMenuSwitch;
    private final MenuSwitchInterface directorMenuSwitch;

    private Fragment currentFragment;
    private MenuSwitchInterface menuSwitch;
    private NavigationView navigationView;

    @Inject
    public NavigationController(
            MainActivity mainActivity,
            @Type("player") MenuSwitchInterface playerMenuSwitch,
            @Type("writer") MenuSwitchInterface writerMenuSwitch,
            @Type("director") MenuSwitchInterface directorMenuSwitch) {

        this.containerId = R.id.main_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();

        this.playerMenuSwitch = playerMenuSwitch;
        this.writerMenuSwitch = writerMenuSwitch;
        this.directorMenuSwitch = directorMenuSwitch;
        menuSwitch = directorMenuSwitch;
    }

    public void switchToPlayerNavigation() {
        menuSwitch = playerMenuSwitch;
    }

    public void switchToWriterNavigation() {
        menuSwitch = writerMenuSwitch;
    }

    public void switchToDirectorNavigation() {
        menuSwitch = directorMenuSwitch;
    }

    public void navigateToBrowse() {
        currentFragment = new BrowseFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, currentFragment, BROWSE)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        menuSwitch.switchToBrowse(navigationView);
    }

    public void navigateToParticipate(long stagePlayId) {
        currentFragment = ParticipateFragment.create(stagePlayId);
        fragmentManager.beginTransaction()
                .replace(containerId, currentFragment, PARTICIPATE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        menuSwitch.switchToParticipate(navigationView);
    }

    public void navigateToCompose(long stagePlayId) {
        currentFragment = ComposeFragment.create(stagePlayId);
        fragmentManager.beginTransaction()
                .replace(containerId, currentFragment, COMPOSE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        menuSwitch.switchToCompose(navigationView);
    }

    public long getCurrentStagePlayId() {
        if(currentFragment instanceof StagePlayInfo ) {
            return ((StagePlayInfo) currentFragment).getStagePlayID();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void setNavigationView(@NonNull NavigationView navigationView) {
        this.navigationView = navigationView;
    }

}
