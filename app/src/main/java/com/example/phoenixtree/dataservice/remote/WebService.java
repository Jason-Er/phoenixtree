package com.example.phoenixtree.dataservice.remote;

import android.arch.lifecycle.LiveData;

import com.example.phoenixtree.model.Play;

import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ej on 9/1/2017.
 */

public interface WebService {
    @POST("users/{login}")
    LiveData<ApiResponse<Play>> login(@Path("login") String login);
}
