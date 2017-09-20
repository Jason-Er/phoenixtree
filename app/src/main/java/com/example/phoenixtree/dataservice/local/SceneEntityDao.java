package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.SceneEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mikef on 2017/8/5.
 */
@Dao
public interface SceneEntityDao {
    @Insert(onConflict = REPLACE)
    void save(SceneEntity scene);
    @Delete
    void delete(SceneEntity scene);
    @Query("SELECT * FROM scene WHERE id = :sceneId")
    LiveData<SceneEntity> retrieve(long sceneId);
}
