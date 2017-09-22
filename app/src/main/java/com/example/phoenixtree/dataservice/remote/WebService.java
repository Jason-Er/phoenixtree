package com.example.phoenixtree.dataservice.remote;

import android.arch.lifecycle.LiveData;

import com.example.phoenixtree.model.Play4PW;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by ej on 9/1/2017.
 */

public interface WebService {
    @GET("v1/web/play/{id}")
    LiveData<ApiResponse<Play4PW>> loadPlay(@Path("id") long id);
    @GET("v1/web/play/{id}")
    LiveData<ApiResponse<Play4PW>> loadPlay(@Header("Authorization") String token, @Path("id") long id);
}
