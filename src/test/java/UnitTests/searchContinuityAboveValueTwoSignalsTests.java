package UnitTests;

import com.dkchallenge.springboot.model.SwingData;
import com.dkchallenge.springboot.service.DataService;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class searchContinuityAboveValueTwoSignalsTests {

    DataService dataService = new DataService();

    @org.junit.Test
    public void testSearchContinuityAboveValueTwoSignalsSuccess(){
        LinkedList<SwingData> data1 = dataService.getData("testCSVS/searchContinuityAboveValueTwoSignalsTests(1).csv");
        LinkedList<SwingData> data2 = dataService.getData("testCSVS/searchContinuityAboveValueTwoSignalsTests(2).csv");
        int shouldEqualTwo = dataService.returnIndexOfDataWithinBothThresholds(data1,data2,0,4, (float)1.9,(float)1.9,1);
        assertEquals(2, shouldEqualTwo);
    }

    @org.junit.Test
    public void testSearchContinuityAboveValueTwoSignalsNull(){
        LinkedList<SwingData> data1 = dataService.getData("testCSVS/searchContinuityAboveValueTwoSignalsTests(1).csv");
        LinkedList<SwingData> data2 = dataService.getData("testCSVS/searchContinuityAboveValueTwoSignalsTests(2).csv");
        Integer shouldBeNull = dataService.returnIndexOfDataWithinBothThresholds(data1,data2,0,4, (float)100,(float)100,1);
        assertNull(shouldBeNull);
    }
}
