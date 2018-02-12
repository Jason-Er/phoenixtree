package com.example.phoenixtree.view.customFrameLayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.widget.FrameLayout;

import com.example.phoenixtree.util.UICommon;
import com.example.phoenixtree.util.commonInterface.NavigationInterface;

/**
 * Created by ej on 12/26/2017.
 */

public class RecyclerContainer extends FrameLayout implements NavigationInterface{

    public RecyclerContainer(@NonNull Context context) {
        super(context);
    }

    public RecyclerContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void navigateToBrowse() {
        addMainContainerMargin();
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        removeMainContainerMargin();
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        removeMainContainerMargin();
    }

    @Override
    public void navigateToLogin() {
        addMainContainerMargin();
    }

    @Override
    public void navigateToProfile() {
        addMainContainerMargin();
    }

    private void addMainContainerMargin() {
        UICommon.setViewMargin(this, true, 0, 0, 48, 0);
    }

    private void removeMainContainerMargin() {
        UICommon.setViewMargin(this, true, 0, 0, 0, 0);
    }
}
