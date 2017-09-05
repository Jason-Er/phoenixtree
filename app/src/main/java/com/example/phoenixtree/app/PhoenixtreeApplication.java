package com.example.phoenixtree.app;

import android.app.Application;

import com.example.phoenixtree.di.AppComponent;
import com.example.phoenixtree.di.DaggerAppComponent;

/**
 * Created by ej on 9/4/2017.
 */

public class PhoenixtreeApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
