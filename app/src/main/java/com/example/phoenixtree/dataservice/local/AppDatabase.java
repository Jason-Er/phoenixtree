package com.example.phoenixtree.dataservice.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.phoenixtree.dataservice.entity.CustomConverter;
import com.example.phoenixtree.dataservice.entity.LineEntity;
import com.example.phoenixtree.dataservice.entity.PlayEntity;
import com.example.phoenixtree.dataservice.entity.RoleEntity;
import com.example.phoenixtree.dataservice.entity.SceneEntity;
import com.example.phoenixtree.dataservice.entity.StageEntity;
import com.example.phoenixtree.dataservice.entity.StageLineEntity;
import com.example.phoenixtree.dataservice.entity.StageLineLocalEntity;
import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.dataservice.entity.StageRoleEntity;
import com.example.phoenixtree.dataservice.entity.StageSceneEntity;
import com.example.phoenixtree.dataservice.entity.UserEntity;

/**
 * Created by mikef on 2017/8/5.
 */
@Database(entities = {
        UserEntity.class,
        PlayEntity.class,
        SceneEntity.class,
        LineEntity.class,
        RoleEntity.class,
        StageEntity.class,
        StageLineEntity.class,
        StageLineLocalEntity.class,
        StagePlayEntity.class,
        StageRoleEntity.class,
        StageSceneEntity.class
        },
        version = 1)
@TypeConverters({CustomConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "phoenix-db";
    public abstract UserEntityDao userEntityDao();
    public abstract RoleEntityDao roleEntityDao();
    public abstract PlayEntityDao playEntityDao();
    public abstract SceneEntityDao sceneEntityDao();
    public abstract LineEntityDao lineEntityDao();
    public abstract StageEntityDao stageEntityDao();
    public abstract StageLineEntityDao stageLineEntityDao();
    public abstract StageLineLocalEntityDao stageLineLocalEntityDao();
    public abstract StagePlayEntityDao stagePlayEntityDao();
    public abstract StageRoleEntityDao stageRoleEntityDao();
    public abstract StageSceneEntityDao stageSceneEntityDao();
}
