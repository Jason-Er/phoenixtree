package com.example.phoenixtree.view.navigation;

import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.di.label.Type;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 11/30/2017.
 */
@Module
public class MenuSwitchModule {

    @Type("player")
    @PerActivity
    @Provides
    MenuSwitchInterface providePlayerMenuSwitch() {
        return new PlayerMenuSwitch();
    }

    @Type("writer")
    @PerActivity
    @Provides
    MenuSwitchInterface provideWriterMenuSwitch() {
        return new WriterMenuSwitch();
    }

    @Type("director")
    @PerActivity
    @Provides
    MenuSwitchInterface provideDirectorMenuSwitch() {
        return new DirectorMenuSwitch();
    }

}
