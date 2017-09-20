package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.RoleEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by ej on 9/20/2017.
 */
@Dao
public interface RoleEntityDao {
    @Insert(onConflict = REPLACE)
    void save(RoleEntity roleEntity);
    @Delete
    void delete(RoleEntity roleEntity);
    @Query("SELECT * FROM user WHERE id = :roleId")
    LiveData<RoleEntity> retrieve(long roleId);
}
