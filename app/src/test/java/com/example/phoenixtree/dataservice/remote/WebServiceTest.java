package com.example.phoenixtree.dataservice.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;
import com.example.phoenixtree.model.Play4PW;
import com.example.phoenixtree.model.StagePlay;
import com.example.phoenixtree.util.LiveDataCallAdapterFactory;
import com.example.phoenixtree.util.RetrievePageInfo;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by ej on 9/29/2017.
 */
public class WebServiceTest {
    final String TAG =  WebServiceTest.class.getName();
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
    public void loadPlayCall() throws Exception {
        Call<Play4PW> call = webService.loadPlayCall(1);
        Play4PW play4PW = call.execute().body();
        assertTrue(play4PW.cast.size()>0);
        String json = "{\"cast\":[{\"id\":1,\"firstName\":\"Jill\",\"lastName\":\"Bradleyson\",\"description\":\"A woman in her late 20s\",\"playId\":1}],\"scenes\":[{\"lines\":[{\"id\":1,\"sceneId\":1,\"roleId\":1,\"ordinal\":0,\"type\":\"DIRECTION_DIALOGUE\",\"stageDirection\":\"Two sets of footsteps\",\"dialogue\":\"What’s happening here?\"}],\"id\":1,\"actOrdinal\":1,\"ordinal\":1,\"playId\":1,\"setting\":\"We are in the basement\",\"atrise\":\"DONALD BRADLEYSON is curled up\"}],\"playwright\":{\"id\":1,\"firstName\":\"mike\",\"lastName\":\"fighter\",\"email\":\"mike@163.com\"},\"id\":1,\"name\":\"THE BOYS IN THE CAGE\",\"playwrightId\":1}";
        Gson gson = new Gson();
        String entityToStr = gson.toJson(play4PW);
        assertEquals(entityToStr, json);
    }

    @Test
    public void loadStagePlayCall() throws Exception {
        Call<StagePlay> call = webService.loadStagePlayCall(1);
        StagePlay play = call.execute().body();
        assertTrue(play.cast.size() > 0);
    }

    @Test
    public void loadPlay1() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        LiveData<ApiResponse<Play4PW>> liveData = webService.loadPlay(1);
        ApiResponse<Play4PW> value = liveData.getValue();
        Observer<ApiResponse<Play4PW>> observer = new Observer<ApiResponse<Play4PW>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<Play4PW> o) {
                Play4PW play4PW = o.body;
                assertTrue(play4PW.cast.size()>0);
                assertTrue(o.isSuccessful());
                Gson gson = new Gson();
                String entityToStr = gson.toJson(play4PW);
                Log.i(TAG, entityToStr);

            }
        };
        liveData.observeForever(observer);
        latch.await(5, TimeUnit.SECONDS);

    }

    @Test
    public void loadStagePlayInfoCall() throws Exception {
        Call<RetrievePageInfo<List<StagePlayEntity>>> call = webService.loadStagePlayInfoCall(0,15);
        RetrievePageInfo<List<StagePlayEntity>> body = call.execute().body();

        assertTrue(body.size == 15);

        List<StagePlayEntity> content = body.content;
        Log.i(TAG, content.get(0).name);

    }

    @Test
    public void loadStagePlayInfo() throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);

        LiveData<ApiResponse<RetrievePageInfo<List<StagePlayEntity>>>> liveData = webService.loadStagePlayInfo(0,15);

        Observer<ApiResponse<RetrievePageInfo<List<StagePlayEntity>>>> observer = new Observer<ApiResponse<RetrievePageInfo<List<StagePlayEntity>>>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<RetrievePageInfo<List<StagePlayEntity>>> retrievePageInfoApiResponse) {
                RetrievePageInfo<List<StagePlayEntity>> pageInfo = retrievePageInfoApiResponse.body;
                assertTrue(pageInfo.size == 15);
                List<StagePlayEntity> content = (List<StagePlayEntity>)pageInfo.content;
                Log.i(TAG, content.get(0).name);
            }
        };
        liveData.observeForever(observer);

        latch.await(5, TimeUnit.SECONDS);

    }

}