package com.example.phoenixtree.model;

import com.example.phoenixtree.dataservice.entity.StageLineEntity;
import com.example.phoenixtree.dataservice.entity.StageLineLocalEntity;
import com.example.phoenixtree.dataservice.entity.StageSceneEntity;
import com.example.phoenixtree.model.actionscript.ActionScript;

import java.util.List;

/**
 * Created by ej on 10/16/2017.
 */

public class StageScene extends StageSceneEntity {
    public ActionScript actionScrpit;
    public List<StageLineEntity> stageLines;
    public List<StageLineLocalEntity> stageLinesLocal;
}
