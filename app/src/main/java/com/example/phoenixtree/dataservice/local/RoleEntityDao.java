package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.RoleEntity;

import java.util.List;

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
    @Query("SELECT * FROM role WHERE id = :roleId")
    LiveData<RoleEntity> retrieveByIdLive(long roleId);
    @Query("SELECT * FROM role WHERE play_id = :playId")
    List<RoleEntity> retrieveAllByPlayId(long playId);
    @Query("SELECT * FROM role WHERE play_id = :playId")
    LiveData<List<RoleEntity>> retrieveAllByPlayIdLive(long playId);
}
