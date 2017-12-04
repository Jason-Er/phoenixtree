package com.example.phoenixtree.view.drawerNavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import com.example.phoenixtree.di.label.PerActivity;

import javax.inject.Inject;

/**
 * Created by ej on 9/4/2017.
 */

@PerActivity
public class WriterMenuSwitch implements MenuSwitchInterface {

    @Inject
    public WriterMenuSwitch() {
    }

    @Override
    public void switchToBrowse(@NonNull NavigationView navigationView) {
    }

    @Override
    public void switchToParticipate(@NonNull NavigationView navigationView) {
    }

    @Override
    public void switchToCompose(@NonNull NavigationView navigationView) {
    }

}
