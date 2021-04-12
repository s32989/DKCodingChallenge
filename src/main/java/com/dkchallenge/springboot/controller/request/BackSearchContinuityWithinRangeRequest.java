package com.dkchallenge.springboot.controller.request;

public class BackSearchContinuityWithinRangeRequest {
    private int indexBegin;
    private int indexEnd;
    private float thresholdHi;
    private float thresholdLow;
    private int winLength;

    public BackSearchContinuityWithinRangeRequest(int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.thresholdHi = thresholdHi;
        this.thresholdLow = thresholdLow;
        this.winLength = winLength;
    }

    public int getIndexBegin() {
        return indexBegin;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public float getThresholdHi() {
        return thresholdHi;
    }

    public float getThresholdLow() {
        return thresholdLow;
    }

    public int getWinLength() {
        return winLength;
    }
}
