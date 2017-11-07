package com.example.phoenixtree.model.actionscript;

import org.junit.Test;

/**
 * Created by ej on 11/7/2017.
 */
public class ActionScriptTest {

    @Test
    public void actionScript2Json() {

        Animate animate = new Animate();
        animate.begin = 0f;
        animate.from = new float[]{0f,0f,0f};
        animate.to = new float[]{0f,0f,0f};
        animate.dur = 1f;

        Role role = new Role();

        RoleFigure roleFigure = new RoleFigure();
        ActionScript actionScrpit = new ActionScript();


    }
}