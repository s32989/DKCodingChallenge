package com.dkchallenge.springboot.service;

import com.dkchallenge.springboot.model.SwingData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class DataService {

    private HashMap<Integer, Float> axData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> ayData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> azData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> wxData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> wyData = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> wzData = new HashMap<Integer, Float>();

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

    public HashMap<Integer,SwingData> getData(String fileName){
        HashMap<Integer, SwingData> swingDataHashMap = new HashMap<Integer, SwingData>();
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
            e.printStackTrace();
        }

        return swingDataHashMap;
    }

    public Optional<Integer> returnIndexOfContinuityAboveValue(HashMap<Integer,Float> swingData, int indexBegin, int indexEnd, float threshold, int winLength){

        Optional<Integer> index = Optional.empty();
        int counter = 0;

        for( int i = indexBegin; i <= indexEnd; i++){

            float dataPoint = swingData.get(i);

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

    public Optional<Integer> returnIndexOfContinuityWithinRange(HashMap<Integer, Float> swingData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){
        Optional<Integer> index = Optional.empty();
        int counter = 0;
        for(int i = indexBegin; i >= indexEnd; i--){

            float dataPoint = swingData.get(i);

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
    public Optional<Integer> returnIndexOfDataWithinBothThresholds(HashMap<Integer, Float> swingData1, HashMap<Integer, Float> swingData2, int indexBegin, int indexEnd, float threshold1, float threshold2, int winLength){

        Optional<Integer> index = Optional.empty();
        int counter1 = 0;
        int counter2 = 0;

        for(int i = indexBegin; i <= indexEnd; i++){
            float dataPoint1 = swingData1.get(i);
            float dataPoint2 = swingData2.get(i);

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

    public ArrayList<int[]> returnIndicesWithinRange(HashMap<Integer, Float> swingData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

        ArrayList<int[]> indicesWithinRange = new ArrayList<int[]>();

        int rowCounter = 0;
        boolean winLengthMet = false;
        int firstIndex = 0;
        int lastIndex = 0;

        for(int i = indexBegin; i <= indexEnd; i++){

            float dataPoint = swingData.get(i);

            if (dataPoint > thresholdLow && dataPoint < thresholdHi){
                rowCounter++;
                if(rowCounter >= winLength) {

                    winLengthMet = true;
                    firstIndex = i - rowCounter + 1;
                    lastIndex = i;

                    if(i == indexEnd){
                        indicesWithinRange.add(new int[]{firstIndex, lastIndex});
                    }
                }
            } else if (!(dataPoint > thresholdLow && dataPoint < thresholdHi)){
                if(winLengthMet){
                    lastIndex = i  - 1;
                    indicesWithinRange.add(new int[]{firstIndex, lastIndex});
                }
                rowCounter = 0;
                winLengthMet = false;
            }
        }
        
        return indicesWithinRange;
    }

    private void populateSwingData(String[] data, int index){
        axData.put(index, Float.parseFloat(data[1]));
        ayData.put(index, Float.parseFloat(data[2]));
        azData.put(index, Float.parseFloat(data[3]));
        wxData.put(index, Float.parseFloat(data[4]));
        wyData.put(index, Float.parseFloat(data[5]));
        wzData.put(index, Float.parseFloat(data[6]));
    }
}
