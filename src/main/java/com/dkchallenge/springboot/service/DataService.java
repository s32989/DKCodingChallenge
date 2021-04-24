package com.dkchallenge.springboot.service;

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

    public DataService() {
        this.getData();
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

    private void getData() {
        String line = "";
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("latestSwing.csv"))){
            while ((line = br.readLine()) != null)
            {
                String[] swingDataSplit = line.split(",");
                populateSwingData(swingDataSplit, index);
                index++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Integer> returnIndexOfContinuityAboveValue(String columnName, int indexBegin, int indexEnd, float threshold, int winLength){

        HashMap<Integer, Float> columnData = getColumnData(columnName);

        Optional<Integer> index = Optional.empty();

        int counter = 0;

        for( int i = indexBegin; i <= indexEnd; i++){

            float dataPoint = columnData.get(i);

            if (dataPoint > threshold){
                counter++;
                if (counter == winLength){
                    index = Optional.of(i - winLength + 1);
                    return index;
                }
            }else{
                counter = 0;
            }
        }
        return index;
    }

    public Optional<Integer> returnIndexOfContinuityWithinRange(String columnName, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

        HashMap<Integer, Float> columnData = getColumnData(columnName);

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
            }else{
                counter = 0;
            }
        }

        return index;
    }
    public Optional<Integer> returnIndexOfDataWithinBothThresholds(String column1Name, String column2Name, int indexBegin, int indexEnd, float threshold1, float threshold2, int winLength){

        HashMap<Integer, Float> column1Data = getColumnData(column1Name);
        HashMap<Integer, Float> column2Data = getColumnData(column2Name);

        Optional<Integer> index = Optional.empty();
        int counter1 = 0;
        int counter2 = 0;

        for(int i = indexBegin; i <= indexEnd; i++){

            float dataPoint1 = column1Data.get(i);
            float dataPoint2 = column2Data.get(i);

            if(dataPoint1 > threshold1){
                counter1++;
            } else {
                counter1 = 0;
            }

            if(dataPoint2 > threshold2){
                counter2++;
            } else {
                counter2 = 0;
            }

            if(counter1 >= winLength && counter2 >= winLength){
                index = Optional.of(i - winLength + 1);
                return index;
            }
        }

        return index;
    }

    public Optional<ArrayList<int[]>> returnIndicesWithinRange(String columnName, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

        HashMap<Integer, Float> columnData = getColumnData(columnName);

        Optional<ArrayList<int[]>> listOfIndices = Optional.of(new ArrayList<int[]>());

        int rowCounter = 0;
        boolean winLengthMet = false;
        int firstIndex = 0;
        int lastIndex = 0;

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
                if(winLengthMet){
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
        HashMap<Integer, Float> nullHashMap = new HashMap<Integer, Float>();
        switch(columnName.toLowerCase()){
            case ("ax"):
                return axData;
            case ("ay"):
                return ayData;
            case ("az"):
                return azData;
            case ("wx"):
                return wxData;
            case ("wy"):
                return wyData;
            case ("wz"):
                return wzData;
            default:
                return nullHashMap;
        }
    }
}
