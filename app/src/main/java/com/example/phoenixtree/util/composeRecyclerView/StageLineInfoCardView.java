package com.example.phoenixtree.util.composeRecyclerView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.example.phoenixtree.model.script.StageLine;

/**
 * Created by ej on 12/1/2017.
 */

public class StageLineInfoCardView extends CardView {

    private StageLine stageLine;

    private int indexInViewGroup;

    public StageLineInfoCardView(Context context) {
        super(context);
    }

    public StageLineInfoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StageLineInfoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StageLine getStageLine() {
        return stageLine;
    }

    public void setStageLine(StageLine stageLine) {
        this.stageLine = stageLine;
    }

    public int getIndexInViewGroup() {
        return indexInViewGroup;
    }

    public void setIndexInViewGroup(int indexInViewGroup) {
        this.indexInViewGroup = indexInViewGroup;
    }
}
