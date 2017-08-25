package com.example.phoenixtree.Model;

import android.graphics.Bitmap;
import android.graphics.RectF;

/**
 * Created by ej on 8/23/2017.
 */

public class Stage {
    Bitmap floor;
    Bitmap setting;
    Bitmap surrouding;

    RectF stageSurfaceSize;

    public RectF getStageSurfaceSize() {
        return stageSurfaceSize;
    }

    public void setStageSurfaceSize(RectF stageSurfaceSize) {
        this.stageSurfaceSize = stageSurfaceSize;
    }
}
