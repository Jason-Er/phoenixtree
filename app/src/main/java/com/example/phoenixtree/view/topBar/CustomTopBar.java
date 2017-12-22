package com.example.phoenixtree.view.topBar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by ej on 12/22/2017.
 */
@CoordinatorLayout.DefaultBehavior(CustomTopBarBehavior.class)
public class CustomTopBar extends RelativeLayout {
    public CustomTopBar(Context context) {
        super(context);
    }

    public CustomTopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTopBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
