package com.dkchallenge.springboot.controller;
import com.dkchallenge.springboot.controller.request.BackSearchContinuityWithinRangeRequest;
import com.dkchallenge.springboot.controller.request.SearchContinuityAboveValueTwoSignalsRequest;
import com.dkchallenge.springboot.controller.request.SearchContinuityAboveValueRequest;
import com.dkchallenge.springboot.controller.request.SearchMultiContinuityWithinRangeRequest;
import com.dkchallenge.springboot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DKChallengeController {

    @Autowired
    DataService dataService;

    @GetMapping("/searchContinuityAboveValue")
    public Integer searchContinuityAboveValue(@RequestBody SearchContinuityAboveValueRequest request) {
        return dataService.returnIndexOfContinuityAboveValue(dataService.getData("latestSwing.csv"), request.getIndexBegin(), request.getIndexEnd(), request.getThreshold(), request.getWinLength());
    }

    @GetMapping("/backSearchContinuityWithinRange")
    public Integer backSearchContinuityWithinRange(@RequestBody BackSearchContinuityWithinRangeRequest request){
        return dataService.returnIndexOfContinuityWithinRange(dataService.getData("latestSwing.csv"), request.getIndexBegin(), request.getIndexEnd(), request.getThresholdHi(), request.getThresholdLow(), request.getWinLength());
    }

    @GetMapping("/searchContinuityAboveValueTwoSignals")
    public Integer searchContinuityAboveValueTwoSignals(@RequestBody SearchContinuityAboveValueTwoSignalsRequest request){
        return dataService.returnIndexOfDataWithinBothThresholds(dataService.getData("latestSwing.csv"), dataService.getData("swingData2.csv"), request.getIndexBegin(), request.getIndexEnd(), request.getThreshold1(), request.getThreshold2(), request.getWinLength());
    }

    @GetMapping("/searchMultiContinuityWithinRange")
    public int[] searchMultiContinuityWithinRange(@RequestBody SearchMultiContinuityWithinRangeRequest request){
        return dataService.returnIndicesWithinRange(dataService.getData("latestSwing.csv"), request.getIndexBegin(), request.getIndexEnd(), request.getThresholdHi(), request.getThresholdLow(), request.getWinLength());
    }

}