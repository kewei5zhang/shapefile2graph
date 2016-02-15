package shapefile.graph;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 * this class models a DijkstraAlgorithm to test the graph
 * @author s4366844 Kewei Zhang
 *
 */
public class DijkstraAlgorithm
{
	/**
	 * the computePaths() method calculate all shortest paths form the source vertex to all reachable nodes in the graph
	 * @param source the start vertex that user want to calculate the path length from
	 */
    public static void computePaths(Vertex source)
    {
    	if(source == null){
    		return;
    	}
        source.minDistance = 0;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
        Vertex u = vertexQueue.poll();
            // Visit each edge exiting u
            for (Edge e : u.getNeighborhood())
            {
            	try{
                Vertex v = e.getNeighbor(u);
                if(e.getDirection().equals("0") || e.getDirection().equals("1") || (e.getDirection().equals("2") && e.getOne().equals(u)) || e.getDirection().equals("3") && e.getTwo().equals(u) ){
                    double weight = e.getWeight();
                    double distanceThroughU = u.minDistance + weight;
                    
                    if (distanceThroughU < v.minDistance) {
                    	vertexQueue.remove(v);

                    	v.minDistance = distanceThroughU ;
                    	v.previous = u;
                    	vertexQueue.add(v);
                    }
                }
                else if ((e.getDirection().equals("2") && e.getTwo().equals(u)) || ((e.getDirection().equals("3") && e.getOne().equals(u)))){
                	double weight = Double.POSITIVE_INFINITY;
                	double distanceThroughU = u.minDistance + weight;
                      
                      if (distanceThroughU < v.minDistance) {
                      	vertexQueue.remove(v);

                      	v.minDistance = distanceThroughU ;
                      	v.previous = u;
                      	vertexQueue.add(v);
                      }           
                	}
            	}
            	catch(NullPointerException ex){
            		
            	}
            }
        }
    }
    /**
     * @param target the target vertex that user want to caluculate the path length to
     * @return a list of pathway vertexes
     */

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
}