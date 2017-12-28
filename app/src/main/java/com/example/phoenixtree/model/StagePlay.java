package com.example.phoenixtree.model;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;

import java.util.List;

/**
 * Created by ej on 10/16/2017.
 */

public class StagePlay extends StagePlayEntity {
    public User director;
    public Stage stage;
    public List<StageScene> scenes;
    public List<StageRole> cast;
}
