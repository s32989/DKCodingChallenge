package com.dkchallenge.springboot.controller;
import com.dkchallenge.springboot.constants.SearchFunctions;
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
        return dataService.searchData(SearchFunctions.searchContinuityAboveValue, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

    @GetMapping("/backSearchContinuityWithinRange")
    public Optional<?> backSearchContinuityWithinRange(@RequestBody SearchRequest request){
        return dataService.searchData(SearchFunctions.backSearchContinuityWithinRange, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

    @GetMapping("/searchContinuityAboveValueTwoSignals")
    public Optional<?> searchContinuityAboveValueTwoSignals(@RequestBody SearchRequest request){
        return dataService.searchData(SearchFunctions.searchContinuityAboveValueTwoSignals, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

    @GetMapping("/searchMultiContinuityWithinRange")
    public Optional<?> searchMultiContinuityWithinRange(@RequestBody SearchRequest request){
        return dataService.searchData(SearchFunctions.searchMultiContinuityWithinRange, request.getColumns(), request.getIndexBegin(), request.getIndexEnd(), request.getThresholds(), request.getWinLength());
    }

}