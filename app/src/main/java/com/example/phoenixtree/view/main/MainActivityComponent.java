package com.example.phoenixtree.view.main;

import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.view.navigation.MenuSwitchModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ej on 9/7/2017.
 */

@PerActivity
@Subcomponent(modules = {
        MainActivityFragmentProvider.class,
        MenuSwitchModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
}
