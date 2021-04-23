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
                    return index = Optional.of(i - winLength);
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
                    return index = Optional.of(i - winLength);
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
                index = Optional.of(i - winLength);
                return index;
            }
        }

        return index;
    }

    public Optional <int[]> returnIndicesWithinRange(HashMap<Integer, Float> swingData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

//        ArrayList<Integer>indexList = makeListOfIndicesThatMeetConditions(swingData, indexBegin, indexEnd, thresholdHi, thresholdLow, winLength);
//
//        return checkListOfIndicesForMultiContinuity(indexList);
    }

    private SwingData populateSwingData(String[] data, int index){
        axData.put(index, Float.parseFloat(data[1]));
        ayData.put(index, Float.parseFloat(data[2]));
        azData.put(index, Float.parseFloat(data[3]));
        wxData.put(index, Float.parseFloat(data[4]));
        wyData.put(index, Float.parseFloat(data[5]));
        wzData.put(index, Float.parseFloat(data[6]));
    }

    private ArrayList<Float> addSwingDataToList(LinkedList<SwingData> allOfTheSwingData, int index){
        ArrayList<Float> swingParameters = new ArrayList<Float>();
        SwingData data = allOfTheSwingData.get(index);
        swingParameters.add(data.getAx());
        swingParameters.add(data.getAy());
        swingParameters.add(data.getAz());
        swingParameters.add(data.getWx());
        swingParameters.add(data.getWy());
        swingParameters.add(data.getWz());

        return swingParameters;
    }

    private ArrayList<Integer> makeListOfIndicesThatMeetConditions(LinkedList<SwingData> allTheSwingData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

        int thresholdsMetCounter = 0;
        ArrayList<Integer>indexList = new ArrayList<>();
        for(int i = indexBegin; i <= indexEnd; i++){
            ArrayList<Float> swingParameters = addSwingDataToList(allTheSwingData, i);
            for(Float swingParameter : swingParameters){
                if (swingParameter > thresholdLow && swingParameter < thresholdHi){
                    thresholdsMetCounter++;
                    if (thresholdsMetCounter >= winLength){
                        indexList.add(i);
                        break;
                    }
                }
            }
            thresholdsMetCounter = 0;
        }

        return indexList;
    }

    private int[] checkListOfIndicesForMultiContinuity(ArrayList<Integer> indexList){
        int[] indices = new int[2];
        int consecutiveRows = 1;
        int largestNumberOfContinuousIndicesSoFar = 0;
        int indexLast = 0;

        if (indexList.size() == 1){
            indices[0] = indexList.get(0);
            indices[1] = indexList.get(0);
            return indices;
        } else if (indexList.size() == 0){                          //if index list is size 0 condition never met
            return new int[0];
        } else {
            for(int i = 0; i < indexList.size() -1; i++){
                if(indexList.get(i) == indexList.get(i + 1) - 1){
                    consecutiveRows++;
                    if (consecutiveRows > largestNumberOfContinuousIndicesSoFar){
                        largestNumberOfContinuousIndicesSoFar = consecutiveRows;
                        indexLast = indexList.get(i + 1);
                    }
                } else {
                    consecutiveRows = 1;
                }
            }
        }
        int indexFirst = (indexLast - largestNumberOfContinuousIndicesSoFar) + 1;
        indices[0] = indexFirst;
        indices[1] = indexLast;
        return indices;
    }

}
