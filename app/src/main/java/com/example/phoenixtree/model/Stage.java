package com.example.phoenixtree.model;

import android.graphics.Bitmap;

/**
 * Created by ej on 8/23/2017.
 */

public class Stage {
    Bitmap floor;
    Bitmap setting;
    Bitmap surrouding;

    float[] stageVertices;

    public float[] getStageVertices() {
        return stageVertices;
    }

    public void setStageVertices(float[] stageVertices) {
        this.stageVertices = stageVertices;
    }
}
