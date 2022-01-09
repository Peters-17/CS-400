// --== CS400 File Header Information ==--
// Name: Yuan Haoyang
// Email: hyuan73@wisc.edu
// Team: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.io.FileReader;
import java.io.Reader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * BackEnd class, implementing BackEndInterface to overwrite all methods
 *
 */
public class BackEnd implements BackEndInterface{
    // store distance with each edge
    private CS400Graph<String> parkDistanceGraph;
    // store Park in the list
    private ArrayList<WIStatePark> parksList;

    /**
     * Default constructor
     */
    public BackEnd() {
    }

    /**
     * test constructor
     *
     * @param testList a list stored WIStateParks
     */
    public BackEnd(ArrayList<WIStatePark> testList) {
        parksList = testList;
        parkDistanceGraph = new CS400Graph<>();
        for (WIStatePark park : parksList) {
            // add vertices
            parkDistanceGraph.insertVertex(park.getName());
        }
        for (WIStatePark park : parksList) {
            // local value that stores connected parks sequence
            LinkedList<Path> connectedParks = park.getPaths();
            for (int i = 0; i < connectedParks.size(); i++) {
                //add edges
                parkDistanceGraph.insertEdge(connectedParks.get(i).getSource(), connectedParks.get(i).getTarget(),
                        connectedParks.get(i).getWeight());
            }
        }
    }

    /**
     * this method return the shortest path from start to end
     *
     * @param start start park
     * @param end end park
     */
    @Override
    public List<String> getShortestPath(String start, String end) {
        try {
            //find shortest path
            return parkDistanceGraph.shortestPath(start, end);
        } catch (Exception e) {
            // return null there is no shortest path found
            return null;
        }
    }

    /**
     * this method return the shortest distance from start to end
     *
     * @param start start park
     * @param end end park
     */
    @Override
    public int getShortestDistance(String start, String end) {
        try {
            // use dijkstras algorithm CS400Graph to find shortest distance
            return parkDistanceGraph.getPathCost(start, end);
        } catch (Exception e) {
            // return -1 if there is no shortest distance found
            return -1;
        }
    }

    /**
     * this method return all paths that begin with start park
     *
     * @param start start park
     */
    @Override
    public List<Path> getDirectReach(String start) {
        for (WIStatePark park : parksList) {
            if (park.getName().equals(start)) {
                return park.getPaths();
            }
        }
        return null;
    }

    /**
     * this method can add path
     *
     * @param start start park
     * @param end end park
     * @param distance distance between start and end park
     */
    @Override
    public boolean addPath(String start, String end, int distance) {
        try {
            if(start == null || end == null){
                System.out.println("the start park or the end park didn't exist");
                return false;
            }
            // add distance into park distance graph
            parkDistanceGraph.insertEdge(start, end, distance);
            for (int i = 0; i < parksList.size(); i++) {
                if (parksList.get(i).getName().equals(start)) {
                    ParkPath tempPath = new ParkPath(start, end, distance);
                    parksList.get(i).getPaths().add(tempPath);
                }
            }
            return true;
        } catch (Exception e) {
            // return false if a exception happens
            return false;
        }
    }

    /**
     * this method can delete path
     *
     * @param start start park
     * @param end end park
     */
    @Override
    public boolean deletePath(String start, String end) {
        try {
            // remove distance from park distance graph
            parkDistanceGraph.removeEdge(start, end);
            for (int i = 0; i < parksList.size(); i++) {
                if (parksList.get(i).getName().equals(start)) {
                    for(int j = 0; j < parksList.get(i).getPaths().size(); j++){
                        if(parksList.get(i).getPaths().get(j).getTarget().equals(end)){
                            parksList.remove(parksList.get(i).getPaths().get(j));
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            // return false if a exception happens
            return false;
        }
    }
}
