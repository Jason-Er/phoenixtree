package com.example.phoenixtree.view.compose;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ej on 11/30/2017.
 */
@Subcomponent
public interface ComposeFragmentComponent extends AndroidInjector<ComposeFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ComposeFragment> {}
}
