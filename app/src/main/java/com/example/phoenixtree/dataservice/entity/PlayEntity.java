package com.example.phoenixtree.dataservice.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ej on 9/19/2017.
 */
@Entity(tableName = "play",
        foreignKeys = @ForeignKey(entity = UserEntity.class,
                parentColumns = "id",
                childColumns = "playwright_id"),
        indices = {
                @Index("playwright_id"),
                @Index("adapted_from")
        })
public class PlayEntity {
    @PrimaryKey
    public long id;

    public String name;

    @ColumnInfo(name = "playwright_id")
    public long playwrightId;

    @ColumnInfo(name = "adapted_from")
    public long adaptedfrom;
}
