package com.example.phoenixtree.view.drawerNavigation;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoenixtree.R;
import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.view.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by ej on 12/21/2017.
 */
@PerActivity
public class CatalogueController {

    final MainActivity mainActivity;

    @Inject
    public CatalogueController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void loadStagePlayCatalogue(@NonNull ViewGroup drawerLayout) {

        if( mainActivity.findViewById(R.id.stage_play_catalogue) == null ) {
            LayoutInflater inflater = mainActivity.getLayoutInflater();
            View layout = inflater.inflate(R.layout.stage_play_catalogue, drawerLayout, false);
            drawerLayout.addView(layout);
        }

    }

    public void unLoadStagePlayCatalogue(@NonNull ViewGroup drawerLayout) {

        View view = mainActivity.findViewById(R.id.stage_play_catalogue);
        if( view != null ) {
            drawerLayout.removeView(view);
        }

    }

}
