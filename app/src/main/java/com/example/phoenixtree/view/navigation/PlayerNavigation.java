package com.example.phoenixtree.view.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

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
public class PlayerNavigation implements ViewNavigationInterface {

    private final int containerId;
    private final FragmentManager fragmentManager;

    private final String BROWSE = "browse";
    private final String PARTICIPATE = "participate";

    @Inject
    public PlayerNavigation(MainActivity mainActivity) {
        this.containerId = R.id.main_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    @Override
    public void navigateToBrowse(@NonNull NavigationView navigationView) {
        BrowseFragment fragment = new BrowseFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, BROWSE)
                .addToBackStack(null)
                .commitAllowingStateLoss();

        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.player_browse_drawer);

    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        ParticipateFragment fragment = ParticipateFragment.create(stagePlayId);
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, PARTICIPATE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        throw new UnsupportedOperationException();
    }

}
