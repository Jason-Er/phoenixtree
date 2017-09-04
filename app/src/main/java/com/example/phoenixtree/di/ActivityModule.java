package com.example.phoenixtree.di;

import android.app.Activity;
import android.content.Context;

import com.example.phoenixtree.di.label.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ej on 9/4/2017.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
