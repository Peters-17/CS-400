import java.util.LinkedList;

/**
 * as a data wrangler interface, contains necessary methods to be implemented in class WIStatePark
 */
interface Park {
    /**
     * retrieve this park's name in String.
     *
     * @return return the name of the park
     */
    public String getName();

    /**
     * retrieve a list of the shortest paths from this park to any other directly reachable Wisconsin State Parks
     *
     * @return LinkedList<Path>
     */
    public LinkedList<Path> getPaths();

    /**
     * change the linked list of the shortest paths between this park and any other Wisconsin State Parks directly reachable from this park
     *
     * @param paths the new linked list to be assigned to this park.
     * @return a boolean indicating if the mutator operation is successful
     */
    public boolean setPaths(LinkedList<Path> paths);
}
