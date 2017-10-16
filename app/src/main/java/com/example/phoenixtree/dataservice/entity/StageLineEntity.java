package com.example.phoenixtree.dataservice.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ej on 10/16/2017.
 */
@Entity(tableName = "stage_line",
        foreignKeys = {
                @ForeignKey(entity = StageSceneEntity.class, parentColumns = "id", childColumns = "stage_scene_id"),
                @ForeignKey(entity = StageRoleEntity.class, parentColumns = "id", childColumns = "stage_role_id")
        },
        indices = {
                @Index("stage_scene_id"),
                @Index("stage_role_id")
        })
public class StageLineEntity {
    @PrimaryKey
    public long id;

    @ColumnInfo(name = "stage_scene_id")
    public long stageSceneId;

    @ColumnInfo(name = "stage_role_id")
    public long stageRoleId;
    public long ordinal;

    public LineType type;
    @ColumnInfo(name = "stage_direction")
    public String stageDirection;
    public String dialogue;

    @ColumnInfo(name = "begin_time") // offset to ordinal time
    public float beginTime;
}
