package UnitTests;

import com.dkchallenge.springboot.service.DataService;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class searchContinuityAboveValueTests {

    DataService dataService = new DataService("testCSVs/searchContAboveValueTests.csv");

    @org.junit.Test
    public void testDataServiceReturnIndexOfContinuityAboveValueSuccess(){
        Optional<Integer> shouldEqualTwo = dataService.returnIndexOfContinuityAboveValue(dataService.getAxData(),0, 5, (float) 1.9,2);
        shouldEqualTwo.ifPresent(integer -> assertEquals(2, integer.intValue()));
    }

    @org.junit.Test
    public void testDataServiceReturnIndexOfContinuityAboveValueFalse() {
        Optional<Integer> shouldBeFalse = dataService.returnIndexOfContinuityAboveValue(dataService.getWxData(), 0, 5, (float) 7, 2);
        assertFalse(shouldBeFalse.isPresent());
    }
}
