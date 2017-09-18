package com.example.phoenixtree.model;

import java.util.List;
import java.util.Map;

/**
 * Created by ej on 8/22/2017.
 */

public class Keyframe {
    private Stage stage;
    private List<Role> roles;
    private Map<Role, String> mapLines;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
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
