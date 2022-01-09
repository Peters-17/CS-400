import java.util.LinkedList;

/**
 * as a data wrangler interface, contains necessary methods to be implemented in class ParkPath
 */
interface Path extends Comparable<Path>{
    /**
     * retrieve the destination of the path.
     * @return the name that refers to the destination park of the path.
     */
    public String getTarget();

    /**
     * retrieve the start point of the path
     * @return the name that refers to the start park of the path.
     */
    public String getSource();

    /**
     * retrieve the length in miles of this path.
     * @return the length in miles of this path.
     */
    public Integer getWeight();

    /**
     * helps sort this path and another path.
     * @param otherPath the other path to be compared to
     * @return an integer to distinguish this path from the other path
     */
    @Override
    public int compareTo(Path otherPath);
}
