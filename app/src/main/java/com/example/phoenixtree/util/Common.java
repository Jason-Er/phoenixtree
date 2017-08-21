package com.example.phoenixtree.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ej on 8/21/2017.
 */

public class Common {
    static public void addFragment(int containerViewId, Fragment fragment, FragmentActivity activity) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }
}
