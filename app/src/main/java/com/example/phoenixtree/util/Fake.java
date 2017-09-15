package com.example.phoenixtree.util;

import com.example.phoenixtree.model.Keyframe;
import com.example.phoenixtree.model.Position3D;
import com.example.phoenixtree.model.Role;
import com.example.phoenixtree.model.Scene;
import com.example.phoenixtree.model.Stage;

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

        Map<Role, Position3D> mapPositons = new HashMap<>();
        Position3D position3D = new Position3D(0.0f, 0.0f, 0.0f);
        mapPositons.put(role, position3D);
        keyframe.setMapPositon(mapPositons);

        return keyframe;
    }
    static public Scene propagateScene() {
        Scene scene = new Scene();
        return scene;
    }
}
