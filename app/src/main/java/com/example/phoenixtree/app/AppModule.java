package com.example.phoenixtree.app;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.phoenixtree.dataservice.entity.StageRoleEntity;
import com.example.phoenixtree.dataservice.entity.UserEntity;
import com.example.phoenixtree.dataservice.local.AppDatabase;
import com.example.phoenixtree.dataservice.local.LineEntityDao;
import com.example.phoenixtree.dataservice.local.PlayEntityDao;
import com.example.phoenixtree.dataservice.local.RoleEntityDao;
import com.example.phoenixtree.dataservice.local.SceneEntityDao;
import com.example.phoenixtree.dataservice.local.StageEntityDao;
import com.example.phoenixtree.dataservice.local.StageLineEntityDao;
import com.example.phoenixtree.dataservice.local.StageLineLocalEntityDao;
import com.example.phoenixtree.dataservice.local.StagePlayEntityDao;
import com.example.phoenixtree.dataservice.local.StageRoleEntityDao;
import com.example.phoenixtree.dataservice.local.StageSceneEntityDao;
import com.example.phoenixtree.dataservice.local.UserEntityDao;
import com.example.phoenixtree.dataservice.remote.WebService;
import com.example.phoenixtree.di.label.DatabaseInfo;
import com.example.phoenixtree.util.LiveDataCallAdapterFactory;
import com.example.phoenixtree.view.main.MainActivityComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ej on 9/4/2017.
 */
@Module(includes = ViewModelModule.class, subcomponents = {MainActivityComponent.class})
class AppModule {

    @Singleton
    @Provides
    WebService provideWebService() {
        return new Retrofit.Builder()
                .baseUrl("http://192.1.112.31:8448/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(WebService.class);
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SharedPreferences provideSharedPrefs(Application application) {
        return application.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }

    @Singleton @Provides
    AppDatabase provideDb(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }

    @Singleton @Provides
    UserEntityDao provideUserEntityDao(AppDatabase db) {
        return db.userEntityDao();
    }

    @Singleton @Provides
    RoleEntityDao provideRoleEntityDao(AppDatabase db) {
        return db.roleEntityDao();
    }

    @Singleton @Provides
    PlayEntityDao providePlayEntityDao(AppDatabase db) {
        return db.playEntityDao();
    }

    @Singleton @Provides
    SceneEntityDao provideSceneEntityDao(AppDatabase db) {
        return db.sceneEntityDao();
    }

    @Singleton @Provides
    LineEntityDao provideLineEntityDao(AppDatabase db) {
        return db.lineEntityDao();
    }

    @Singleton @Provides
    StageEntityDao provideStageEntityDao(AppDatabase db) {
        return db.stageEntityDao();
    }

    @Singleton @Provides
    StageLineEntityDao provideStageLineEntityDao(AppDatabase db) {
        return db.stageLineEntityDao();
    }

    @Singleton @Provides
    StageLineLocalEntityDao provideStageLineLocalEntityDao(AppDatabase db) {
        return db.stageLineLocalEntityDao();
    }

    @Singleton @Provides
    StagePlayEntityDao provideStagePlayEntityDao(AppDatabase db) {
        return db.stagePlayEntityDao();
    }

    @Singleton @Provides
    StageRoleEntityDao provideStageRoleEntityDao(AppDatabase db) {
        return db.stageRoleEntityDao();
    }

    @Singleton @Provides
    StageSceneEntityDao provideStageSceneEntityDao(AppDatabase db) {
        return db.stageSceneEntityDao();
    }

}
