import java.io.IOException;
import java.util.ArrayList;

/**
 * as a data wrangler interface, contains necessary methods to be implemented in class CSVLoaderInterface
 */
interface CSVLoaderInterface {
    /**
     * loads information from a csv file
     *
     * @param csvPath the path of the csv file
     * @return a list of vertex objects retrieved from csv file data, each vertex object should contain its own list of edges
     * @throws IOException if the csv file is not found
     */
    ArrayList<WIStatePark> load(String csvPath) throws IOException;
}
