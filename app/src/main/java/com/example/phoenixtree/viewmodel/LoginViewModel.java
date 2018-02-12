package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.dto.AccountCredentials;
import com.example.phoenixtree.repository.TokenRepository;
import com.example.phoenixtree.util.AbsentLiveData;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 1/15/2018.
 */
@Singleton
public class LoginViewModel extends ViewModel {
    private final MutableLiveData<AccountCredentials> credentialsLiveData = new MutableLiveData<>();
    public final LiveData<Resource<Boolean>> loginStatus;
    @Inject
    public LoginViewModel(final TokenRepository repository) {
        loginStatus = Transformations.switchMap(credentialsLiveData, new Function<AccountCredentials, LiveData<Resource<Boolean>>>() {
            @Override
            public LiveData<Resource<Boolean>> apply(AccountCredentials input) {
                if(credentialsLiveData == null) {
                    return AbsentLiveData.create();
                } else {
                    return repository.loadToken(input);
                }
            }
        });
    }

    public void setCredentialsLiveData(AccountCredentials credentials) {
        if (Objects.equals(this.credentialsLiveData.getValue(), credentials)) {
            return;
        }
        this.credentialsLiveData.setValue(credentials);
    }
}
