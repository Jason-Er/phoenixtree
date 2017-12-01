package com.example.phoenixtree.view.login;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ej on 11/30/2017.
 */
@Subcomponent
public interface LoginFragmentComponent extends AndroidInjector<LoginFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginFragment> {}
}
