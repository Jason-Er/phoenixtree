package com.example.phoenixtree.Model;

import java.util.Map;
import java.util.Set;

/**
 * Created by ej on 8/22/2017.
 */

public class Keyframe {
    private Set<Role> roles;
    private Map mapLines;
    private Map mapPositons;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
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
