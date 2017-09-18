package com.example.phoenixtree.util.processor;

import java.util.List;

/**
 * Created by ej on 9/14/2017.
 */

public class Role {
    private String name;
    private RoleFigure figure;
    private List<Animate> animateList;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setFigure(RoleFigure figure) {
        this.figure = figure;
    }
    public RoleFigure getFigure() {
        return figure;
    }

    public List<Animate> getAnimateList() {
        return animateList;
    }

    public void setAnimateList(List<Animate> animateList) {
        this.animateList = animateList;
    }
}
