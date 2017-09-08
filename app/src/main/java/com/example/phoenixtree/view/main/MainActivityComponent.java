package com.example.phoenixtree.view.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ej on 9/7/2017.
 */

@Subcomponent(modules = {MainActivityFragmentProvider.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
}
