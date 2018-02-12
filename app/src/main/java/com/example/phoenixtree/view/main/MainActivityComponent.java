package com.example.phoenixtree.view.main;

import com.example.phoenixtree.di.label.PerActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ej on 9/7/2017.
 */

@PerActivity
@Subcomponent(modules = {
        MainActivityFragmentProvider.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
}
