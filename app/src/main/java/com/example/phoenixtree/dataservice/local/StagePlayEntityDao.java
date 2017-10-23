package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
/**
 * Created by ej on 10/18/2017.
 */
@Dao
public interface StagePlayEntityDao {
    @Insert(onConflict = REPLACE)
    void save(StagePlayEntity entity);
    @Delete
    void delete(StagePlayEntity entity);
    @Query("SELECT * FROM stage_play WHERE id = :id")
    LiveData<StagePlayEntity> retrieveByIdLive(long id);
    @Query("SELECT * FROM stage_play WHERE id = :id")
    StagePlayEntity retrieveById(long id);
}
