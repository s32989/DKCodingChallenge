package UnitTests;

import com.dkchallenge.springboot.model.SwingData;
import com.dkchallenge.springboot.service.DataService;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class backSearchContinuityWithinRangeTests {

    DataService dataService = new DataService();

    @org.junit.Test
    public void testDataServiceSuccessBackwardsSearch(){
        LinkedList<SwingData> data = dataService.getData("testCSVS/backSearchContAboveValueTests.csv");
        int shouldEqualFive = dataService.returnIndexOfContinuityWithinRange(data,6,0,(float) 4.1,(float)3.9,1);
        assertEquals(5, shouldEqualFive);
    }

    @org.junit.Test
    public void testDataServiceSuccessBackwardsSearchNull(){
        LinkedList<SwingData> data = dataService.getData("testCSVs/backSearchContAboveValueTests.csv");
        Integer shouldBeNull = dataService.returnIndexOfContinuityWithinRange(data,6,0,(float) -100,(float) -99,1);
        assertNull(shouldBeNull);
    }
}
