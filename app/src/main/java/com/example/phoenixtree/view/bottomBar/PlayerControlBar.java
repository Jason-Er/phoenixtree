package com.example.phoenixtree.view.bottomBar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.phoenixtree.util.commonInterface.NavigationInterface;

/**
 * Created by ej on 12/12/2017.
 */

@CoordinatorLayout.DefaultBehavior(PlayerControlBarBehavior.class)
public class PlayerControlBar extends LinearLayout implements NavigationInterface{

    final String TAG = "PlayerControlBar";

    public PlayerControlBar(Context context) {
        super(context);
    }

    public PlayerControlBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerControlBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
