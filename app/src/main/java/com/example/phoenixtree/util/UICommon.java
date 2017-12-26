package com.example.phoenixtree.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

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

    public static ViewGroup.LayoutParams setViewMargin(@NonNull View view, boolean isDp, int left, int right, int top, int bottom) {
        int leftPx = left;
        int rightPx = right;
        int topPx = top;
        int bottomPx = bottom;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }

        //根据DP与PX转换计算值
        if (isDp) {
            leftPx = (int) pxFromDp(view.getContext(), left);
            rightPx = (int) pxFromDp(view.getContext(), right);
            topPx = (int) pxFromDp(view.getContext(), top);
            bottomPx = (int) pxFromDp(view.getContext(), bottom);
        }
        //设置margin
        marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);
        view.setLayoutParams(marginParams);
        return marginParams;
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
