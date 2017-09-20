package com.example.phoenixtree.dataservice.entity;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by ej on 9/20/2017.
 */

public class CustomConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
    @TypeConverter
    public static LineType stringToLineType(String value) {
        return value == null ? null : LineType.valueOf(value);
    }
    @TypeConverter
    public static String lineTypeToString(LineType lineType) {
        return lineType == null? null : lineType.toString();
    }
}
