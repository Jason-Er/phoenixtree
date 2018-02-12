package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.StageLineEntity;
import com.example.phoenixtree.model.script.StageLine;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
/**
 * Created by ej on 10/18/2017.
 */
@Dao
public interface StageLineEntityDao {
    @Insert(onConflict = REPLACE)
    void save(StageLineEntity entity);
    @Delete
    void delete(StageLineEntity entity);
    @Query("SELECT * FROM stage_line WHERE id = :id")
    LiveData<StageLineEntity> retrieveByIdLive(long id);
    @Query("SELECT * FROM stage_line WHERE id = :id")
    StageLineEntity retrieveById(long id);
    @Query("SELECT * FROM stage_line WHERE stage_scene_id = :stageSceneId order by ordinal asc")
    LiveData<List<StageLine>> retrieveAllByStageSceneIdLive(long stageSceneId);
}
