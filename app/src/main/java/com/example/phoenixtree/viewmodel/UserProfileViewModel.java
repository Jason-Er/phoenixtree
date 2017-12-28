package com.example.phoenixtree.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.phoenixtree.model.LoginInfo;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.User;
import com.example.phoenixtree.repository.UserRepository;
import com.example.phoenixtree.util.AbsentLiveData;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 12/28/2017.
 */
@Singleton
public class UserProfileViewModel extends ViewModel {
    private final MutableLiveData<LoginInfo> loginInfo = new MutableLiveData<>();
    public final LiveData<Resource<User>> userProfile;
    @Inject
    public UserProfileViewModel(final UserRepository repository) {
        userProfile = Transformations.switchMap(loginInfo, new Function<LoginInfo, LiveData<Resource<User>>>() {
            @Override
            public LiveData<Resource<User>> apply(LoginInfo input) {
                if(loginInfo == null) {
                    return AbsentLiveData.create();
                } else {
                    return repository.loadUser(input);
                }
            }
        });
    }
    public void setLoginInfo(LoginInfo loginInfo) {
        if (Objects.equals(this.loginInfo.getValue(), loginInfo)) {
            return;
        }
        this.loginInfo.setValue(loginInfo);
    }
}
