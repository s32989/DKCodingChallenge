package UnitTests;

import com.dkchallenge.springboot.service.DataService;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class backSearchContinuityWithinRangeTests {

    DataService dataService = new DataService("testCSVs/backSearchContAboveValueTests.csv");

    @org.junit.Test
    public void testDataServiceSuccessBackwardsSearch(){
        Optional<Integer> shouldEqualFive = dataService.returnIndexOfContinuityWithinRange(dataService.getAxData(),6, 0, (float) 4.9,(float)3.9,1);
        shouldEqualFive.ifPresent(integer -> assertEquals(5, integer.intValue()));
    }

    @org.junit.Test
    public void testDataServiceSuccessBackwardsSearchNotPresent(){
        Optional<Integer> shouldBeFalse = dataService.returnIndexOfContinuityWithinRange(dataService.getAxData(),6, 0, (float) -100,(float) -99,1);
        assertFalse(shouldBeFalse.isPresent());
    }
}
