package com.example.phoenixtree.app;

import android.app.Activity;

import com.example.phoenixtree.view.main.MainActivity;
import com.example.phoenixtree.view.main.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by ej on 9/7/2017.
 */

@Module
public abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(MainActivityComponent.Builder builder);
}
