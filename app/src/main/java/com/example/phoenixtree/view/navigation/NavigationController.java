package com.example.phoenixtree.view.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;

import com.example.phoenixtree.R;
import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;
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
        ComposeFragment fragment = ComposeFragment.create(stagePlayId);
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, COMPOSE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        menuSwitch.switchToCompose(navigationView);
    }

    public void setNavigationView(@NonNull NavigationView navigationView) {
        this.navigationView = navigationView;
    }
}
