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
        ThreeToFiveAndSevenToNine.ifPresent(ints -> assertArrayEquals(ThreeToFive, ints.get(0)));
        ThreeToFiveAndSevenToNine.ifPresent(ints -> assertArrayEquals(SevenToEight, ints.get(1)));
    }

    @org.junit.Test
    public void testDataServiceSearchMultiContinuityWithinRangeArrayListIsEmpty(){
        Optional<ArrayList<int[]>> shouldNotBeEmpty = dataService.returnAllIndicesWithinRange(dataService.getWzData(), 0, 9,(float)100,(float) 99,2);
        shouldNotBeEmpty.ifPresent(ints -> assertTrue(ints.isEmpty()));
    }

    @org.junit.Test
    public void  testDataServiceSearchMultiContinuityWithinRangeOneRowResultLastRowOfData(){
        Optional<ArrayList<int[]>> shouldBeNineToNine = dataService.returnAllIndicesWithinRange(dataService.getWzData(), 0, 9,(float)4.1,(float) 3.9,1);
        int[] NineToNine = {9,9};
        assertArrayEquals(NineToNine, shouldBeNineToNine.get().get(0));
    }


}
