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

    @Type("role1")
    @PerActivity
    @Provides
    ViewNavigationInterface provideViewNavigationInterface(MainActivity mainActivity) {
        return new FragmentNavigation(mainActivity);
    }

}
