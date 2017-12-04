package com.example.phoenixtree.view.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
public class DirectorMenuSwitch implements MenuSwitchInterface {

    @Inject
    public DirectorMenuSwitch() {
    }

    @Override
    public void switchToBrowse(@NonNull NavigationView navigationView) {
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.director_browse_drawer);
    }

    @Override
    public void switchToParticipate(@NonNull NavigationView navigationView) {
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.director_participate_drawer);
    }

    @Override
    public void switchToCompose(@NonNull NavigationView navigationView) {
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.director_compose_drawer);
    }

}
