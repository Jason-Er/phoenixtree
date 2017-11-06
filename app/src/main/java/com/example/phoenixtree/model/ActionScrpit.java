package com.example.phoenixtree.model;

import java.util.List;

/**
 * Created by ej on 9/14/2017.
 */

public class ActionScrpit {

    public class Animate {
        public String attributeName;
        public float from[];
        public float to[];
        public float begin;
        public float dur;
        public String calcMode;
    }

    public class RoleFigure {
        public float width;
        public float height;
    }

    public class Role {
        public String name;
        public RoleFigure figure;
        public List<Animate> animateList;
    }

    public String sceneId;
    public List<Role> roleList;
}
