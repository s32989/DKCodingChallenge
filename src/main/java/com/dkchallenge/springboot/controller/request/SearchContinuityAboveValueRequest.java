package com.dkchallenge.springboot.controller.request;

import java.util.HashMap;

public class SearchContinuityAboveValueRequest extends SearchRequest{

    private final HashMap<String, Float> thresholds;
    private final HashMap<String, String> columns;

    public SearchContinuityAboveValueRequest(int indexBegin, int indexEnd, int winLength, HashMap<String, Float> thresholds, HashMap<String, String> columns) {
        super(indexBegin, indexEnd, winLength);
        this.thresholds = thresholds;
        this.columns = columns;
    }

    @Override
    public int getIndexBegin() {
        return super.getIndexBegin();
    }

    @Override
    public int getIndexEnd() {
        return super.getIndexEnd();
    }

    @Override
    public int getWinLength() {
        return super.getWinLength();
    }

    public float getThreshold(){
        return thresholds.get("threshold");
    }

    public String getColumnName(){
        return columns.get("column1");
    }
}
