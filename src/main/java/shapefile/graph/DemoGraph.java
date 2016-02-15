package shapefile.graph;
import java.util.List;

/**
 * the DemoGraph class contains the main method
 * @author s4366844 Kewei Zhang
 *
 */
public class DemoGraph{
	
	public static void main(String[] args) throws Exception {
		
		BuildGraph dGraph = new BuildGraph();
		dGraph.addVertexes();
		dGraph.addEdges();

		//compute shortest path
		String source = "173036";
		String target = "273509";
		try {
			DijkstraAlgorithm.computePaths((Vertex)dGraph.getShapeFileNodes().get(source));
			System.out.println("Distance from " + source + " to " + target + " : " + dGraph.getShapeFileNodes().get(target).minDistance);
			List<Vertex> path = DijkstraAlgorithm.getShortestPathTo(dGraph.getShapeFileNodes().get(target));
			System.out.println("Path:" + path);
		} catch (NullPointerException ex) {
			System.out.println("No such nodes in the graph, or there is nothing in the graph!");
		}
	}
}


	

