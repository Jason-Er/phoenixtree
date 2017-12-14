package com.example.phoenixtree.view.bottomBar;

import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.phoenixtree.util.UICommon;

import java.util.Calendar;

/**
 * Created by ej on 12/12/2017.
 */

public class PlayerControlBarBehavior extends CoordinatorLayout.Behavior<PlayerControlBar> {

    private final String TAG = "PlayerControlBarBehavio";
    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;

    private boolean isNeedIntercept = false;

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, PlayerControlBar child, MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(UICommon.isViewContains(child, (int)ev.getX(), (int)ev.getY())) {
                    Log.i(TAG, "onInterceptTouchEvent: MotionEvent.ACTION_DOWN in rect");
                    isNeedIntercept = false;
                } else {
                    Log.i(TAG, "onInterceptTouchEvent: MotionEvent.ACTION_DOWN out rect");
                    isNeedIntercept = true;
                }
                break;
        }
        // return isNeedIntercept;
        return false;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, PlayerControlBar child, MotionEvent ev) {
        Log.i(TAG, "onTouchEvent");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent: MotionEvent.ACTION_DOWN");
                startClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent: MotionEvent.ACTION_UP");
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    Log.i(TAG, "click event has occurred");
                    if (child.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        ObjectAnimator animY = ObjectAnimator.ofFloat(child,
                                View.TRANSLATION_Y, 1* child.getHeight(), 0);
                        animY.setDuration(300);
                        animY.start();
                    } else {
                        ObjectAnimator animY = ObjectAnimator.ofFloat(child,
                                View.TRANSLATION_X, 1* child.getWidth(), 0);
                        animY.setDuration(300);
                        animY.start();
                    }
                }
                break;
        }
        return false;
    }
}
