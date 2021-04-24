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
import java.util.Set;


@RestController
public class DKChallengeController {

    @Autowired
    DataService dataService;

    @GetMapping("/searchContinuityAboveValue")
    public Optional<Integer> searchContinuityAboveValue(@RequestBody SearchContinuityAboveValueRequest request) {
        dataService.getData("latestSwing.csv");
        return dataService.returnIndexOfContinuityAboveValue(dataService.getAxData(), request.getIndexBegin(), request.getIndexEnd(), request.getThreshold(), request.getWinLength());
    }

    @GetMapping("/backSearchContinuityWithinRange")
    public Optional<Integer> backSearchContinuityWithinRange(@RequestBody BackSearchContinuityWithinRangeRequest request){
        dataService.getData("latestSwing.csv");
        return dataService.returnIndexOfContinuityWithinRange(dataService.getAxData(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholdHi(), request.getThresholdLow(), request.getWinLength());
    }

    @GetMapping("/searchContinuityAboveValueTwoSignals")
    public Optional<Integer> searchContinuityAboveValueTwoSignals(@RequestBody SearchContinuityAboveValueTwoSignalsRequest request){
        dataService.getData("latestSwing.csv");
        return dataService.returnIndexOfDataWithinBothThresholds(dataService.getAxData(), dataService.getAyData(), request.getIndexBegin(), request.getIndexEnd(), request.getThreshold1(), request.getThreshold2(), request.getWinLength());
    }

    @GetMapping("/searchMultiContinuityWithinRange")
    public ArrayList<int[]> searchMultiContinuityWithinRange(@RequestBody SearchMultiContinuityWithinRangeRequest request){
        dataService.getData("latestSwing.csv");
        return dataService.returnIndicesWithinRange(dataService.getAxData(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholdHi(), request.getThresholdLow(), request.getWinLength());
    }

}