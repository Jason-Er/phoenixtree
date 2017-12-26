package com.example.phoenixtree.view.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.example.phoenixtree.R;
import com.example.phoenixtree.util.UICommon;

/**
 * Created by ej on 12/26/2017.
 */

public class DockPanel extends CoordinatorLayout{

    public DockPanel(Context context) {
        super(context);
    }

    public DockPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DockPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View view = findViewById(R.id.main_container);
        UICommon.setViewMargin(view, true, 0, 0, 48, 0);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
