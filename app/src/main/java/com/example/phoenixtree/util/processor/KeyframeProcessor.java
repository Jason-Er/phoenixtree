package com.example.phoenixtree.util.processor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phoenixtree.model.StageScene;
import com.example.phoenixtree.model.actionscript.ActionScript;
import com.example.phoenixtree.model.actionscript.Animate;
import com.example.phoenixtree.model.actionscript.Role;
import com.example.phoenixtree.model.keyframe.Line;
import com.example.phoenixtree.model.keyframe.Stage;
import com.example.phoenixtree.util.PanelInterface;
import com.example.phoenixtree.model.keyframe.Keyframe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Created by ej on 8/30/2017.
 */

public class KeyframeProcessor implements PanelInterface {
    final String TAG = KeyframeProcessor.class.getName();

    private StageScene scene;
    public MutableLiveData<Keyframe> keyframeLiveData = new MediatorLiveData<>();

    private final long timeInterval = 30;
    private long startTime;
    private Timer timer;
    private TimerTask timerTask;
    private boolean isPause = true;
    private boolean isPlay = false;
    private long elapsedTime = 0;
    private long costTime;
    private Keyframe keyframe = new Keyframe();

    @Inject
    public KeyframeProcessor() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                // TODO: 9/11/2017 handle keyframeLive role position changing with time
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

    public void setScene(@NonNull StageScene scene) {
        this.scene = scene;
    }

    public LiveData<Keyframe> firstFrame() {
        // TODO: 10/13/2017 need calculate first frame according to action script
        ActionScript actionScript = this.scene.actionScrpit;
        List<Role> roleList = actionScript.roleList;
        List<com.example.phoenixtree.model.keyframe.Role> roles = new ArrayList<>();
        Map<com.example.phoenixtree.model.keyframe.Role, Line> mapLines = new HashMap<>();
        for(Role role: roleList) {
            float width = role.figure.width / 2f;
            float height = role.figure.height;

            for(Animate animate: role.animateList) {
                if(Math.abs(animate.begin) < 1e-5) {
                    com.example.phoenixtree.model.keyframe.Role roleT = new com.example.phoenixtree.model.keyframe.Role();
                    float x = animate.from[0];
                    float y = animate.from[1];
                    float z = animate.from[2];
                    float[] roleVerties = {
                            -width + x, y, z,1f,
                            -width + x, y, height+z,1f,
                            width + x,  y, height+z,1f,
                            width + x,  y, z,1f};

                    roleT.roleVertices = roleVerties;
                    roleT.name = role.name;
                    roles.add(roleT);

                    mapLines.put(roleT, new Line());
                }
            }
        }
        keyframe.roles = roles;

        keyframe.mapLines = mapLines;

        // TODO: 9/18/2017 stage need further change
        float[] stageVerties = {
                -8f, 8f,0f,1f,
                -8f,-8f,0f,1f,
                8f,-8f,0f,1f,
                8f,8f,0f,1f,
                8f,8f,10f,1f,
                -8f,8f,10f,1f};
        Stage stage = new Stage();
        stage.stageVertices =  stageVerties;
        keyframe.stage = stage;

        keyframeLiveData.setValue(keyframe);


        return keyframeLiveData;
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
