package com.example.phoenixtree.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.phoenixtree.di.label.ViewModelKey;
import com.example.phoenixtree.viewmodel.ParticipateViewModel;
import com.example.phoenixtree.viewmodel.SceneViewModel;
import com.example.phoenixtree.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by ej on 9/5/2017.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ParticipateViewModel.class)
    abstract ViewModel bindParticipateViewModel(ParticipateViewModel participateViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SceneViewModel.class)
    abstract ViewModel bindSceneViewModel(SceneViewModel sceneViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
