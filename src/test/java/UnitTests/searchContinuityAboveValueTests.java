//package UnitTests;
//
//import com.dkchallenge.springboot.model.SwingData;
//import com.dkchallenge.springboot.service.DataService;
//import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
//
//import java.util.LinkedList;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//public class searchContinuityAboveValueTests {
//
//    DataService dataService = new DataService();
//
//    @org.junit.Test
//    public void testDataServiceSuccess(){
//        LinkedList<SwingData> data = dataService.getData("testCSVs/searchContAboveValueTests.csv");
//        int shouldEqualFive = dataService.returnIndexOfContinuityAboveValue(data, 0, 5, (float) 4, 1);
//        assertEquals(5, shouldEqualFive);
//    }
//
//    @org.junit.Test
//    public void testDataServiceNull(){
//        LinkedList<SwingData> data = dataService.getData("testCSVs/searchContAboveValueTests.csv");
//        Integer noIndicesFound = dataService.returnIndexOfContinuityAboveValue(data,0, 5, (float) 10, 1);
//        assertNull(noIndicesFound);
//    }
//
//}
