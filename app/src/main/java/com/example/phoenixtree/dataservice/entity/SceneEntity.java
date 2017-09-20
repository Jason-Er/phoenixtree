package com.example.phoenixtree.dataservice.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ej on 9/20/2017.
 */
@Entity(tableName = "scene",
        foreignKeys = @ForeignKey(entity = PlayEntity.class,
        parentColumns = "id",
        childColumns = "play_id"),
        indices = {@Index("play_id")})
public class SceneEntity {
    @PrimaryKey
    public long id;

    @ColumnInfo(name = "act_ordinal")
    public long actOrdinal;
    public long ordinal;

    @ColumnInfo(name = "play_id")
    public long playId;
}
