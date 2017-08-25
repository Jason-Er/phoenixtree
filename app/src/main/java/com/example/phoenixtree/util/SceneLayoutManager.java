package com.example.phoenixtree.util;

import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
                    calcRoleViewSize(stageViewRect, stageSurfaceRect, (RoleCardView) view);
                    addView(view);
                    layoutRoleItemView(view, stageViewRect);
                    break;
                case LINE:
                    addView(view);
                    layoutLineItemView(view);
                    break;
            }
        }
    }

    private void calcRoleViewSize(Rect stageViewRect, RectF stageSurfaceRect, RoleCardView view) {
        int width = stageViewRect.width();
        float surfaceWidth = stageSurfaceRect.width();
        float scale = width / surfaceWidth ;
        RectF roleFigure = view.getRoleFigure();
        int roleWidth = (int)(roleFigure.width() * scale);
        int roleHeight = (int)(roleFigure.height() * scale);
        view.setRealHeight(roleHeight);
        view.setRealWidth(roleWidth);
    }

    private Rect calcRoleViewPosition(float[] vertices, Rect roleSize, Rect stageView) {
        Rect roleViewPositon = new Rect();
        roleViewPositon.left = stageView.left + (stageView.width() - roleSize.width())/2;
        roleViewPositon.top = stageView.top + (stageView.height() - roleSize.height())/2;
        roleViewPositon.right = stageView.left + (stageView.width() + roleSize.width())/2;
        roleViewPositon.bottom = stageView.top + (stageView.height() + roleSize.height())/2;
        return roleViewPositon;
    }

    private Rect layoutStageItemView(View view) {

        measureChildWithMargins(view, 0, 0);

        int width = getDecoratedMeasuredWidth(view);
        int height = getDecoratedMeasuredHeight(view);

        int parentWidth = ((View)view.getParent()).getWidth();
        int parentHeight = ((View)view.getParent()).getHeight();

        layoutDecorated(view, (parentWidth-width)/2, (parentHeight-height)/2, (parentWidth+width)/2, (parentHeight+height)/2);

        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    private void layoutRoleItemView(View view, Rect stageView) {

        float[] vertices = ((RoleCardView)view).getVertices();

        measureChildWithMargins(view, 0, 0);

        int width = getDecoratedMeasuredWidth(view);
        int height = getDecoratedMeasuredHeight(view);

        Rect rect = calcRoleViewPosition(vertices, new Rect(0,0,width,height), stageView);
        layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom);

    }

    private void layoutLineItemView(View view) {

    }
}
