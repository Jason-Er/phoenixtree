package com.example.phoenixtree.di;

import android.app.Application;

import com.example.phoenixtree.app.PhoenixtreeApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by ej on 9/4/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }

    void inject(PhoenixtreeApplication app);
}
