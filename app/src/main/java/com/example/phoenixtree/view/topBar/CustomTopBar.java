package com.example.phoenixtree.view.topBar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.phoenixtree.R;
import com.example.phoenixtree.util.commonInterface.NavigationInterface;

/**
 * Created by ej on 12/22/2017.
 */
@CoordinatorLayout.DefaultBehavior(CustomTopBarBehavior.class)
public class CustomTopBar extends RelativeLayout implements NavigationInterface {

    public CustomTopBar(Context context) {
        super(context);
    }

    public CustomTopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTopBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void navigateToBrowse() {
        setVisibility(View.VISIBLE);
        findViewById(R.id.btn_drawer_toggle).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_nav_back).setVisibility(View.GONE);
        findViewById(R.id.btn_user_login).setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        setVisibility(View.GONE);
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        setVisibility(View.GONE);
    }

    @Override
    public void navigateToLogin() {
        setVisibility(View.VISIBLE);
        findViewById(R.id.btn_drawer_toggle).setVisibility(View.GONE);
        findViewById(R.id.btn_nav_back).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_user_login).setVisibility(View.GONE);
    }

    @Override
    public void navigateToProfile() {
        setVisibility(View.VISIBLE);
        findViewById(R.id.btn_drawer_toggle).setVisibility(View.GONE);
        findViewById(R.id.btn_nav_back).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_user_login).setVisibility(View.VISIBLE);
    }
}
