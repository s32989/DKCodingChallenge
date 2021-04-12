package com.dkchallenge.springboot.controller.request;

public class SearchContinuityAboveValueTwoSignalsRequest {

    private int indexBegin;
    private int indexEnd;
    private float threshold1;
    private float threshold2;
    private int winLength;

    public SearchContinuityAboveValueTwoSignalsRequest(int indexBegin, int indexEnd, float threshold1, float threshold2, int winLength) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        this.winLength = winLength;
    }

    public int getIndexBegin() {
        return indexBegin;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public float getThreshold1() {
        return threshold1;
    }

    public float getThreshold2() {
        return threshold2;
    }

    public int getWinLength() {
        return winLength;
    }
}
