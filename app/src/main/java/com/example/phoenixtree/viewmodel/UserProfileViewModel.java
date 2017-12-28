package com.example.phoenixtree.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 12/28/2017.
 */
@Singleton
public class UserProfileViewModel extends ViewModel {
    @Inject
    public UserProfileViewModel(final UserRepository repository) {
    }
}
