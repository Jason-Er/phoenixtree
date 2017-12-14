package com.example.phoenixtree.view.main;

import android.support.v4.app.Fragment;

import com.example.phoenixtree.view.browse.BrowseFragment;
import com.example.phoenixtree.view.browse.BrowseFragmentComponent;
import com.example.phoenixtree.view.compose.ComposeFragment;
import com.example.phoenixtree.view.compose.ComposeFragmentComponent;
import com.example.phoenixtree.view.participate.ParticipateFragment;
import com.example.phoenixtree.view.participate.ParticipateFragmentComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by ej on 9/8/2017.
 */
@Module(subcomponents = {
        ParticipateFragmentComponent.class,
        BrowseFragmentComponent.class,
        ComposeFragmentComponent.class})
public abstract class MainActivityFragmentProvider {
    @Binds
    @IntoMap
    @FragmentKey(ParticipateFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> provideParticipateFragmentFactory(ParticipateFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(BrowseFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> provideBrowseFragmentFactory(BrowseFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ComposeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> provideComposeFragmentFactory(ComposeFragmentComponent.Builder builder);
}
