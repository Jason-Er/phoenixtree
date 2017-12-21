package com.example.phoenixtree.view.drawerNavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ViewGroup;

import com.example.phoenixtree.R;
import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;
import com.example.phoenixtree.util.commonInterface.StagePlayInfo;
import com.example.phoenixtree.view.browse.BrowseFragment;
import com.example.phoenixtree.view.compose.ComposeFragment;
import com.example.phoenixtree.view.login.LoginFragment;
import com.example.phoenixtree.view.main.MainActivity;
import com.example.phoenixtree.view.participate.ParticipateFragment;

import javax.inject.Inject;

/**
 * Created by ej on 11/30/2017.
 */
@PerActivity
public class NavigationController {

    private final String TAG = "NavigationController";
    private final int containerId;
    private final FragmentManager fragmentManager;
    private final String BROWSE = "browse";
    private final String PARTICIPATE = "participate";
    private final String COMPOSE = "compose";
    private final String LOGIN = "login";

    private final MenuSwitchInterface playerMenuSwitch;
    private final MenuSwitchInterface writerMenuSwitch;
    private final MenuSwitchInterface directorMenuSwitch;
    private final BottomBarController bottomBarController;
    private final CatalogueController catalogueController;
    private final SystemUIController systemUIController;
    private final ToolBarController toolBarController;

    private Fragment currentFragment;
    private MenuSwitchInterface menuSwitch;

    private NavigationView navigationView;
    private ViewGroup coordinatorLayout;
    private ViewGroup drawerLayout;

    @Inject
    public NavigationController(
            MainActivity mainActivity,
            BottomBarController bottomBarController,
            CatalogueController catalogueController,
            SystemUIController systemUIController,
            ToolBarController toolBarController,
            @Type("player") MenuSwitchInterface playerMenuSwitch,
            @Type("writer") MenuSwitchInterface writerMenuSwitch,
            @Type("director") MenuSwitchInterface directorMenuSwitch) {

        this.containerId = R.id.main_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();

        this.bottomBarController = bottomBarController;
        this.catalogueController = catalogueController;
        this.systemUIController = systemUIController;
        this.toolBarController = toolBarController;
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
        bottomBarController.unLoadBottomBar(coordinatorLayout);
        catalogueController.unLoadStagePlayCatalogue(drawerLayout);
        systemUIController.show();
        toolBarController.show();
    }

    public void navigateToParticipate(long stagePlayId) {
        currentFragment = ParticipateFragment.create(stagePlayId);
        fragmentManager.beginTransaction()
                .replace(containerId, currentFragment, PARTICIPATE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        menuSwitch.switchToParticipate(navigationView);
        bottomBarController.loadBottomBar(coordinatorLayout);
        catalogueController.loadStagePlayCatalogue(drawerLayout);
        systemUIController.hide();
        toolBarController.hide();

    }

    public void navigateToCompose(long stagePlayId) {
        currentFragment = ComposeFragment.create(stagePlayId);
        fragmentManager.beginTransaction()
                .replace(containerId, currentFragment, COMPOSE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        menuSwitch.switchToCompose(navigationView);
        bottomBarController.loadBottomBar(coordinatorLayout);
        catalogueController.loadStagePlayCatalogue(drawerLayout);
        systemUIController.hide();
        toolBarController.hide();
    }

    public void navigateToLogin() {
        Log.i(TAG, "navigateToLogin");
        currentFragment = new LoginFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, currentFragment, LOGIN)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        menuSwitch.switchToCompose(navigationView);
        bottomBarController.unLoadBottomBar(coordinatorLayout);
        catalogueController.unLoadStagePlayCatalogue(drawerLayout);
        systemUIController.show();
        toolBarController.show();
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

    public void setCoordinatorLayout(@NonNull ViewGroup coordinatorLayout) {
        this.coordinatorLayout = coordinatorLayout;
    }

    public void setDrawerLayout(@NonNull ViewGroup drawerLayout) {
        this.drawerLayout = drawerLayout;
    }
}
