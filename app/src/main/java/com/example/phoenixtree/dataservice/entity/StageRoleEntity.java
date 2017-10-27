package com.example.phoenixtree.dataservice.entity;

/**
 * Created by ej on 10/16/2017.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "stage_role",
        foreignKeys = {
                @ForeignKey(entity = StagePlayEntity.class, parentColumns = "id", childColumns = "stage_play_id"),
                @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "user_id")
        },
        indices = {
                @Index("user_id"),
                @Index("stage_play_id")
        })
public class StageRoleEntity {
    @PrimaryKey
    public long id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public String description;

    @ColumnInfo(name = "user_id")
    public long userId;

    @ColumnInfo(name = "stage_play_id")
    public long stagePlayId;
}
