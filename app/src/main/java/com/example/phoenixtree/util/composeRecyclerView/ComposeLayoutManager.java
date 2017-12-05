package com.example.phoenixtree.util.composeRecyclerView;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.example.phoenixtree.model.StageLine;
import com.example.phoenixtree.util.SetOpt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ej on 12/1/2017.
 */

public class ComposeLayoutManager extends RecyclerView.LayoutManager {

    private final String TAG = ComposeLayoutManager.class.getName();

    private Point pointLeftTop = new Point();

    private List<Rect> layoutRectInfo;
    private HashMap layoutRowInfo;
    private List<Integer> indexSet;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        //We have nothing to show for an empty data set but clear any existing views
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {
            //Nothing to do during prelayout when empty
            return;
        }

        //Clear all attached views into the recycle bin
        detachAndScrapAttachedViews(recycler);

        fillVisibleChildren(recycler, MoveDirection.NONE);
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {

        if(getChildCount() == 0) {
            return 0;
        }

        int delta = 0;
        int indexShowFirst = indexSet.get(0);
        int indexShowLast = indexSet.get(indexSet.size() -1);

        Rect rectTopView =  measureViewWithMarginsByIndex(indexShowFirst, layoutRowInfo, layoutRectInfo, recycler);
        Rect rectBottomView =  measureViewWithMarginsByIndex(indexShowLast, layoutRowInfo, layoutRectInfo, recycler);

        boolean isFirstItemIn = indexShowFirst == 0;
        boolean isLastItemIn = indexShowLast + 1 >= getItemCount();
        if(dx > 0) { // Contents are scrolling left
            //Check right bound
            int rightOffset = getHorizontalSpace() - (rectBottomView.right - pointLeftTop.x) + getPaddingRight();
            if(isLastItemIn) {
                //If we've reached the last column, enforce limits
                delta = Math.max(-dx, rightOffset);
            } else {
                if(-dx > rightOffset) {
                    delta = -dx;
                } else {
                    int index = 0;
                    int width = 0;
                    while ( -dx < rightOffset ) {
                        index ++;
                        if(indexShowLast + index < getItemCount()) {
                            Rect rect = measureViewWithMarginsByIndex(indexShowLast + index, layoutRowInfo, layoutRectInfo, recycler);
                            width += rect.width();
                            rightOffset = getHorizontalSpace() - (rectBottomView.right - pointLeftTop.x) + getPaddingRight() - width;
                        } else {
                            rightOffset = getHorizontalSpace() - (rectBottomView.right - pointLeftTop.x) + getPaddingRight() - width;
                            delta = Math.max(-dx, rightOffset);
                            break;
                        }
                        delta = -dx;
                    }
                }

            }
        } else { // Contents are scrolling right
            //Check left bound
            int leftOffset = pointLeftTop.x - rectTopView.left + getPaddingLeft();
            if(isFirstItemIn) {
                delta = Math.min(-dx, pointLeftTop.x);
            } else {
                if( -dx < leftOffset) {
                    delta = -dx;
                } else {
                    int index = 0;
                    int width = 0;
                    while (-dx > leftOffset) {
                        index ++;
                        if(indexShowFirst - index >= 0) {
                            Rect rect = measureViewWithMarginsByIndex(indexShowFirst - index, layoutRowInfo, layoutRectInfo, recycler);
                            width += rect.width();
                            leftOffset += width;
                        } else {
                            delta = Math.min(-dx, pointLeftTop.x);
                            break;
                        }
                        delta = -dx;
                    }
                }
            }
        }
        offsetChildrenHorizontal(delta);
        pointLeftTop.x += -delta;
        fillVisibleChildren(recycler, MoveDirection.LEFT);
        return -delta;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (getChildCount() == 0) {
            return 0;
        }

        int delta = 0;

        return -delta;
    }

    @Override
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        removeAllViews();
    }

    private Rect measureViewWithMarginsByIndex(int index, HashMap layoutRowInfo, List<Rect> layoutRectInfo, RecyclerView.Recycler recycler) {
        Rect rect;
        if(!layoutRectInfo.isEmpty() && layoutRectInfo.size() > index) {
            rect = layoutRectInfo.get(index);
        } else {
            StageLineInfoCardView view = (StageLineInfoCardView) recycler.getViewForPosition(index);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            removeView(view);

            StageLine stageLine = view.getStageLine();

            int rowTop;
            if( layoutRowInfo.containsKey(stageLine.stageRoleId) ) {
                rowTop = (int)layoutRowInfo.get(stageLine.stageRoleId);
            } else {
                // TODO: 11/27/2017 'height' here need further refactoring
                rowTop = height * layoutRowInfo.size();
                layoutRowInfo.put(stageLine.stageRoleId, rowTop);
            }

            rect = new Rect();
            rect.left = getLayoutInfoLastViewRectLeft(layoutRectInfo);
            rect.top = rowTop;
            rect.right = rect.left + width;
            rect.bottom = rowTop + height;
            layoutRectInfo.add(rect);
            Log.i(TAG, "add view info to layoutRectInfo index: " + index);
        }
        return rect;
    }

    private int getLayoutInfoLastViewRectLeft(List<Rect> layoutInfo) {
        int left = 0;
        for(Rect rect: layoutInfo) {
            left += rect.width();
        }
        return left;
    }

    // return all views indexes within recycler view
    private List<Integer> findIndexSetWithinView(Point pointLeftTop, int viewWidth, int viewHeight, List<Rect> layoutInfo, RecyclerView.Recycler recycler) {
        List<Integer> indexes = new ArrayList<>();
        Rect rect;
        Rect rectView = new Rect();
        rectView.top = pointLeftTop.y;
        rectView.left = pointLeftTop.x;
        rectView.right = pointLeftTop.x + viewWidth;
        rectView.bottom = pointLeftTop.y + viewHeight;
        for(int index = 0; index < layoutInfo.size(); index ++) {
            rect = layoutInfo.get(index);
            if(Rect.intersects(rectView, rect)) {
                indexes.add(index);
            }
        }
        /*
        for(int index = layoutInfo.size(); index < getItemCount(); index ++) {
            rect = measureViewWithMarginsByIndex(index, layoutRowInfo, layoutRectInfo, recycler);
            if(Rect.intersects(rectView, rect)) {
                indexes.add(index);
            } else {
                break;
            }
        }
        */
        return indexes;
    }

    private enum MoveDirection {
        LEFT, RIGHT, UP, DOWN, NONE
    }

    private void fillVisibleChildren(RecyclerView.Recycler recycler, MoveDirection direction){

        if (getChildCount() != 0) {

            SparseArray viewCache = new SparseArray(getChildCount());

            List<Integer> indexes = findIndexSetWithinView(pointLeftTop, getHorizontalSpace(), getVerticalSpace(), layoutRectInfo, recycler);
            List<Integer> toRemove = SetOpt.diff(indexSet, indexes);

            //Cache all views by their existing position, before updating counts
            for (int i=0; i < getChildCount(); i++) {
                final StageLineInfoCardView child = (StageLineInfoCardView) getChildAt(i);
                viewCache.put(child.getIndexInViewGroup(), child);
            }

            //Temporarily detach all views.
            // Views we still need will be added back at the proper index.
            for (int i=0; i < viewCache.size(); i++) {
                detachView((View) viewCache.valueAt(i));
            }

            // remove the views that do not in recycler view
            for(Integer index: toRemove) {
                recycler.recycleView((View) viewCache.get(index));
            }

            List<Integer> remains = SetOpt.intersect(indexSet, indexes);

            for(Integer index: remains) {
                attachView((View) viewCache.get(index));
            }

            // add needed views to recycler view
            List<Integer> toAdd = SetOpt.diff(indexes, indexSet);
            for(Integer index: toAdd) {
                View view = recycler.getViewForPosition(index);
                addView(view);
                measureChildWithMargins(view, 0, 0);
                Rect rect = measureViewWithMarginsByIndex(index, layoutRowInfo, layoutRectInfo, recycler);
                layoutDecorated(view, rect.left-pointLeftTop.x, rect.top-pointLeftTop.y, rect.right-pointLeftTop.x, rect.bottom-pointLeftTop.y);
            }
            // now views within the recycler view
            indexSet = indexes;

        } else {
            int rightOffset = 0;
            // int bottomOffset = 0;
            int index = 0;
            indexSet = new ArrayList<>();
            layoutRectInfo = new ArrayList<>();
            layoutRowInfo = new HashMap();
            Rect rect;
            while (rightOffset <= getHorizontalSpace() && index < getItemCount()) {
                Log.i(TAG, "index: " + index + " rightOffset: " + rightOffset);
                indexSet.add(index);
                rect = measureViewWithMarginsByIndex(index, layoutRowInfo, layoutRectInfo, recycler);
                View view = recycler.getViewForPosition(index);
                addView(view, index);
                measureChildWithMargins(view, 0, 0);
                layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom);
                rightOffset += rect.width();
                // bottomOffset += rect.height();
                index ++;
            }
        }
    }

    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

}

