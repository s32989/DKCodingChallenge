package com.dkchallenge.springboot.controller;
import com.dkchallenge.springboot.controller.request.BackSearchContinuityWithinRangeRequest;
import com.dkchallenge.springboot.controller.request.SearchContinuityAboveValueTwoSignalsRequest;
import com.dkchallenge.springboot.controller.request.SearchContinuityAboveValueRequest;
import com.dkchallenge.springboot.controller.request.SearchMultiContinuityWithinRangeRequest;
import com.dkchallenge.springboot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class DKChallengeController {

    @Autowired
    DataService dataService;

    @GetMapping("/searchContinuityAboveValue")
    public Optional<Integer> searchContinuityAboveValue(@RequestBody SearchContinuityAboveValueRequest request) {
        return dataService.returnIndexOfContinuityAboveValue(request.getColumnName(), request.getIndexBegin(), request.getIndexEnd(), request.getThreshold(), request.getWinLength());
    }

    @GetMapping("/backSearchContinuityWithinRange")
    public Optional<Integer> backSearchContinuityWithinRange(@RequestBody BackSearchContinuityWithinRangeRequest request){
        return dataService.returnIndexOfContinuityWithinRange(request.getColumnName(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholdHi(), request.getThresholdLow(), request.getWinLength());
    }

    @GetMapping("/searchContinuityAboveValueTwoSignals")
    public Optional<Integer> searchContinuityAboveValueTwoSignals(@RequestBody SearchContinuityAboveValueTwoSignalsRequest request){
        return dataService.returnIndexOfDataWithinBothThresholds(request.getColumn1Name(), request.getColumn2Name(), request.getIndexBegin(), request.getIndexEnd(), request.getThreshold1(), request.getThreshold2(), request.getWinLength());
    }

    @GetMapping("/searchMultiContinuityWithinRange")
    public Optional<ArrayList<int[]>> searchMultiContinuityWithinRange(@RequestBody SearchMultiContinuityWithinRangeRequest request){
        return dataService.returnIndicesWithinRange(request.getColumnName(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholdHi(), request.getThresholdLow(), request.getWinLength());
    }

}