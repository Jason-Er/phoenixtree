package com.example.phoenixtree.dataservice.entity;

/**
 * Created by ej on 10/16/2017.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "stage_scene",
        foreignKeys = {
                @ForeignKey(entity = StagePlayEntity.class, parentColumns = "id", childColumns = "stage_play_id")
        },
        indices = {
                @Index("stage_play_id")
        })
public class StageSceneEntity {
    @PrimaryKey
    public long id;

    @ColumnInfo(name = "act_ordinal")
    public long actOrdinal;
    public long ordinal;

    @ColumnInfo(name = "stage_play_id")
    public long stagePlayId;

    public String setting;
    @ColumnInfo(name = "at_rise")
    public String atrise;

    @ColumnInfo(name = "action_script")
    public String actionScript;

    @ColumnInfo(name = "setting_url")
    public String settingURL;

    @ColumnInfo(name = "bgm_url")
    public String backgroundMusicURL;
}
