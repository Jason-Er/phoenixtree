package com.example.phoenixtree.dataservice.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ej on 10/16/2017.
 */
@Entity(tableName = "stage_play",
        foreignKeys = {
                @ForeignKey(entity = PlayEntity.class, parentColumns = "id", childColumns = "play_id"),
                @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "director_id"),
                @ForeignKey(entity = StageEntity.class, parentColumns = "id", childColumns = "stage_id")
        },
        indices = {
                @Index("play_id"),
                @Index("director_id"),
                @Index("stage_id")
        })
public class StagePlayEntity {
    @PrimaryKey
    public long id;

    public String name;

    @ColumnInfo(name = "poster_url")
    public String posterURL;

    @ColumnInfo(name = "brief_intro")
    public String briefIntro;

    @ColumnInfo(name = "play_id")
    public long playId;

    @ColumnInfo(name = "director_id")
    public long directorId;

    @ColumnInfo(name = "stage_id")
    public long stageId;

    @ColumnInfo(name = "copy_from")
    public long copyfrom;
}
