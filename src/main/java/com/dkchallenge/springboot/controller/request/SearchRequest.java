package com.dkchallenge.springboot.controller.request;

import java.util.HashMap;

public class SearchRequest {
    private final int indexBegin;
    private final int indexEnd;
    private final int winLength;
    private final HashMap<String, Float> thresholds;
    private final HashMap<String, String> columns;

    public SearchRequest(int indexBegin, int indexEnd, int winLength, HashMap<String, Float> thresholds, HashMap<String, String> columns) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.winLength = winLength;
        this.thresholds = thresholds;
        this.columns = columns;
    }

    public int getIndexBegin() {
        return indexBegin;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public int getWinLength() {
        return winLength;
    }

    public HashMap<String, Float> getThresholds() {
        return thresholds;
    }

    public HashMap<String, String> getColumns() {
        return columns;
    }
}