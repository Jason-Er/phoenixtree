package com.example.phoenixtree.util;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by ej on 8/25/2017.
 */

public class RoleCardView extends CardView {

    private int realHeight;
    private int realWidth;

    private float[] roleVertices;

    public RoleCardView(Context context) {
        super(context);
    }

    public RoleCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float[] getRoleVertices() {
        return roleVertices;
    }

    public void setRoleVertices(float[] roleVertices) {
        this.roleVertices = roleVertices;
    }

    public int getRealHeight() {
        return realHeight;
    }

    public void setRealHeight(int realHeight) {
        this.realHeight = realHeight;
    }

    public int getRealWidth() {
        return realWidth;
    }

    public void setRealWidth(int realWidth) {
        this.realWidth = realWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(
                MeasureSpec.makeMeasureSpec(realWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(realHeight, MeasureSpec.EXACTLY));
    }
}
