package com.example.phoenixtree.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneLayoutManager extends RecyclerView.LayoutManager{

    final static String TAG = SceneLayoutManager.class.getName();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.i(TAG, "SceneLayoutManager onLayoutChildren");
        detachAndScrapAttachedViews(recycler);
        int offsetY = 0;
        for(int i=0; i< getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            offsetY += height;
        }
    }
}
