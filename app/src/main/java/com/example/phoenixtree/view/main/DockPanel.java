package com.example.phoenixtree.view.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

import com.example.phoenixtree.util.UICommon;
import com.example.phoenixtree.util.FragmentName;
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
        UICommon.notifyChildrenWhereToGo(this, FragmentName.BROWSE, 0);
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        UICommon.notifyChildrenWhereToGo(this, FragmentName.PARTICIPATE, stagePlayId);
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        UICommon.notifyChildrenWhereToGo(this, FragmentName.COMPOSE, stagePlayId);
    }

    @Override
    public void navigateToLogin() {
        UICommon.notifyChildrenWhereToGo(this, FragmentName.LOGIN, 0);
    }

    @Override
    public void navigateToProfile() {
        UICommon.notifyChildrenWhereToGo(this, FragmentName.PROFILE, 0);
    }

}
