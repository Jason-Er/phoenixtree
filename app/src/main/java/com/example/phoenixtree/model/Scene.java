package com.example.phoenixtree.model;

import com.example.phoenixtree.util.processor.ActionScrpit;

/**
 * Created by ej on 8/22/2017.
 */

public class Scene {
    private String sceneId;
    private Role[] roles = new Role[0];
    private ActionScrpit actionScrpit;

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public ActionScrpit getActionScrpit() {
        return actionScrpit;
    }

    public void setActionScrpit(ActionScrpit actionScrpit) {
        this.actionScrpit = actionScrpit;
    }
}
