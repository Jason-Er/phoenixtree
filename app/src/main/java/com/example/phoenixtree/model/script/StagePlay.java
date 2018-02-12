package com.example.phoenixtree.model.script;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.model.User;

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
