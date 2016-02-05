package shapefile.graph;
import java.util.List;



public class DemoGraph{
	
	public static void main(String[] args) throws Exception {
		
		BuildGraph dGraph = new BuildGraph();
		dGraph.addVertexes();
		dGraph.addEdges();

		//compute shortest path
		String source = "173036";
		String target = "273509";
    DijkstraAlgorithm.computePaths((Vertex)dGraph.getShapeFileNodes().get(source));
    System.out.println("Distance from " + source + " to " + target + " : " + dGraph.getShapeFileNodes().get(target).minDistance);
    List<Vertex> path = DijkstraAlgorithm.getShortestPathTo(dGraph.getShapeFileNodes().get(target));
    System.out.println("Path:" + path);
    for(int i = 0; i<= path.size()-1;i++){

    	System.out.println(path.get(i) );
}

}}


	

