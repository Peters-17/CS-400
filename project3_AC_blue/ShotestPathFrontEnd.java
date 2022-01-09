
/**
 *This interface helps implements the PathSystemApp
 *@author Qi Dong
 */
interface ShotestPathFrontEndInterface{
public void run(GraphADT startEngine);
public boolean updateMap(GraphADT engine, Scanner newScanner);
public boolean CalShortestPath(GraphADT engine, Scanner newScanner);
}

/**
 *placeholder of the FrontEnd implements the ShotestPathFrontEndInterface
 *@author Qi Dong
 */
class ShortestPathFrontEndPlaceholder implements ShotestPathFrontEndInterface {
    public void run(GraphADT searchEngine) {
        System.out.println("This front end has not been implemented yet.");
    }
    public boolean updateMap(GraphADT engine, Scanner newScanner){
	    system.out.println("This front end has not been implemented yet.");
    return true;
    }
    public boolean CalShortestPath(GraphADT engine, Scanner newScanner){
            system.out.println("This front end has not been implemented yet.");
    return true;
    }
}


