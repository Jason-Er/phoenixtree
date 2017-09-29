package com.example.phoenixtree.model;

import com.example.phoenixtree.dataservice.entity.PlayEntity;
import com.example.phoenixtree.dataservice.entity.RoleEntity;
import com.example.phoenixtree.dataservice.entity.UserEntity;

import java.util.List;

/**
 * Created by ej on 8/22/2017.
 *
 * This play class for playwright
 *
 */

public class Play4PW extends PlayEntity{
    public List<RoleEntity> cast;
    public List<Scene4PW> scenes;
    public UserEntity playwright;
}
