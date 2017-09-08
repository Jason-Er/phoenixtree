package com.example.phoenixtree.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
                .baseUrl("https://api.github.com/")
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
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs(Application application) {
        return application.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
