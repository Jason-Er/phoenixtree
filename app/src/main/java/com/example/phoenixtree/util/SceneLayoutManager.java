package com.example.phoenixtree.util;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phoenixtree.R;

/**
 * Created by ej on 8/22/2017.
 */

public class SceneLayoutManager extends RecyclerView.LayoutManager{

    final private static String TAG = SceneLayoutManager.class.getName();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.i(TAG, "SceneLayoutManager onLayoutChildren");
        //We have nothing to show for an empty data set but clear any existing views
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {
            //Nothing to do during prelayout when empty
            return;
        }
        detachAndScrapAttachedViews(recycler);

        // layout according to position
        layoutItemView(recycler);
    }

    private void layoutItemView(RecyclerView.Recycler recycler) {
        Rect stageViewRect = new Rect();
        RectF stageSurfaceRect = new RectF();
        for(int i=0; i< getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            int viewType = getItemViewType(view);
            switch (SceneViewType.values()[viewType]) {
                case STAGE:
                    stageSurfaceRect = ((StageCardView) view).getStageSurfaceSize();
                    addView(view);
                    stageViewRect = layoutStageItemView(view);
                    break;
                case ROLE:
                    calcViewSize(stageViewRect, stageSurfaceRect, (RoleCardView) view);
                    addView(view);
                    layoutRoleItemView(view);
                    break;
                case LINE:
                    addView(view);
                    layoutLineItemView(view);
                    break;
            }
        }
    }

    private void calcViewSize(Rect stageViewRect, RectF stageSurfaceRect, RoleCardView view) {
        int width = stageViewRect.width();
        float surfaceWidth = stageSurfaceRect.width();
        float scale = width / surfaceWidth ;
        RectF roleFigure = view.getRoleFigure();
        int roleWidth = (int)(roleFigure.width() * scale);
        int roleHeight = (int)(roleFigure.height() * scale);
        view.setRealHeight(roleHeight);
        view.setRealWidth(roleWidth);
    }

    private Rect layoutStageItemView(View view) {

        measureChildWithMargins(view, 0, 0);

        int width = getDecoratedMeasuredWidth(view);
        int height = getDecoratedMeasuredHeight(view);

        int parentWidth = ((View)view.getParent()).getWidth();
        int parentHeight = ((View)view.getParent()).getHeight();

        layoutDecorated(view, (parentWidth-width)/2, (parentHeight-height)/2, (parentWidth+width)/2, (parentHeight+height)/2);

        return new Rect(0,0,width, height);
    }

    private void layoutRoleItemView(View view) {

        float[] vertices = ((RoleCardView)view).getVertices();

        measureChildWithMargins(view, 0, 0);

        int width = getDecoratedMeasuredWidth(view);
        int height = getDecoratedMeasuredHeight(view);

        layoutDecorated(view, 0, 0, width, height);

    }

    private void layoutLineItemView(View view) {

    }
}
