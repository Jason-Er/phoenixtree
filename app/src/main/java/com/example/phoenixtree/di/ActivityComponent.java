package com.example.phoenixtree.di;

import com.example.phoenixtree.di.label.PerActivity;
import com.example.phoenixtree.view.MainActivity;

import dagger.Component;

/**
 * Created by ej on 9/4/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
