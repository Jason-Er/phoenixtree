package com.example.phoenixtree.Model;

import android.graphics.RectF;

/**
 * Created by ej on 8/22/2017.
 */

public class Role {
    String name;
    RectF roleFigure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RectF getRoleFigure() {
        return roleFigure;
    }

    public void setRoleFigure(RectF roleFigure) {
        this.roleFigure = roleFigure;
    }
}
