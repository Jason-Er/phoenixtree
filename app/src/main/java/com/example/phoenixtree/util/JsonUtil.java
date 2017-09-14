package com.example.phoenixtree.util;

import com.google.gson.Gson;

/**
 * Created by ej on 9/14/2017.
 */

public class JsonUtil {

    private static Gson gson = new Gson();

    public static Object stringToObject( String json , Class classOfT){
        return  gson.fromJson( json , classOfT ) ;
    }

    public static <T> String objectToString(T object) {
        return gson.toJson(object);
    }

}
