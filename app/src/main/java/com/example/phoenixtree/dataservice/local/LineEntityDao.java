package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.LineEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mikef on 2017/8/5.
 */
@Dao
public interface LineEntityDao {
    @Insert(onConflict = REPLACE)
    void save(LineEntity line);
    @Delete
    void delete(LineEntity line);
    @Query("SELECT * FROM line WHERE id = :lineId")
    LiveData<LineEntity> retrieve(long lineId);
}
