package com.example.phoenixtree.app;

import android.app.Application;
import android.content.Context;

import com.example.phoenixtree.di.ApplicationComponent;
import com.example.phoenixtree.di.ApplicationModule;
import com.example.phoenixtree.di.DaggerApplicationComponent;

/**
 * Created by ej on 9/4/2017.
 */

public class PhoenixtreeApplication extends Application {
    protected ApplicationComponent applicationComponent;

    public static PhoenixtreeApplication get(Context context) {
        return (PhoenixtreeApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
