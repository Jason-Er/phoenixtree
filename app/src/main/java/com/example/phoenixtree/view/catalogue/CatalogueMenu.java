package com.example.phoenixtree.view.catalogue;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.phoenixtree.util.commonInterface.NavigationInterface;

/**
 * Created by ej on 12/27/2017.
 */

public class CatalogueMenu extends RecyclerView implements NavigationInterface{

    public CatalogueMenu(Context context) {
        super(context);
    }

    public CatalogueMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CatalogueMenu(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void navigateToBrowse() {
        setVisibility(View.GONE);
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToLogin() {
        setVisibility(View.GONE);
    }

    @Override
    public void navigateToProfile() {
        setVisibility(View.GONE);
    }

}
