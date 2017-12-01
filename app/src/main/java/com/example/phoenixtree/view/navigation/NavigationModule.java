package com.example.phoenixtree.view.navigation;

import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;

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
    MenuSwitchInterface providePlayerNavigation() {
        return new PlayerNavigation();
    }

    @Type("writer")
    @PerActivity
    @Provides
    MenuSwitchInterface provideWriterNavigation() {
        return new WriterNavigation();
    }

    @Type("director")
    @PerActivity
    @Provides
    MenuSwitchInterface provideDirectorNavigation() {
        return new DirectorNavigation();
    }

}
