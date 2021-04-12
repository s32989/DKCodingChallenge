package com.dkchallenge.springboot.controller.request;

public class SearchMultiContinuityWithinRangeRequest {

    private int indexBegin;
    private int indexEnd;
    private float thresholdLow;
    private float thresholdHi;
    private int winLength;

    public SearchMultiContinuityWithinRangeRequest(int indexBegin, int indexEnd, float thresholdLow, float thresholdHi, int winLength) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.thresholdLow = thresholdLow;
        this.thresholdHi = thresholdHi;
        this.winLength = winLength;
    }

    public int getIndexBegin() {
        return indexBegin;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public float getThresholdLow() {
        return thresholdLow;
    }

    public float getThresholdHi() {
        return thresholdHi;
    }

    public int getWinLength() {
        return winLength;
    }
}
