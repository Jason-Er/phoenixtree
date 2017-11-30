package com.example.phoenixtree.view.navigation;

import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;

import javax.inject.Inject;

/**
 * Created by ej on 11/30/2017.
 */
@PerActivity
public class NavigationController implements ViewNavigationInterface {

    private final ViewNavigationInterface playerNavigation;
    private final ViewNavigationInterface writerNavigation;
    private final ViewNavigationInterface directorNavigation;

    private ViewNavigationInterface currentNavigation;

    @Inject
    public NavigationController(@Type("player") ViewNavigationInterface playerNavigation, @Type("writer") ViewNavigationInterface writerNavigation, @Type("director") ViewNavigationInterface directorNavigation) {
        this.playerNavigation = playerNavigation;
        this.writerNavigation = writerNavigation;
        this.directorNavigation = directorNavigation;
        currentNavigation = playerNavigation;
    }

    public void switchToPlayerNavigation() {
        currentNavigation = playerNavigation;
    }

    public void switchToWriterNavigation() {
        currentNavigation = writerNavigation;
    }

    public void switchToDirectorNavigation() {
        currentNavigation = directorNavigation;
    }

    @Override
    public void navigateToBrowse() {
        currentNavigation.navigateToBrowse();
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        currentNavigation.navigateToParticipate(stagePlayId);
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        currentNavigation.navigateToCompose(stagePlayId);
    }
}
