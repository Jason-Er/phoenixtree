package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.phoenixtree.dataservice.entity.UserEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by ej on 9/20/2017.
 */
@Dao
public interface UserEntityDao {
    @Insert(onConflict = REPLACE)
    void save(UserEntity userEntity);
    @Delete
    void delete(UserEntity userEntity);
    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<UserEntity> retrieveByIdLive(long userId);
    @Query("SELECT * FROM user WHERE id = :userId")
    UserEntity retrieveById(long userId);
}
