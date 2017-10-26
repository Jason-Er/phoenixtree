package com.example.phoenixtree.dataservice.local;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.phoenixtree.dataservice.entity.StagePlayEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by ej on 10/26/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StagePlayEntityDaoTest {
    private AppDatabase appDatabase;
    private StagePlayEntityDao stagePlayEntityDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        stagePlayEntityDao = appDatabase.stagePlayEntityDao();

        StagePlayEntity entity = new StagePlayEntity();
        entity.name = "hello world";
        entity.directorId = 1l;
        stagePlayEntityDao.save(entity);
    }

    @After
    public void closeDb() throws IOException {
        appDatabase.close();
    }

    @Test
    public void retrieveCountLiveCall() throws Exception {
        long along = stagePlayEntityDao.retrieveCountLiveCall();
        assertEquals(along, 1l);
    }

    @Test
    public void retrievePagedListLiveCall() throws Exception {
        List<StagePlayEntity> stagePlayEntities = stagePlayEntityDao.retrievePagedListLiveCall(0, 15);
        assertNotNull(stagePlayEntities);
    }

}