package com.example.phoenixtree.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by ej on 12/4/2017.
 */

public class UICommon {
    public static boolean isViewContains(View view, int rx, int ry) {
        //int[] l = new int[2];
        //view.getLocationOnScreen(l);
        Rect rect = new Rect();//new Rect(l[0], l[1], l[0] + view.getWidth(), l[1] + view.getHeight());
        view.getGlobalVisibleRect(rect);
        return rect.contains(rx, ry);
    }
}
