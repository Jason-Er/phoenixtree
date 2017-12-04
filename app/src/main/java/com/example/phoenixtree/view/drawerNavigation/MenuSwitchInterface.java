package com.example.phoenixtree.view.drawerNavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

/**
 * Created by ej on 11/30/2017.
 */

public interface MenuSwitchInterface {
    void switchToBrowse(@NonNull NavigationView navigationView);
    void switchToParticipate(@NonNull NavigationView navigationView);
    void switchToCompose(@NonNull NavigationView navigationView);
}
