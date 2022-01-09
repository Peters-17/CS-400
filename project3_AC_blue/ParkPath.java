// --== CS400 Project Three File Header ==--
// Name: Yang Qiu
// Email: qiu67@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun


/**
 * defines contents and methods of objects representing any shortest direct path between two Wisconsin Stae Parks,
 * as edges in the graph.
 */
public class ParkPath implements Path {
    // contains the object that refers to the Wisconsin State Park which the path starts from
    private String source;
    // contains the object that refers to the Wisconsin State Park which the path leads to
    private String target;
    // contains the length of this path. The path is chosen so that it is the shortest path viable from source to target
    private Integer length;

    /**
     * sets up information contained in the object
     *
     * @param source the Wisconsin State Park at which the path starts
     * @param target the Wisconsin State Park at which the path ends (should be directly reachable from source)
     * @param length the length of this path (the path is only considered when it is the shortest possible)
     */
    public ParkPath(String source, String target, Integer length) {
        this.source = source;
        this.target = target;
        this.length = length;
    }

    /**
     * retrieve the destination Wisconsin State Park of the path.
     *
     * @return the object that refers to the destination Wisconsin State Park of the path.
     */
    @Override
    public String getTarget() {
        return target;
    }

    /**
     * retrieve the starting Wisconsin State Park of the path
     *
     * @return the object that refers to the starting Wisconsin State Park of the path.
     */
    @Override
    public String getSource() {
        return source;
    }

    @Override
    public Integer getWeight() {
        return length;
    }

    @Override
    public int compareTo(Path otherPath) {
        int cmp = this.getWeight() - otherPath.getWeight();
        if (cmp != 0)
            return cmp; // use path distance as the natural ordering
        // when path distances are equal, break ties by comparing the string
        // representation of name
        return this.getTarget().compareTo(otherPath.getTarget());
    }

    /**
     * retrieve a simple representation in String of this path, including source, length, and target
     * e.g., ".[YellowStone]-(1314)->[Smoky_Mountains]."
     *
     * @return a simple String representation of this path
     */
    @Override
    public String toString() {
        return source + "-(" + length + ")->" + target + ".";
    }
}


