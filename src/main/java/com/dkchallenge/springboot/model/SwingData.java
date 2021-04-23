package com.dkchallenge.springboot.model;

public class SwingData {

    private long timestamp;
    private float ax;
    private float ay;
    private float az;
    private float wx;
    private float wy;
    private float wz;

    public SwingData(long timestamp, float ax, float ay, float az, float wx, float wy, float wz) {
        this.timestamp = timestamp;
        this.ax = ax;
        this.ay = ay;
        this.az = az;
        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getAx() {
        return ax;
    }

    public float getAy() {
        return ay;
    }

    public float getAz() {
        return az;
    }

    public float getWx() {
        return wx;
    }

    public float getWy() {
        return wy;
    }

    public float getWz() {
        return wz;
    }

}
