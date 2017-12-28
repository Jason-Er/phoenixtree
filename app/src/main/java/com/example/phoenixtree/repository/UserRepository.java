package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;

import com.example.phoenixtree.dataservice.remote.WebService;
import com.example.phoenixtree.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 12/28/2017.
 */
@Singleton
public class UserRepository {
    private final WebService webservice;
    @Inject
    public UserRepository(WebService webservice) {
        this.webservice = webservice;
    }
    /*
    public LiveData<User> getUser(int userId) {

    }
    */
}
