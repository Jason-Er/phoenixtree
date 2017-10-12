package com.example.phoenixtree.dataservice.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.phoenixtree.dataservice.entity.UserEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by ej on 10/11/2017.
 */
@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {
    final String TAG = AppDatabaseTest.class.getName();
    private UserEntityDao userEntityDao;
    private AppDatabase appDatabase;
    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userEntityDao = appDatabase.userEntityDao();
    }

    @After
    public void tearDown() throws Exception {
        appDatabase.close();
    }

    @Test
    public void writeUserAndReadInList() {
        UserEntity entity = new UserEntity();
        entity.firstName = "fighter";
        entity.lastName = "mike";
        entity.id = 1L;
        entity.email = "1@163.com";
        entity.password = "1";
        userEntityDao.save(entity);
        UserEntity value = userEntityDao.retrieveById(entity.id );
        assertNotNull(value);

    }

}