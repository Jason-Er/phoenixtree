package com.example.phoenixtree.view.drawerNavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.example.phoenixtree.R;
import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.view.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by ej on 12/21/2017.
 */
@PerActivity
public class BottomBarController {

    final MainActivity mainActivity;

    @Inject
    public BottomBarController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void loadBottomBar(@NonNull CoordinatorLayout coordinatorLayout) {

        if( mainActivity.findViewById(R.id.bottom_bar) == null ) {
            LayoutInflater inflater = mainActivity.getLayoutInflater();
            View layout = inflater.inflate(R.layout.bottom_bar, null);
            coordinatorLayout.addView(layout);
        }

    }

    public void unLoadBottomBar(@NonNull CoordinatorLayout coordinatorLayout) {

        View view = mainActivity.findViewById(R.id.bottom_bar);
        if( view != null ) {
            coordinatorLayout.removeView(view);
        }

    }
}
