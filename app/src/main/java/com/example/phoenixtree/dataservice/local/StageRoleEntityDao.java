package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.StageRoleEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
/**
 * Created by ej on 10/18/2017.
 */
@Dao
public interface StageRoleEntityDao {
    @Insert(onConflict = REPLACE)
    void save(StageRoleEntity entity);
    @Delete
    void delete(StageRoleEntity entity);
    @Query("SELECT * FROM stage_role WHERE id = :id")
    LiveData<StageRoleEntity> retrieveByIdLive(long id);
    @Query("SELECT * FROM stage_role WHERE id = :id")
    StageRoleEntity retrieveById(long id);
    @Query("SELECT * FROM stage_role WHERE stage_play_id = :stagePlayId")
    LiveData<List<StageRoleEntity>> retrieveAllByStagePlayIdLive(long stagePlayId);
}
