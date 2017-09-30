package com.example.phoenixtree.dataservice.remote;

import android.util.Log;

import com.example.phoenixtree.model.Play4PW;
import com.example.phoenixtree.util.LiveDataCallAdapterFactory;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by ej on 9/29/2017.
 */
public class WebServiceTest {
    WebService webService;
    @Before
    public void setUp() throws Exception {
        webService = new Retrofit.Builder()
                .baseUrl("http://192.1.112.31:8448/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(WebService.class);
    }

    @Test
    public void loadPlayC() throws Exception {
        Call<Play4PW> call = webService.loadPlayC(1);
        Play4PW play4PW = call.execute().body();
        assertTrue(play4PW.cast.size()>0);
        String json = "";
        Gson gson = new Gson();
        String entityToStr = gson.toJson(play4PW);
    }

}