package com.example.phoenixtree.dataservice.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ej on 10/16/2017.
 */
@Entity(tableName = "stage_line_audio",
        foreignKeys = {
                @ForeignKey(entity = StageLineEntity.class, parentColumns = "id", childColumns = "stage_line_id"),
                @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "user_id")
        },
        indices = {
                @Index(value = {"stage_line_id", "user_id"})
        })
public class StageLineAudioEntity {
    @PrimaryKey
    public long id;

    @ColumnInfo(name = "audio_url")
    public String audioURL;

    @ColumnInfo(name = "stage_line_id")
    public long stageLineId;
    @ColumnInfo(name = "user_id")
    public long userId;
}
