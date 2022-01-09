// --== CS400 Project Three File Header ==--
// Name: Yang Qiu
// Email: qiu67@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun
import java.util.LinkedList;

/**
 * defines contents and methods of objects representing any Wisconsin State Park as vertices in the graph:
 * its name and a list of the shortest paths between this park and other
 * directly reachable Wisconsin State Parks.
 */
public class WIStatePark implements Park {
    // the name of the park
    private String name;
    // includes the shortest paths from this park to other Wisconsin State Park directly reachable from this park
    private LinkedList<Path> paths;

    /**
     * creates a new object of Wisconsin State Park.
     *
     * @param name the name of this park
     */
    public WIStatePark(String name) {
        this.name = name;
        paths = new LinkedList<Path>();
    }


    /**
     * retrieve this Wisconsin State Park's name in String.
     *
     * @return return the name of the park
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retrieve a collection of destination Wisconsin State Parks and distance between this park and destination parks.
     *
     * @return the linked list the shortest paths from this park to any directly reachable paths
     */
    @Override
    public LinkedList<Path> getPaths() {
        return paths;
    }

    /**
     * Change the linked list of this park that contains the shortest paths between this park and other Wisconsin State Parks directly reachable from this path
     *
     * @param paths the new collection to be set.
     * @return a boolean indicating if the mutator operation is successful.
     */
    @Override
    public boolean setPaths(LinkedList<Path> paths) {
        try {
            for (Path path : paths) {
                if (!path.getSource().equals(this.name)) {
                    throw new Exception("paths's source not match.");
                }
            }
            this.paths = paths;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * retrieve a simple representation in String of this Wisconsin National Park as vertex.
     * e.g. [YellowStone]
     *
     * @return the simple String representation of this park.
     */
    public String toString() {
        return "[" + name + "]";
    }

    /**
     * retrieve the String representation of this Wisconsin State Park that includes information of the path
     * e.g., "YellowStone[leads to: Smoky_Montains-1314 Grand_Canyon-1215]"
     *
     * @return the String representation of this Wisconsin State Park along with paths starting from it
     */
    public String toLongString() {
        String returnValue = name + "[leads to: "; // includes the name of this park
        for (Path path : paths) { // includes the names of directly reachable parks and corresponding path lengths in miles
            returnValue += (path.getTarget() + "-" + path.getWeight() + "km ");
        }
        returnValue += "]";
        return returnValue;
    }
}


