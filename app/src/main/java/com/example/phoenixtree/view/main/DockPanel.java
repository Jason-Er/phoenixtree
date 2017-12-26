package com.example.phoenixtree.view.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

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
        for (int i = 0; i < getChildCount(); i++) {
            ((NavigationInterface)getChildAt(i)).navigateToBrowse();
        }
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        for (int i = 0; i < getChildCount(); i++) {
            ((NavigationInterface)getChildAt(i)).navigateToParticipate(stagePlayId);
        }
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        for (int i = 0; i < getChildCount(); i++) {
            ((NavigationInterface)getChildAt(i)).navigateToCompose(stagePlayId);
        }
    }

    @Override
    public void navigateToLogin() {
        for (int i = 0; i < getChildCount(); i++) {
            ((NavigationInterface)getChildAt(i)).navigateToLogin();
        }
    }

    @Override
    public void navigateToProfile() {
        for (int i = 0; i < getChildCount(); i++) {
            ((NavigationInterface)getChildAt(i)).navigateToProfile();
        }
    }

}
