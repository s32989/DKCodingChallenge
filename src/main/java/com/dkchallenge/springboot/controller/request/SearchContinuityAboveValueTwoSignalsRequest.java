package com.dkchallenge.springboot.controller.request;

import java.util.HashMap;

public class SearchContinuityAboveValueTwoSignalsRequest extends SearchRequest{

    private final HashMap<String, Float> thresholds;
    private final HashMap<String, String> columns;

    public SearchContinuityAboveValueTwoSignalsRequest(int indexBegin, int indexEnd, int winLength, HashMap<String, Float> thresholds, HashMap<String, String> columns) {
        super(indexBegin, indexEnd, winLength);
        this.columns = columns;
        this.thresholds = thresholds;
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

    public String getColumn1Name(){
        return columns.get("column1");
    }

    public String getColumn2Name(){
        return columns.get("column2");
    }

    public float getThreshold1(){
        return thresholds.get("threshold1");
    }

    public float getThreshold2(){
        return thresholds.get("threshold2");
    }
}
