package UnitTests;


import com.dkchallenge.springboot.service.DataService;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

public class searchMultiContinuityWithinRangeTests {

    DataService dataService = new DataService("testCSVS/searchMultiContinuityWithinRangeTests.csv");

    @org.junit.Test
    public void testDataServiceSearchMultiContinuityWithinRangeSuccess(){
        Optional<ArrayList<int[]>> ThreeToFiveAndSevenToNine = dataService.returnAllIndicesWithinRange(dataService.getAyData(), 0, 9,(float)3.1,(float) 2,2);
        int[] ThreeToFive = {3,5};
        int[] SevenToEight = {7,8};
        assertArrayEquals(ThreeToFive, ThreeToFiveAndSevenToNine.get().get(0));
        assertArrayEquals(SevenToEight, ThreeToFiveAndSevenToNine.get().get(1));
    }

    @org.junit.Test
    public void testDataServicesearchMultiContinuityWithinRangeArrayListNotPresent(){
        Optional<ArrayList<int[]>> shouldNotBePresent = dataService.returnAllIndicesWithinRange(dataService.getWzData(), 0, 9,(float)100,(float) 99,1);
        assertFalse(shouldNotBePresent.get().isEmpty());
    }
//
//    @org.junit.Test
//    public void searchMultiContinuityWithinRangeOneRowResult(){
//        LinkedList<SwingData> data = dataService.getData("testCSVS/searchMultiContinuityWithinRangeTests.csv");
//        int[] shouldBeginAndEndSameIndex = dataService.returnIndicesWithinRange(data,0,8,(float)1.1,(float)0.9,1);
//        int[] correctIndices = {1,1};
//        assertArrayEquals(correctIndices, shouldBeginAndEndSameIndex);
//    }


}
