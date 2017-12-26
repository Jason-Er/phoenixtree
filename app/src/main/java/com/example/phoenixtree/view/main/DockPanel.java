package com.example.phoenixtree.view.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.example.phoenixtree.R;
import com.example.phoenixtree.util.UICommon;
import com.example.phoenixtree.util.commonInterface.NavigationInterface;

/**
 * Created by ej on 12/26/2017.
 */

public class DockPanel extends CoordinatorLayout implements NavigationInterface{

    public DockPanel(Context context) {
        super(context);
    }

    public DockPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DockPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void navigateToBrowse() {
        addMainContainerMargin();
        hideBottomBar();
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        removeMainContainerMargin();
        showBottomBar();
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        removeMainContainerMargin();
        showBottomBar();
    }

    @Override
    public void navigateToLogin() {
        addMainContainerMargin();
        showTopBar();
        hideBottomBar();
    }

    @Override
    public void navigateToProfile() {
        addMainContainerMargin();
        hideBottomBar();
    }

    private void showBottomBar() {
        findViewById(R.id.bottom_bar).setVisibility(View.VISIBLE);
    }

    private void hideBottomBar() {
        findViewById(R.id.bottom_bar).setVisibility(View.GONE);
    }

    private void showTopBar() {
        findViewById(R.id.top_bar).setVisibility(View.VISIBLE);
    }

    private void hideTopBar() {
        findViewById(R.id.top_bar).setVisibility(View.GONE);
    }

    private void addMainContainerMargin() {
        View view = findViewById(R.id.main_container);
        UICommon.setViewMargin(view, true, 0, 0, 48, 0);
        invalidate();
    }

    private void removeMainContainerMargin() {
        View view = findViewById(R.id.main_container);
        UICommon.setViewMargin(view, true, 0, 0, 0, 0);
        invalidate();
    }

}
