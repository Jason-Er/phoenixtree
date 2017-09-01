package com.example.phoenixtree.util;

import android.util.Log;

/**
 * Created by ej on 8/31/2017.
 */

public class AudioProcessor implements PanelInterface{
    final String TAG = AudioProcessor.class.getName();

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
