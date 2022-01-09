import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PathSystemAppTest {
    /**
     * DataWrangler Test for csvLoader method.
     */
    @Test
    public void dataWrangler_csvLoader() throws IOException {
        CSVLoader csvLoader = new CSVLoader();
        ArrayList<WIStatePark> data = csvLoader.load("./WiscStateparks.csv");
        assertEquals(7, data.size());
        assertEquals(4, data.get(0).getPaths().size());
    }

    /**
     * DataWrangler Test for toLongString method.
     */
    @Test
    public void dataWrangler_toLongString() throws IOException {
        CSVLoader csvLoader = new CSVLoader();
        ArrayList<WIStatePark> data = csvLoader.load("./WiscStateparks.csv");
        assertEquals("Aztalan State Park[leads to: Amnicon Falls State Park-533km "
            + "Belmont Mound State Park-153km " + "Big Bay State Park-566km "
            + "Lake Kegonsa State Park-45km ]", data.get(0).toLongString());
    }

    /**
     * DataWrangler Test for setPaths method.
     */
    @Test
    public void dataWrangler_setPaths() {
        WIStatePark wiStatePark = new WIStatePark("ERIC");
        ParkPath path = new ParkPath("ERIC", "MIKE", 3);
        LinkedList<Path> paths = new LinkedList();
        paths.add(path);
        wiStatePark.setPaths(paths);
        assertEquals("MIKE", wiStatePark.getPaths().get(0).getTarget());
    }

    // Back End Developer Tests
    @Test
    /**
     * BackEnd Developer Test for getShortestPath
     */
    public void BackEndDeveloper_getShortestPath() {
        try{
            CSVLoader csvLoader = new CSVLoader();
            ArrayList<WIStatePark> data = csvLoader.load("./WiscStateparks.csv");
            BackEnd backEndList = new BackEnd(data);
            assertEquals(backEndList.getShortestPath("Amnicon Falls State Park", "Big Bay State Park").toString(),
                    "[Amnicon Falls State Park, Big Bay State Park]");
        }catch (Exception e){
            fail("fail to getDirectReach ");
        }
    }

    @Test
    /**
     * BackEnd Developer Test for getShortestDistance
     */
    public void BackEndDeveloper_getShortestDistance(){
        try{
            CSVLoader csvLoader = new CSVLoader();
            ArrayList<WIStatePark> data = csvLoader.load("./WiscStateparks.csv");
            BackEnd backEndList = new BackEnd(data);
            assertEquals(backEndList.getShortestDistance("Aztalan State Park", "Big Bay State Park"),547);
            assertEquals(backEndList.getShortestDistance("Belmont Mound State Park", "Big Bay State Park"),394);
            assertEquals(backEndList.getShortestDistance("Amnicon Falls State Park", "Big Bay State Park"),122);
        }catch (Exception e){
            fail("fail to getDirectReach ");
        }
    }

    @Test
    /**
     * BackEnd Developer Test for getDirectReach
     */
    public void BackEndDeveloper_getDirectReach() {
        try{
            CSVLoader csvLoader = new CSVLoader();
            ArrayList<WIStatePark> data = csvLoader.load("./WiscStateparks.csv");
            BackEnd backEndList = new BackEnd(data);
            assertEquals(backEndList.getDirectReach("Aztalan State Park").toString(),
                    "[Aztalan State Park-(533)->" +
                    "Amnicon Falls State Park., Aztalan State Park-(153)->" +
                    "Belmont Mound State Park., Aztalan State Park-(566)->" +
                    "Big Bay State Park., Aztalan State Park-(45)->Lake Kegonsa State Park.]");
        }catch (Exception e){
            fail("fail to getDirectReach ");
        }
    }

    @Test
    /**
     * BackEnd Developer Test for addPath
     */
    public void BackEndDeveloper_addPath() {
        try{
            CSVLoader csvLoader = new CSVLoader();
            ArrayList<WIStatePark> data = csvLoader.load("./WiscStateparks.csv");
            BackEnd backEndList = new BackEnd(data);
            assertTrue(backEndList.addPath("Aztalan State Park","Buckhorn State Park",999));
            WIStatePark testPark = new WIStatePark("Buckhorn State Park");
            assertEquals(backEndList.getDirectReach("Aztalan State Park").toString(), "[Aztalan State " +
                    "Park-(533)->Amnicon Falls State Park., Aztalan State Park-(153)->Belmont Mound State Park., " +
                    "Aztalan State Park-(566)->Big Bay State Park., Aztalan State Park-(45)->" +
                    "Lake Kegonsa State Park., Aztalan State Park-(999)->Buckhorn State Park.]");
        }catch (Exception e){
            fail("fail to getDirectReach ");
        }
    }

    @Test
    /**
     * BackEnd Developer Test for deletePath
     */
    public void BackEndDeveloper_deletePath() throws IOException {
        CSVLoader csvLoader = new CSVLoader();
        ArrayList<WIStatePark> data = csvLoader.load("./WiscStateparks.csv");
        BackEnd backEndList = new BackEnd(data);
        assertTrue(backEndList.deletePath("Aztalan State Park","Buckhorn State Park"));
        assertEquals(backEndList.getDirectReach("Aztalan State Park").toString(), "[Aztalan " +
                "State Park-(533)->Amnicon Falls State Park., Aztalan State Park-(153)->Belmont Mound State Park., " +
                "Aztalan State Park-(566)->Big Bay State Park., Aztalan State Park-(45)->Lake Kegonsa State Park.]");
    }
    // Front End Developer Tests

    // Integration Manager Tests

}
