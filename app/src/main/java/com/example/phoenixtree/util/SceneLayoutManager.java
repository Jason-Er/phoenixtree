package com.example.phoenixtree.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.phoenixtree.Model.Position3D;
import com.example.phoenixtree.R;

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
        int offsetY = 0;
        for(int i=0; i< getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            int viewType = getItemViewType(view);
            switch (SceneViewType.values()[viewType]) {
                case STAGE:
                    break;
                case ROLE:
                    TextView textView = (TextView)view.findViewById(R.id.role_view_position_x);
                    float x = Float.parseFloat(textView.getText().toString());
                    textView = (TextView)view.findViewById(R.id.role_view_position_y);
                    float y = Float.parseFloat(textView.getText().toString());
                    textView = (TextView)view.findViewById(R.id.role_view_position_z);
                    float z = Float.parseFloat(textView.getText().toString());
                    break;
                case LINE:
                    break;
            }

            addView(view);

            measureChildWithMargins(view, 0, 0);

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            offsetY += height;
        }
    }
}
