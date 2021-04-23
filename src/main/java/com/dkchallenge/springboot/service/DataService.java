package com.dkchallenge.springboot.service;

import com.dkchallenge.springboot.model.SwingData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

@Service
public class DataService {

    public LinkedList<SwingData> getData(String fileName){
        LinkedList<SwingData> swingDataList = new LinkedList<SwingData>();

        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while ((line = br.readLine()) != null)
            {
                String[] swingDataSplit = line.split(",");
                swingDataList.add(ModelSwingData(swingDataSplit));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return swingDataList;
    }

    public Integer returnIndexOfContinuityAboveValue(LinkedList<SwingData> swingData, int indexBegin, int indexEnd, float threshold, int winLength){
        Integer index = null;
        int counter = 0;
        for(int i = indexBegin; i <= indexEnd; i++){

            ArrayList<Float> swingParameters = addSwingDataToList(swingData, i);

            for(int j = 0; j < swingParameters.size(); j++){
                if (swingParameters.get(j) > threshold){
                    counter++;
                    if(counter >= winLength){
                        index = i;
                        return index;
                    }
                }
            }
            counter = 0;
        }

        return index;
    }

    public Integer returnIndexOfContinuityWithinRange(LinkedList<SwingData> swingData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){
        Integer index = null;
        int counter = 0;
        for(int i = indexBegin; i >= indexEnd; i--){

            ArrayList<Float> swingParameters = addSwingDataToList(swingData, i);

            for (Float swingParameter : swingParameters) {
                if (swingParameter > thresholdLow && swingParameter < thresholdHi) {
                    counter++;
                    if (counter >= winLength) {
                        index = i;
                        return index;
                    }
                }
            }
            counter = 0;
        }

        return index;
    }
    public Integer returnIndexOfDataWithinBothThresholds(LinkedList<SwingData> swingData1, LinkedList<SwingData> swingData2, int indexBegin, int indexEnd, float threshold1, float threshold2, int winLength){
        Integer index = null;
        int counter1 = 0;
        int counter2 = 0;

        for(int i = indexBegin; i <= indexEnd; i++){
            ArrayList<Float> swingParameters1 = addSwingDataToList(swingData1, i);
            ArrayList<Float> swingParameters2 = addSwingDataToList(swingData2, i);

            for(Float swingParameter : swingParameters1){
                if(swingParameter > threshold1){
                    counter1++;
                }
            }

            for(Float swingParameter : swingParameters2){
                if(swingParameter > threshold2){
                    counter2++;
                }
            }

            if(counter1 >= winLength && counter2 >= winLength){
                index = i;
                return index;
            }

            counter1 = 0;
            counter2 = 0;

        }

        return index;
    }

    public int[] returnIndicesWithinRange(LinkedList<SwingData> swingData, int indexBegin, int indexEnd, float thresholdHi, float thresholdLow, int winLength){

        ArrayList<Integer>indexList = makeListOfIndicesThatMeetConditions(swingData, indexBegin, indexEnd, thresholdHi, thresholdLow, winLength);

        return checkListOfIndicesForMultiContinuity(indexList);
    }

    private SwingData ModelSwingData(String[] data){
        long time = Long.parseLong(data[0]);
        float ax = Float.parseFloat(data[1]);
        float ay = Float.parseFloat(data[2]);
        float az = Float.parseFloat(data[3]);
        float wx = Float.parseFloat(data[4]);
        float wy = Float.parseFloat(data[5]);
        float wz = Float.parseFloat(data[6]);

        return new SwingData(time, ax, ay, az, wx, wy, wz);
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
