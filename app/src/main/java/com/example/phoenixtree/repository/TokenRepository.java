package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.remote.ApiResponse;
import com.example.phoenixtree.dataservice.remote.WebService;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.dto.AccountCredentials;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 1/15/2018.
 */
@Singleton
public class TokenRepository {
    private final WebService webservice;
    private final AppExecutors appExecutors;
    private final SharedPreferences sharedPreferences;

    private MediatorLiveData<String> tokenLiveData;

    @Inject
    public TokenRepository(WebService webservice,
                           SharedPreferences sharedPreferences,
                           AppExecutors appExecutors) {
        this.webservice = webservice;
        this.sharedPreferences = sharedPreferences;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<String>> loadToken(final AccountCredentials credentials) {
        return new NetworkBoundResource<String, String>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull String token) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", token);
                editor.commit();
            }

            @Override
            protected boolean shouldFetch(@Nullable String data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<String> loadFromDb() {
                String name = sharedPreferences.getString("token", "");
                // TODO: 1/15/2018 tell if token is expired otherwise return false; do not need fetch
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<String>> createCall() {
                return webservice.loadToken(credentials);
            }
        }.asLiveData();
    }
}
