package com.example.phoenixtree.view.participate;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by ej on 9/8/2017.
 */

@Subcomponent
public interface ParticipateFragmentComponent extends AndroidInjector<ParticipateFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ParticipateFragment> {}
}
