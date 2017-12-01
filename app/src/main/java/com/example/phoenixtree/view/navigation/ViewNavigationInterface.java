package com.example.phoenixtree.view.navigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

/**
 * Created by ej on 11/30/2017.
 */

public interface ViewNavigationInterface {
    void navigateToBrowse(@NonNull NavigationView navigationView);
    void navigateToParticipate(long stagePlayId);
    void navigateToCompose(long stagePlayId);
}
