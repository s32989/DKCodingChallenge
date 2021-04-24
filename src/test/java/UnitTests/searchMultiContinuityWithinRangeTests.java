//package UnitTests;
//
//import com.dkchallenge.springboot.model.SwingData;
//import com.dkchallenge.springboot.service.DataService;
//import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
//
//import java.util.LinkedList;
//
//import static org.junit.Assert.*;
//
//public class searchMultiContinuityWithinRangeTests {
//
//    DataService dataService = new DataService();
//
//    @org.junit.Test
//    public void searchMultiContinuityWithinRangeSuccess(){
//        LinkedList<SwingData> data = dataService.getData("testCSVS/searchMultiContinuityWithinRangeTests.csv");
//        int[] shouldBeFromFourToEight = dataService.returnIndicesWithinRange(data,0,8,(float)3.9,(float)2.9,1);
//        int[] correctIndices = {4,8};
//        assertArrayEquals(correctIndices, shouldBeFromFourToEight);
//
//    }
//
//    @org.junit.Test
//    public void searchMultiContinuityWithinRangeEmptyArray(){
//        LinkedList<SwingData> data = dataService.getData("testCSVS/searchMultiContinuityWithinRangeTests.csv");
//        int[]shouldBeNull = dataService.returnIndicesWithinRange(data,0,8,(float)100,(float)99,1);
//        assertEquals(0, shouldBeNull.length);
//    }
//
//    @org.junit.Test
//    public void searchMultiContinuityWithinRangeOneRowResult(){
//        LinkedList<SwingData> data = dataService.getData("testCSVS/searchMultiContinuityWithinRangeTests.csv");
//        int[] shouldBeginAndEndSameIndex = dataService.returnIndicesWithinRange(data,0,8,(float)1.1,(float)0.9,1);
//        int[] correctIndices = {1,1};
//        assertArrayEquals(correctIndices, shouldBeginAndEndSameIndex);
//    }
//
//
//}
