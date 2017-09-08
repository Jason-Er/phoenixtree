package com.example.phoenixtree.di.label;

import android.arch.lifecycle.LifecycleFragment;
import android.support.v4.app.Fragment;

import java.lang.annotation.Target;

import dagger.MapKey;
import dagger.internal.Beta;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by ej on 9/8/2017.
 */
@Beta
@MapKey
@Target(METHOD)
public @interface LifecycleFragmentKey {
    Class<? extends LifecycleFragment> value();
}
