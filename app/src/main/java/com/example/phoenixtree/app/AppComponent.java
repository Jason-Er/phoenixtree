package com.example.phoenixtree.app;

import android.app.Application;

import com.example.phoenixtree.view.participate.ParticipateFragment;
import com.example.phoenixtree.view.participate.SceneFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by ej on 9/4/2017.
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }

    void inject(PhoenixtreeApplication app);

}
