package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.PlayEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mikef on 2017/8/5.
 */
@Dao
public interface PlayEntityDao {
    @Insert(onConflict = REPLACE)
    void save(PlayEntity play);
    @Delete
    void delete(PlayEntity play);
    @Query("SELECT * FROM play WHERE id = :playId")
    LiveData<PlayEntity> retrieve(long playId);
}
