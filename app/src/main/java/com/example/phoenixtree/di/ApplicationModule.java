package com.example.phoenixtree.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.phoenixtree.di.label.ApplicationContext;
import com.example.phoenixtree.di.label.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 9/4/2017.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application app) {
        application = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
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
    SharedPreferences provideSharedPrefs() {
        return application.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
