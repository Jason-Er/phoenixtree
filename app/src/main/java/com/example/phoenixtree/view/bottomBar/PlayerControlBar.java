package com.example.phoenixtree.view.bottomBar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by ej on 12/12/2017.
 */

@CoordinatorLayout.DefaultBehavior(PlayerControlBarBehavior.class)
public class PlayerControlBar extends LinearLayout {

    final String TAG = "PlayerControlBar";

    public PlayerControlBar(Context context) {
        super(context);
    }

    public PlayerControlBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerControlBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure");
        /*
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else {

            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams((int)getResources().getDimension(R.dimen.imageview_width), ViewGroup.LayoutParams.MATCH_PARENT);
            lParams.gravity = Gravity.RIGHT;
            this.setLayoutParams(lParams);
        }
        */
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
