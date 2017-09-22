package com.example.phoenixtree.util;

import android.arch.lifecycle.LiveData;

/**
 * Created by ej on 9/22/2017.
 */

public class AbsentLiveData extends LiveData {
    private AbsentLiveData() {
        postValue(null);
    }
    public static <T> LiveData<T> create() {
        //noinspection unchecked
        return new AbsentLiveData();
    }
}

