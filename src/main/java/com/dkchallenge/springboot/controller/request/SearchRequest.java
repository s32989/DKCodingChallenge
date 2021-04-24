package com.dkchallenge.springboot.controller.request;

import java.util.HashMap;

public abstract class SearchRequest {
    private final int indexBegin;
    private final int indexEnd;
    private final int winLength;

    public SearchRequest(int indexBegin, int indexEnd, int winLength) {
        this.indexBegin = indexBegin;
        this.indexEnd = indexEnd;
        this.winLength = winLength;
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
}