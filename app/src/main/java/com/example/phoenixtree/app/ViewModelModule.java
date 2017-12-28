package com.example.phoenixtree.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.phoenixtree.di.label.ViewModelKey;
import com.example.phoenixtree.viewmodel.BrowseViewModel;
import com.example.phoenixtree.viewmodel.StagePlayViewModel;
import com.example.phoenixtree.viewmodel.UserProfileViewModel;
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
    @ViewModelKey(StagePlayViewModel.class)
    abstract ViewModel bindParticipateViewModel(StagePlayViewModel participateViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BrowseViewModel.class)
    abstract ViewModel bindBrowseViewModel(BrowseViewModel browseViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    abstract ViewModel bindUserProfileViewModel(UserProfileViewModel userProfileViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
