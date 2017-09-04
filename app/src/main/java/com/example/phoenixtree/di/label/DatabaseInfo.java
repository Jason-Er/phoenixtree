package com.example.phoenixtree.di.label;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ej on 9/4/2017.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseInfo {
}
