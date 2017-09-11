package com.example.phoenixtree.util;

import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phoenixtree.Model.Keyframe;
import com.example.phoenixtree.Model.Resource;
import com.example.phoenixtree.Model.Scene;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ej on 8/30/2017.
 */

public class KeyframeProcessor implements PanelInterface {
    final String TAG = KeyframeProcessor.class.getName();

    private Scene scene;
    private MediatorLiveData<Resource<Keyframe>> keyframe;

    private final long timeInterval = 30;
    private long startTime;
    private Timer timer;
    private TimerTask timerTask;
    private boolean isPause = true;
    private boolean isPlay = false;
    private long elapsedTime = 0;
    private long costTime;

    public KeyframeProcessor() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                // TODO: 9/11/2017 handle keyframe
                Log.i(TAG, "Hello World!" + ( System.nanoTime() - startTime) / 1000000 + elapsedTime);
                if(elapsedTime > 0) {
                    costTime = ( System.nanoTime() - startTime) / 1000000 + elapsedTime;
                    elapsedTime = 0;
                } else {
                    costTime = ( System.nanoTime() - startTime) / 1000000;
                }
                startTime = System.nanoTime();
            }
        };
    }

    public void init(@NonNull Scene scene, @NonNull MediatorLiveData<Resource<Keyframe>> keyframe) {
        this.scene = scene;
        this.keyframe = keyframe;
        initial();
    }

    private void initial() {
        keyframe.setValue(Resource.success(Fake.propagateKeyframe()));
    }

    @Override
    public void play() {
        Log.i(TAG, "play");
        if(!isPlay) {
            startTime = System.nanoTime();
            timer.schedule(timerTask, 0, timeInterval);
        }
    }

    @Override
    public void pause() {
        Log.i(TAG, "pause");
        if(isPlay && !isPause) {
            elapsedTime = (System.nanoTime() - startTime) / 1000000;
            timer.cancel();
            isPause = true;
        }
    }

    @Override
    public void resume() {
        Log.i(TAG, "resume");
        if (isPlay && isPause) {
            startTime = System.nanoTime();
            timer.schedule(timerTask, 0, timeInterval);
            isPause = false;
        }
    }

    @Override
    public void stop() {
        Log.i(TAG, "stop");
        timer.cancel();
        isPlay = false;
        isPause = true;
        elapsedTime = 0;
    }
}
