package com.example.phoenixtree.dataservice.remote;

import android.arch.lifecycle.LiveData;

import com.example.phoenixtree.Model.Play;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ej on 9/1/2017.
 */

public interface WebService {
    @POST("users/{login}")
    LiveData<ApiResponse<Play>> login(@Path("login") String login);
}
