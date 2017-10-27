package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.StageEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
/**
 * Created by ej on 10/18/2017.
 */
@Dao
public interface StageEntityDao {
    @Insert(onConflict = REPLACE)
    void save(StageEntity entity);
    @Delete
    void delete(StageEntity entity);
    @Query("SELECT * FROM stage WHERE id = :id")
    LiveData<StageEntity> retrieveByIdLive(long id);
    @Query("SELECT * FROM stage WHERE id = :id")
    StageEntity retrieveById(long id);
}
