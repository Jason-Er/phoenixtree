package com.example.phoenixtree.util.sceneRecyclerView;

import android.graphics.Rect;
import android.opengl.Matrix;
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
    private float[] VPMatrix = {
            1.6875f,
            0.0f,
            0.0f,
            0.0f,
            0.0f,
            0.23138356f,
            1.2185816f,
            0.99702126f,
            0.0f,
            2.9910638f,
            -0.094267376f,
            -0.07712785f,
            0.0f,
            -8.262117f,
            13.207706f,
            16.26085f};
    private Rect stageViewRect = new Rect();
    private void layoutItemView(RecyclerView.Recycler recycler) {
        for(int i=0; i< getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            int viewType = getItemViewType(view);
            switch (SceneViewType.values()[viewType]) {
                case STAGE:
                    addView(view);
                    stageViewRect = layoutStageItemView(view);
                    break;
                case ROLE:
                    float[] roleVertices = ((RoleCardView) view).getRoleVertices();
                    Rect roleRect = calcRoleView(roleVertices, stageViewRect);
                    ((RoleCardView) view).setRealHeight(roleRect.height());
                    ((RoleCardView) view).setRealWidth(roleRect.width());
                    addView(view);
                    layoutRoleItemView(view, roleRect);
                    break;
                case LINE:
                    addView(view);
                    layoutLineItemView(view);
                    break;
            }
        }
    }

    private Rect calcRoleView(float[] roleVertices, Rect stageView) {
        Rect roleViewSize = new Rect();

        float[] roleVerticesTrans = new float[16];
        for(int i=0; i<4; i++) {
            Matrix.multiplyMV(roleVerticesTrans, i*4, VPMatrix, 0, roleVertices, i*4);
            roleVerticesTrans[i*4] /= roleVerticesTrans[i*4 + 3];
            roleVerticesTrans[i*4 + 1] /= roleVerticesTrans[i*4 + 3];
            roleVerticesTrans[i*4 + 2] /= roleVerticesTrans[i*4 + 3];
        }

        float[] roleView = new float[8];
        for(int i=0; i<4; i++) {
            roleView[i * 2] = (roleVerticesTrans[i * 4]+ 1) * (stageView.width() / 2) + stageView.left;
            roleView[i * 2 + 1] = -(roleVerticesTrans[i * 4 + 1]  + 1) * (stageView.height() / 2) + stageView.height() + stageView.top;
        }

        roleViewSize.left = (int)roleView[0];
        roleViewSize.top = (int)roleView[1];
        roleViewSize.right = roleViewSize.left;
        roleViewSize.bottom = roleViewSize.top;
        for(int i=1; i<3; i++) {
            if (roleViewSize.left > (int)roleView[i*2])
                roleViewSize.left = (int)roleView[i*2];
            if (roleViewSize.top > (int)roleView[i*2+1])
                roleViewSize.top = (int)roleView[i*2+1];
            if (roleViewSize.right < (int)roleView[i*2])
                roleViewSize.right = (int)roleView[i*2];
            if (roleViewSize.bottom < (int)roleView[i*2+1])
                roleViewSize.bottom = (int)roleView[i*2+1];
        }

        return roleViewSize;
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

    private void layoutRoleItemView(View view, Rect roleView) {
        measureChildWithMargins(view, 0, 0);
        layoutDecorated(view, roleView.left, roleView.top, roleView.right, roleView.bottom);
    }

    private void layoutLineItemView(View view) {

    }
}
