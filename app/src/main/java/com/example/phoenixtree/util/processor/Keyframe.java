package com.example.phoenixtree.util.processor;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Map;

/**
 * Created by ej on 8/22/2017.
 */

public class Keyframe {

    public class Stage {
        Bitmap floor;
        Bitmap setting;
        Bitmap surrouding;
        public float[] stageVertices;
    }

    public class Role {
        public String name;
        public float[] roleVertices;
    }

    public class Line {
        public String line;
        public float[] linePosition;
    }

    public Stage stage;
    public List<Role> roles;
    public Map<Role, Line> mapLines;

}
