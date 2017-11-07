package com.example.phoenixtree.model.actionscript;

import com.example.phoenixtree.util.JsonUtil;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

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

        RoleFigure roleFigure = new RoleFigure();
        roleFigure.width = 2f;
        roleFigure.height = 2f;

        Role role = new Role();
        role.figure = roleFigure;
        role.name = "mike";
        role.animateList = new ArrayList<>();
        role.animateList.add(animate);

        ActionScript actionScript = new ActionScript();
        actionScript.roleList = new ArrayList<>();
        actionScript.roleList.add(role);
        actionScript.sceneId = 1L;

        String json = JsonUtil.objectToString(actionScript);

        assertNotNull(json);


    }
}