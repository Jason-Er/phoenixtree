package com.example.phoenixtree.util.processor;

import com.example.phoenixtree.model.Role4DIR;

import java.util.List;
import java.util.Map;

/**
 * Created by ej on 8/22/2017.
 */

public class Keyframe {
    Stage stage;
    List<Role4DIR> roles;
    Map<Role4DIR, String> mapLines;

    public List<Role4DIR> getRoles() {
        return roles;
    }

    public void setRoles(List<Role4DIR> roles) {
        this.roles = roles;
    }

    public Map getMapLines() {
        return mapLines;
    }

    public void setMapLines(Map mapLines) {
        this.mapLines = mapLines;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
