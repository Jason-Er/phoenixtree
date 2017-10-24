package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.StageSceneEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
/**
 * Created by ej on 10/18/2017.
 */
@Dao
public interface StageSceneEntityDao {
    @Insert(onConflict = REPLACE)
    void save(StageSceneEntity entity);
    @Delete
    void delete(StageSceneEntity entity);
    @Query("SELECT * FROM stage_scene WHERE id = :id")
    LiveData<StageSceneEntity> retrieveByIdLive(long id);
    @Query("SELECT * FROM stage_scene WHERE id = :id")
    StageSceneEntity retrieveById(long id);
    @Query("SELECT * FROM stage_scene WHERE stage_play_id = :stagePlayId")
    LiveData<List<StageSceneEntity>> retrieveAllByStagePlayIdLive(long stagePlayId);
}
