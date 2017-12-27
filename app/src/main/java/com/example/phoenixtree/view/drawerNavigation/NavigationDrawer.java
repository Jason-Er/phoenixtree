package com.example.phoenixtree.view.drawerNavigation;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;

import com.example.phoenixtree.R;
import com.example.phoenixtree.util.commonInterface.CharacterInterface;
import com.example.phoenixtree.util.commonInterface.NavigationInterface;

import javax.inject.Inject;

/**
 * Created by ej on 12/27/2017.
 */

public class NavigationDrawer extends NavigationView implements NavigationInterface, CharacterInterface{

    NavigationInterface drawer4Character;

    NavigationInterface drawer4Stranger;
    NavigationInterface drawer4Player;
    NavigationInterface drawer4Writer;
    NavigationInterface drawer4Director;

    public NavigationDrawer(Context context) {
        super(context);
    }

    public NavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void navigateToBrowse() {
        // TODO: 12/27/2017 need further refactoring
        // drawer4Character.navigateToBrowse();
        getMenu().clear();
        inflateMenu(R.menu.director_browse_drawer);
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        // drawer4Character.navigateToParticipate(stagePlayId);
        getMenu().clear();
        inflateMenu(R.menu.director_participate_drawer);
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        // drawer4Character.navigateToCompose(stagePlayId);
        getMenu().clear();
        inflateMenu(R.menu.director_compose_drawer);
    }

    @Override
    public void navigateToLogin() {
        // drawer4Character.navigateToLogin();
        throw new UnsupportedOperationException();
    }

    @Override
    public void navigateToProfile() {
        // drawer4Character.navigateToProfile();
        throw new UnsupportedOperationException();
    }

    @Override
    public void switchToStranger() {

    }

    @Override
    public void switchToPlayer() {

    }

    @Override
    public void switchToWriter() {

    }

    @Override
    public void switchToDirector() {

    }
}
