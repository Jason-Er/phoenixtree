package com.example.phoenixtree.util.browseRecyclerView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.util.commonInterface.OnClickCallBack;

/**
 * Created by ej on 10/27/2017.
 */

public class StagePlayInfoCardView extends CardView {

    private final String TAG = StagePlayInfoCardView.class.getName();

    private StagePlayEntity stagePlayEntity;

    public StagePlayInfoCardView(Context context) {
        super(context);
    }

    public StagePlayInfoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StagePlayInfoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStagePlayEntity(StagePlayEntity stagePlayEntity) {
        this.stagePlayEntity = stagePlayEntity;
    }

    public void setClickCallback(final OnClickCallBack clickCallback) {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "setClickCallback");
                clickCallback.onClick(stagePlayEntity.id);
            }
        });

    }
}
