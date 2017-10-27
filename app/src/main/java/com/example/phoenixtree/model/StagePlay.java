package com.example.phoenixtree.model;

import com.example.phoenixtree.dataservice.entity.StageEntity;
import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.dataservice.entity.StageRoleEntity;
import com.example.phoenixtree.dataservice.entity.UserEntity;

import java.util.List;

/**
 * Created by ej on 10/16/2017.
 */

public class StagePlay extends StagePlayEntity {
    public UserEntity director;
    public StageEntity stageSize;
    public List<StageScene> scenes;
    public List<StageRoleEntity> cast;
}
