package com.example.phoenixtree.util.processor;

import java.util.List;
import java.util.Map;

/**
 * Created by ej on 8/22/2017.
 */

public class Keyframe {
    Stage stage;
    List<com.example.phoenixtree.model.Role> roles;
    Map<com.example.phoenixtree.model.Role, String> mapLines;

    public List<com.example.phoenixtree.model.Role> getRoles() {
        return roles;
    }

    public void setRoles(List<com.example.phoenixtree.model.Role> roles) {
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
