package com.example.phoenixtree.Model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ej on 8/22/2017.
 */

public class Keyframe {
    private List<Role> roles;
    private Map<Role, String> mapLines;
    private Map<Role, Position3D> mapPositons;

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

    public Map getMapPositon() {
        return mapPositons;
    }

    public void setMapPositon(Map mapPositons) {
        this.mapPositons = mapPositons;
    }
}
