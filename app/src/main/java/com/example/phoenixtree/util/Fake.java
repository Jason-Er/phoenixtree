package com.example.phoenixtree.util;

import com.example.phoenixtree.util.processor.ActionScrpit;
import com.example.phoenixtree.util.processor.Animate;
import com.example.phoenixtree.util.processor.Keyframe;
import com.example.phoenixtree.model.Role;
import com.example.phoenixtree.model.Scene;
import com.example.phoenixtree.util.processor.RoleFigure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ej on 8/23/2017.
 */

public class Fake {
    static public Keyframe propagateKeyframe() {
        Keyframe keyframe = new Keyframe();

        /*
        float[] stageVerties = {
                -8f, 8f,0f,1f,
                -8f,-8f,0f,1f,
                 8f,-8f,0f,1f,
                8f,8f,0f,1f,
                8f,8f,10f,1f,
                -8f,8f,10f,1f};
        Stage stage = new Stage();
        stage.setStageVertices(stageVerties);
        keyframe.setStage(stage);
        */
        List<Role> roles = new ArrayList<>();
        float[] roleVerties = {-1f,0f,0f,1f,
                -1f,0f,6f,1f,
                 1f,0f,6f,1f,
                 1f,0f,0f,1f};
        Role role = new Role();
        role.setRoleVertices(roleVerties);
        role.setName("mike");
        roles.add(role);
        keyframe.setRoles(roles);

        Map<Role, String> mapLines = new HashMap<>();
        mapLines.put(role, "Hello world!");
        keyframe.setMapLines(mapLines);

        return keyframe;
    }
    static public Scene propagateScene() {
        Scene scene = new Scene();

        ActionScrpit actionScrpit = new ActionScrpit();
        actionScrpit.setSceneId("1");
        List<com.example.phoenixtree.util.processor.Role> roles = new ArrayList<>();
        com.example.phoenixtree.util.processor.Role role = new com.example.phoenixtree.util.processor.Role();
        RoleFigure roleFigure = new RoleFigure(2f,6f);
        role.setFigure(roleFigure);
        roles.add(role);
        List<Animate> animates = new ArrayList<>();
        Animate animate = new Animate();
        animate.setBegin(0f);
        animate.setAttributeName("position");
        animate.setFrom(new float[]{0f,0f,0f});
        animates.add(animate);
        role.setAnimateList(animates);

        actionScrpit.setRoleList(roles);

        scene.setSceneId("1");
        scene.setActionScrpit(actionScrpit);
        return scene;
    }
}
