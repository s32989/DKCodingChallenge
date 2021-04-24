package com.dkchallenge.springboot.controller.request;

import java.util.HashMap;

public class BackSearchContinuityWithinRangeRequest extends SearchRequest {

    private final HashMap<String, Float> thresholds;
    private final HashMap<String, String> columns;

    public BackSearchContinuityWithinRangeRequest(int indexBegin, int indexEnd, int winLength, HashMap<String, Float> thresholds, HashMap<String, String> columns) {
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

    public float getThresholdHi(){
        return thresholds.get("thresholdHi");
    }

    public float getThresholdLow(){
        return thresholds.get("thresholdLow");
    }

    public String getColumnName(){
        return columns.get("column1");
    }
}
