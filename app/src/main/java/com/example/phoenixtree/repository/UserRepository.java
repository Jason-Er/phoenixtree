package com.example.phoenixtree.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.example.phoenixtree.app.AppExecutors;
import com.example.phoenixtree.dataservice.entity.UserEntity;
import com.example.phoenixtree.dataservice.local.UserEntityDao;
import com.example.phoenixtree.dataservice.remote.ApiResponse;
import com.example.phoenixtree.dataservice.remote.WebService;
import com.example.phoenixtree.model.LoginInfo;
import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.User;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ej on 12/28/2017.
 */
@Singleton
public class UserRepository {
    private final WebService webservice;
    private final AppExecutors appExecutors;
    private final UserEntityDao userEntityDao;

    private MediatorLiveData<User> userLiveData;
    @Inject
    public UserRepository(WebService webservice,
                          UserEntityDao userEntityDao,
                          AppExecutors appExecutors) {
        this.webservice = webservice;
        this.userEntityDao = userEntityDao;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<User>> loadUser(final LoginInfo loginInfo) {
        return new NetworkBoundResource<User, User>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull User item) {
                userEntityDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                if(userLiveData == null) {
                    userLiveData = new MediatorLiveData<>();
                }
                final LiveData<UserEntity> userEntityLiveData = userEntityDao.retrieveByEmailAndPasswordLive(loginInfo.email, loginInfo.password);
                userLiveData.addSource(userEntityLiveData, new Observer<UserEntity>() {
                    @Override
                    public void onChanged(@android.support.annotation.Nullable UserEntity userEntity) {
                        userLiveData.removeSource(userEntityLiveData);
                        if(userEntity != null) {
                            User user = new User();
                            user.id = userEntity.id;
                            user.email = userEntity.email;
                            user.firstName = userEntity.firstName;
                            user.lastName = userEntity.lastName;
                            user.password = userEntity.password;
                            userLiveData.setValue(user);
                        } else {
                            userLiveData.setValue(null);
                        }
                    }
                });
                return userLiveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<User>> createCall() {
                return webservice.loadUser(loginInfo);
            }
        }.asLiveData();
    }
}
