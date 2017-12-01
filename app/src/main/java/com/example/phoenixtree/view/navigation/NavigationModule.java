package com.example.phoenixtree.view.navigation;

import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;
import com.example.phoenixtree.view.main.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 11/30/2017.
 */
@Module
public class NavigationModule {

    @Type("player")
    @PerActivity
    @Provides
    ViewNavigationInterface providePlayerNavigation(MainActivity mainActivity) {
        return new PlayerNavigation(mainActivity);
    }

    @Type("writer")
    @PerActivity
    @Provides
    ViewNavigationInterface provideWriterNavigation(MainActivity mainActivity) {
        return new WriterNavigation(mainActivity);
    }

    @Type("director")
    @PerActivity
    @Provides
    ViewNavigationInterface provideDirectorNavigation(MainActivity mainActivity) {
        return new DirectorNavigation(mainActivity);
    }

}
