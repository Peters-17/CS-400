// --== CS400 File Header Information ==--
// Name: Yuan Haoyang
// Email: hyuan73@wisc.edu
// Team: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: <optional extra notes>


import java.util.List;

public interface BackEndInterface {
    /**
     * this method find the shortest path from start node to end node and store
     * all passing nodes in list
     *
     * @param start     park's name
     * @param end       park's name
     * @return a list of passing parks
     */
    List<String> getShortestPath(String start, String end);

    /**
     * this method find the distance of the shortest path
     *
     * @param start     park's name
     * @param end       park's name
     * @return integer distance
     */
    int getShortestDistance(String start, String end);


    /**
     * this method get a given parks’ all directly reachable parkes.
     *
     * @param start park
     */
    List<Path> getDirectReach(String start);

    /**
     * this method will add a path between two given parks.
     *
     * @param start     park name
     * @param end       park name
     * @param cost      path cost
     * @param distance  path distance
     */
    boolean addPath(String start,String end, int distance);

    /**
     * this method delete the existing path
     *
     * @param start     park name
     * @param end       park name
     */
    boolean deletePath(String start,String end);
}
