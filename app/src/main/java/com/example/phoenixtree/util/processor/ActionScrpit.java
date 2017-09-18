package com.example.phoenixtree.util.processor;

import java.util.List;

/**
 * Created by ej on 9/14/2017.
 */

public class ActionScrpit {
    private String sceneId;
    private List<Role> roleList;

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
