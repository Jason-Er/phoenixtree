package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;

import java.util.List;

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
    @Query("SELECT * FROM stage_play ORDER BY id ASC LIMIT :size OFFSET :page * :size")
    LiveData<List<StagePlayEntity>> retrievePagedListLive(long page, long size);
    @Query("SELECT * FROM stage_play ORDER BY id ASC LIMIT :size OFFSET :page * :size")
    List<StagePlayEntity> retrievePagedListLiveCall(long page, long size);
    @Query("SELECT COUNT(*) FROM stage_play")
    LiveData<Long> retrieveCountLive();
    @Query("SELECT COUNT(*) FROM stage_play")
    Long retrieveCountLiveCall();

}
