package com.dkchallenge.springboot.controller;
import com.dkchallenge.springboot.constants.searchFunctions;
import com.dkchallenge.springboot.controller.request.SearchRequest;
import com.dkchallenge.springboot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class DKChallengeController {


    @Autowired
    DataService dataService;

    @GetMapping("/searchContinuityAboveValue")
    public Optional<?> searchContinuityAboveValue(@RequestBody SearchRequest request) {
        return dataService.searchData(searchFunctions.searchContinuityAboveValue, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

    @GetMapping("/backSearchContinuityWithinRange")
    public Optional<?> backSearchContinuityWithinRange(@RequestBody SearchRequest request){
        return dataService.searchData(searchFunctions.backSearchContinuityWithinRange, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

    @GetMapping("/searchContinuityAboveValueTwoSignals")
    public Optional<?> searchContinuityAboveValueTwoSignals(@RequestBody SearchRequest request){
        return dataService.searchData(searchFunctions.searchContinuityAboveValueTwoSignals, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

    @GetMapping("/searchMultiContinuityWithinRange")
    public Optional<?> searchMultiContinuityWithinRange(@RequestBody SearchRequest request){
        return dataService.searchData(searchFunctions.searchMultiContinuityWithinRange, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

}