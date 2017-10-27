package com.example.phoenixtree.view.browse;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ej on 10/16/2017.
 */
@Subcomponent
public interface BrowseFragmentComponent extends AndroidInjector<BrowseFragment>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BrowseFragment> {}
}
