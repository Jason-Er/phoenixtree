package com.example.phoenixtree.util;

import android.util.Log;

/**
 * Created by ej on 8/30/2017.
 */

public class KeyframeProcessor implements PanelInterface {
    final String TAG = KeyframeProcessor.class.getName();

    public KeyframeProcessor() {

    }

    @Override
    public void play() {
        Log.i(TAG, "play");
    }

    @Override
    public void pause() {
        Log.i(TAG, "pause");
    }

    @Override
    public void resume() {
        Log.i(TAG, "resume");
    }

    @Override
    public void stop() {
        Log.i(TAG, "stop");
    }
}
