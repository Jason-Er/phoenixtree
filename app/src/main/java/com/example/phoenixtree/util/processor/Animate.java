package com.example.phoenixtree.util.processor;

/**
 * Created by ej on 9/14/2017.
 */

public class Animate {
    private String attributeName;
    private float from[];
    private float to[];
    private float begin;
    private float dur;
    private String calcMode;

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    public String getAttributeName() {
        return attributeName;
    }

    public float[] getFrom() {
        return from;
    }
    public void setFrom(float[] from) {
        this.from = from;
    }

    public float[] getTo() {
        return to;
    }
    public void setTo(float[] to) {
        this.to = to;
    }

    public float getBegin() {
        return begin;
    }
    public void setBegin(float begin) {
        this.begin = begin;
    }

    public float getDur() {
        return dur;
    }
    public void setDur(float dur) {
        this.dur = dur;
    }

    public void setCalcMode(String calcMode) {
        this.calcMode = calcMode;
    }
    public String getCalcMode() {
        return calcMode;
    }

}
