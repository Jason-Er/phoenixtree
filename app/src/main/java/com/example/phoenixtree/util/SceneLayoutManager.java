package com.example.phoenixtree.util;

import android.content.res.Configuration;
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

    final private static float STAGE_RATIO = 16f / 9f;
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
        for(int i=0; i< getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            int viewType = getItemViewType(view);
            addView(view);
            switch (SceneViewType.values()[viewType]) {
                case STAGE:
                    layoutStageItemView(view);
                    break;
                case ROLE:
                    layoutRoleItemView(view);
                    break;
                case LINE:
                    layoutLineItemView(view);
                    break;
            }
        }
    }

    private void layoutStageItemView(View view) {

        measureChildWithMargins(view, 0, 0);

        int width = getDecoratedMeasuredWidth(view);
        int height = getDecoratedMeasuredHeight(view);

        layoutDecorated(view, 0, 0, width, height);
        /*
        int width = getDecoratedMeasuredWidth(view);
        int height = (int) (width / STAGE_RATIO);//getDecoratedMeasuredHeight(view);
        int parentHeight = ((View)view.getParent()).getHeight();

        if(view.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                && height > parentHeight ) {

            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.width = (int) (parentHeight * STAGE_RATIO);
            view.setLayoutParams(lp);

            layoutDecorated(view, (int)(width - parentHeight * STAGE_RATIO) / 2, 0, (int)(width + parentHeight * STAGE_RATIO) / 2, parentHeight);

        } else {

            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.height = height;
            view.setLayoutParams(lp);

            layoutDecorated(view, 0, (parentHeight - height)/2, width, (parentHeight + height)/2);
        }
        */
    }

    private void layoutRoleItemView(View view) {

        TextView textView = (TextView)view.findViewById(R.id.role_view_position_x);
        float x = Float.parseFloat(textView.getText().toString());
        textView = (TextView)view.findViewById(R.id.role_view_position_y);
        float y = Float.parseFloat(textView.getText().toString());
        textView = (TextView)view.findViewById(R.id.role_view_position_z);
        float z = Float.parseFloat(textView.getText().toString());

        measureChildWithMargins(view, 0, 0);

        int width = getDecoratedMeasuredWidth(view);
        int height = getDecoratedMeasuredHeight(view);

        layoutDecorated(view, width, height, width*2, height*2);

    }

    private void layoutLineItemView(View view) {

    }
}
