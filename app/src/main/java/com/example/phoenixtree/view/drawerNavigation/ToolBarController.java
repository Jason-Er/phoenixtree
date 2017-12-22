package com.example.phoenixtree.view.drawerNavigation;

import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.view.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by ej on 12/21/2017.
 */
@PerActivity
public class ToolBarController {
    final MainActivity mainActivity;
    @Inject
    public ToolBarController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void show() {
        mainActivity.getSupportActionBar().show();
    }

    public void hide() {
        mainActivity.getSupportActionBar().hide();
    }

}
