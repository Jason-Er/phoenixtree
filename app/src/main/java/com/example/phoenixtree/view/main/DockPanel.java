package com.example.phoenixtree.view.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
        unLoadBottomBar();
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        removeMainContainerMargin();
        loadBottomBar();
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        removeMainContainerMargin();
        loadBottomBar();
    }

    @Override
    public void navigateToLogin() {
        addMainContainerMargin();
        unLoadBottomBar();
    }

    @Override
    public void navigateToProfile() {
        addMainContainerMargin();
        unLoadBottomBar();
    }

    private void loadBottomBar() {
        if( findViewById(R.id.bottom_bar) == null ) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View layout = inflater.inflate(R.layout.bottom_bar, this, false);
            addView(layout);
        }
    }

    private void unLoadBottomBar() {
        View view = findViewById(R.id.bottom_bar);
        if( view != null ) {
            removeView(view);
        }
    }

    private void loadTopBar() {

    }

    private void unLoadTopBar() {

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
