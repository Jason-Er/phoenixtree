package com.example.phoenixtree.dataservice.remote;

import android.arch.lifecycle.LiveData;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.model.dto.AccountCredentials;
import com.example.phoenixtree.model.Play4PW;
import com.example.phoenixtree.model.script.StagePlay;
import com.example.phoenixtree.util.RetrievePageInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ej on 9/1/2017.
 */

public interface WebService {
    @GET("v1/method/play/{id}")
    Call<Play4PW> loadPlayCall(@Path("id") long id);
    @GET("v1/method/play/{id}")
    LiveData<ApiResponse<Play4PW>> loadPlay(@Path("id") long id);
    @GET("v1/method/play/{id}")
    LiveData<ApiResponse<Play4PW>> loadPlay(@Header("Authorization") String token, @Path("id") long id);
    @GET("v1/method/stageplay/{id}")
    LiveData<ApiResponse<StagePlay>> loadStagePlay(@Path("id") long id);
    @GET("v1/method/stageplay/{id}")
    Call<StagePlay> loadStagePlayCall(@Path("id") long id);
    @GET("v1/method/stageplayinfo")
    LiveData<ApiResponse<List<StagePlayEntity>>> loadStagePlayInfo();
    @GET("v1/method/stageplayinfo")
    LiveData<ApiResponse<RetrievePageInfo<List<StagePlayEntity>>>> loadStagePlayInfo(@Query("page") long page, @Query("size") long size);
    @GET("v1/method/stageplayinfo")
    Call<RetrievePageInfo<List<StagePlayEntity>>> loadStagePlayInfoCall(@Query("page") long page, @Query("size") long size);
    @POST("v1/method/login")
    LiveData<ApiResponse<String>> loadToken(@Body AccountCredentials credentials);
}
