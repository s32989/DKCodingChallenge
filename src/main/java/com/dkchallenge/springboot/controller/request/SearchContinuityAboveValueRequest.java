package com.dkchallenge.springboot.controller.request;

public class SearchContinuityAboveValueRequest {

    private int indexBegin;
    private int indexEnd;
    private float threshold;
    private int winLength;

    public SearchContinuityAboveValueRequest(int indexBegin, int indexEnd, float threshold, int winLength) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.threshold = threshold;
        this.winLength = winLength;
    }

    public int getIndexBegin() {
        return indexBegin;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public float getThreshold() {
        return threshold;
    }

    public int getWinLength() {
        return winLength;
    }
}
