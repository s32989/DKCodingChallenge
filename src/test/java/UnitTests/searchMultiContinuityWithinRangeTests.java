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
    public void testDataServicesearchMultiContinuityWithinRangeArrayListIsEmpty(){
        Optional<ArrayList<int[]>> shouldNotBePresent = dataService.returnAllIndicesWithinRange(dataService.getWzData(), 0, 9,(float)100,(float) 99,2);
        assertTrue(shouldNotBePresent.get().isEmpty());
    }

    @org.junit.Test
    public void searchMultiContinuityWithinRangeOneRowResultLastRowOfData(){
        Optional<ArrayList<int[]>> shouldBeNineToNine = dataService.returnAllIndicesWithinRange(dataService.getWzData(), 0, 9,(float)4.1,(float) 3.9,1);
        int[] NineToNine = {9,9};
        assertArrayEquals(NineToNine, shouldBeNineToNine.get().get(0));
    }


}
