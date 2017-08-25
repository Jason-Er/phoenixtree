package com.example.phoenixtree.util;

import android.content.Context;
import android.graphics.RectF;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by ej on 8/25/2017.
 */

public class RoleCardView extends CardView {

    private int realHeight;
    private int realWidth;

    private RectF roleFigure;
    private float[] vertices;

    public RoleCardView(Context context) {
        super(context);
    }

    public RoleCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public RectF getRoleFigure() {
        return roleFigure;
    }

    public void setRoleFigure(RectF roleFigure) {
        this.roleFigure = roleFigure;
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
