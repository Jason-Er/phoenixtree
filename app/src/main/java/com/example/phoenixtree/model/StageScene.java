package com.example.phoenixtree.model;

import com.example.phoenixtree.dataservice.entity.StageLineEntity;
import com.example.phoenixtree.dataservice.entity.StageLineLocalEntity;
import com.example.phoenixtree.dataservice.entity.StageSceneEntity;

import java.util.List;

/**
 * Created by ej on 10/16/2017.
 */

public class StageScene extends StageSceneEntity {
    public ActionScrpit actionScrpit;
    public List<StageLineEntity> stageLines;
    public List<StageLineLocalEntity> stageLinesLocal;
}
