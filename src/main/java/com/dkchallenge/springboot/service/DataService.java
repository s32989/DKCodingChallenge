package com.dkchallenge.springboot.service;

import com.dkchallenge.springboot.constants.SearchFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class DataService {

    private HashMap<Integer, Float> axData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> ayData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> azData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> wxData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> wyData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> wzData = new HashMap<Integer, Float>();


    public DataService(String fileName) {
        this.getData(fileName);
    }
    public DataService(){
        this.getData("latestSwing.csv");
    }

    public HashMap<Integer, Float> getAxData() {
        return axData;
    }

    public HashMap<Integer, Float> getAyData() {
        return ayData;
    }

    public HashMap<Integer, Float> getAzData() {
        return azData;
    }

    public HashMap<Integer, Float> getWxData() {
        return wxData;
    }

    public HashMap<Integer, Float> getWyData() {
        return wyData;
    }

    public HashMap<Integer, Float> getWzData() {
        return wzData;
    }

    private void getData(String fileName) {
        String line = "";
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while ((line = br.readLine()) != null)
            {
                String[] swingDataSplit = line.split(",");
                populateSwingData(swingDataSplit, index);
                index++;
            }
        }
        catch (IOException e) {
            Logger logger  = LoggerFactory.getLogger(DataService.class);
            logger.error("Could not find file");
        }
    }

    public Optional<?> searchData(String searchFunction, HashMap<String, String> columns, int indexBegin, int indexEnd, HashMap<String, Float> thresholds, int winLength){

        Optional<?> data;
        switch(searchFunction){
            case SearchFunctions.searchContinuityAboveValue:
                data = returnIndexOfContinuityAboveValue(getColumnData(columns.get("column1")), indexBegin, indexEnd, thresholds.get("threshold"), winLength);
                break;
            case SearchFunctions.backSearchContinuityWithinRange:
                data = returnIndexOfContinuityWithinRange(getColumnData(columns.get("column1")), indexBegin, indexEnd, thresholds.get("thresholdHi"), thresholds.get("thresholdLow"), winLength);
                break;
            case SearchFunctions.searchContinuityAboveValueTwoSignals:
                data = returnIndexOfDataWithinBothThresholds(getColumnData(columns.get("column1")),getColumnData(columns.get("column2")), indexBegin, indexEnd, thresholds.get("threshold1"), thresholds.get("threshold2"), winLength);
                break;
            case SearchFunctions.searchMultiContinuityWithinRange:
                data = returnAllIndicesWithinRange(getColumnData(columns.get("column1")), indexBegin, indexEnd, thresholds.get("thresholdHi"), thresholds.get("thresholdLow"), winLength);
                break;
            default:
                data = Optional.empty();
                break;
        }
        return data;
    }

    public Optional<Integer> returnIndexOfContinuityAboveValue(HashMap<Integer, Float> columnData, int indexBegin, int indexEnd, float threshold, int winLength){

        Optional<Integer> index = Optional.empty();

        int counter = 0;

        for (int i = indexBegin; i <= indexEnd; i++){

            float dataPoint = columnData.get(i);

            if (dataPoint > threshold){
                counter++;
                if (counter == winLength){
                    index = Optional.of(i - winLength + 1);
                    return index;
                }
            } else {
                counter = 0;
            }
        }
        return index;
    }

    public Optional<Integer> returnIndexOfContinuityWithinRange(HashMap<Integer, Float> columnData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

        Optional<Integer> index = Optional.empty();
        int counter = 0;

        for(int i = indexBegin; i >= indexEnd; i--){

            float dataPoint = columnData.get(i);

            if (dataPoint > thresholdLow && dataPoint < thresholdHi){
                counter++;
                if (counter == winLength){
                    index = Optional.of(i - winLength + 1);
                    return index;
                }
            } else {
                counter = 0;
            }
        }

        return index;
    }
    public Optional<Integer> returnIndexOfDataWithinBothThresholds(HashMap<Integer, Float> column1Data, HashMap<Integer, Float> column2Data, int indexBegin, int indexEnd, float threshold1, float threshold2, int winLength){

        Optional<Integer> index = Optional.empty();
        int counter1 = 0;
        int counter2 = 0;

        for (int i = indexBegin; i <= indexEnd; i++){

            float dataPoint1 = column1Data.get(i);
            float dataPoint2 = column2Data.get(i);

            if (dataPoint1 > threshold1){
                counter1++;
            } else {
                counter1 = 0;
            }

            if (dataPoint2 > threshold2){
                counter2++;
            } else {
                counter2 = 0;
            }

            if (counter1 >= winLength && counter2 >= winLength){
                index = Optional.of(i - winLength + 1);
                return index;
            }
        }

        return index;
    }


    public Optional<ArrayList<int[]>> returnAllIndicesWithinRange(HashMap<Integer, Float> columnData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

        Optional<ArrayList<int[]>> listOfIndices = Optional.of(new ArrayList<>());

        int rowCounter = 0;
        boolean winLengthMet = false;
        int firstIndex = 0;
        int lastIndex;

        for(int i = indexBegin; i <= indexEnd; i++){

            float dataPoint = columnData.get(i);

            if (dataPoint > thresholdLow && dataPoint < thresholdHi){               //Data is within thresholds
                rowCounter++;
                if(rowCounter >= winLength) {

                    winLengthMet = true;
                    firstIndex = i - rowCounter + 1;
                    lastIndex = i;

                    if(i == indexEnd){                                              //last item of data on the list
                        listOfIndices.get().add(new int[]{firstIndex, lastIndex});
                    }
                }
            } else if (!(dataPoint > thresholdLow && dataPoint < thresholdHi)){     //Data is not within thresholds
                if (winLengthMet){
                    lastIndex = i  - 1;
                    listOfIndices.get().add(new int[]{firstIndex, lastIndex});
                }
                rowCounter = 0;
                winLengthMet = false;
            }
        }

        return listOfIndices;
    }

    private void populateSwingData(String[] data, int index){
        axData.put(index, Float.parseFloat(data[1]));
        ayData.put(index, Float.parseFloat(data[2]));
        azData.put(index, Float.parseFloat(data[3]));
        wxData.put(index, Float.parseFloat(data[4]));
        wyData.put(index, Float.parseFloat(data[5]));
        wzData.put(index, Float.parseFloat(data[6]));
    }

    private HashMap<Integer, Float> getColumnData(String columnName){
        HashMap<Integer, Float> columnData;
        switch(columnName.toLowerCase()){
            case ("ax"):
                columnData = axData;
                break;
            case ("ay"):
                columnData = ayData;
                break;
            case ("az"):
                columnData = azData;
                break;
            case ("wx"):
                columnData = wxData;
                break;
            case ("wy"):
                columnData = wyData;
                break;
            case ("wz"):
                columnData = wzData;
                break;
            default:
                columnData = null;
        }
        return columnData;
    }
}
