package UnitTests;

import com.dkchallenge.springboot.service.DataService;
import java.util.Optional;
import static org.junit.Assert.*;

public class searchContinuityAboveValueTwoSignalsTests {

    DataService dataService = new DataService("testCSVS/searchContinuityAboveValueTwoSignalsTests.csv");

    @org.junit.Test
    public void testDataServiceSearchContinuityAboveValueTwoSignalsSuccess(){
        Optional<Integer> shouldEqualThree = dataService.returnIndexOfDataWithinBothThresholds(dataService.getAzData(), dataService.getWyData(),0,5, (float)2,(float)1.9,2);
        shouldEqualThree.ifPresent(integer -> assertEquals(3, integer.intValue()));
    }

    @org.junit.Test
    public void testDataServiceSearchContinuityAboveValueTwoSignalsFalse(){
        Optional<Integer> shouldBeFalse = dataService.returnIndexOfDataWithinBothThresholds(dataService.getAzData(), dataService.getWyData(),0,5, (float)10,(float)19,2);
        assertFalse(shouldBeFalse.isPresent());
    }
}
