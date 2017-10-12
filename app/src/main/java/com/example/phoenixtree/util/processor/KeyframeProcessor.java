package com.example.phoenixtree.util.processor;

import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.phoenixtree.model.Resource;
import com.example.phoenixtree.model.Role4DIR;
import com.example.phoenixtree.model.Scene4PW;
import com.example.phoenixtree.util.PanelInterface;

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

    private Scene4PW scene;
    private MediatorLiveData<Resource<Keyframe>> keyframeLive;

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

    public void init(@NonNull Scene4PW scene, @NonNull MediatorLiveData<Resource<Keyframe>> keyframe) {
        this.scene = scene;
        this.keyframeLive = keyframe;

        this.keyframe.roles = null;
        this.keyframe.mapLines = null;
        this.keyframe.stage = null;

        firstFrame();
    }

    private void firstFrame() {
        /*
        ActionScrpit actionScrpit = this.scene.getActionScrpit();
        List<Role> roleList = actionScrpit.getRoleList();
        List<Role4DIR> roles = new ArrayList<>();
        Map<Role4DIR, String> mapLines = new HashMap<>();
        for(Role role: roleList) {
            float width = role.getFigure().getWidth() / 2f;
            float height = role.getFigure().getHeight();

            for(Animate animate: role.getAnimateList()) {
                if(Math.abs(animate.getBegin()) < 1e-5) {
                    Role4DIR roleT = new Role4DIR();
                    float x = animate.getFrom()[0];
                    float y = animate.getFrom()[1];
                    float z = animate.getFrom()[2];
                    float[] roleVerties = {
                            -width + x, y, z,1f,
                            -width + x, y, height+z,1f,
                            width + x,  y, height+z,1f,
                            width + x,  y, z,1f};

                    roleT.setRoleVertices(roleVerties);
                    roleT.setName(role.getName());
                    roles.add(roleT);

                    mapLines.put(roleT, "Hello world!");
                }
            }
        }
        keyframe.setRoles(roles);

        keyframe.setMapLines(mapLines);

        // TODO: 9/18/2017 stage need further change
        float[] stageVerties = {
                -8f, 8f,0f,1f,
                -8f,-8f,0f,1f,
                8f,-8f,0f,1f,
                8f,8f,0f,1f,
                8f,8f,10f,1f,
                -8f,8f,10f,1f};
        Stage stage = new Stage();
        stage.setStageVertices(stageVerties);
        keyframe.setStage(stage);

        keyframeLive.setValue(Resource.success(keyframe));
        */
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
