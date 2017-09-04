package com.example.phoenixtree.di;

import android.app.Application;
import android.content.Context;

import com.example.phoenixtree.app.PhoenixtreeApplication;
import com.example.phoenixtree.app.SharedPrefsHelper;
import com.example.phoenixtree.di.label.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ej on 9/4/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PhoenixtreeApplication phoenixtreeApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    SharedPrefsHelper getPreferenceHelper();

}
