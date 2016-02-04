package shapefile.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import map.graph.*;

public class DemoGraph{
	
	public static void main(String[] args) throws Exception {
		

		
		
	
BuildGraph dGraph = new BuildGraph();
	dGraph.addVertexes();
	dGraph.addEdges();
	
	
	
//	Iterator it2 = dGraph.shapeFileNodes.entrySet().iterator();
//    while (it2.hasNext()) {
//        Map.Entry pair = (Map.Entry)it2.next();
//      System.out.println(pair.getValue());
//      	for(int i = 0; i < ((Vertex)pair.getValue()).getNeighborCount(); i++)
//      		System.out.println(((Vertex)pair.getValue()).getNeighbor(i));
//    }	

	
  //compute shortest path
    DijkstraAlgorithm.computePaths((Vertex)dGraph.shapeFileNodes.get("285550"));
    System.out.println("Distance to " + 172222 + " : " + dGraph.shapeFileNodes.get("172222").minDistance);
    List<Vertex> path = DijkstraAlgorithm.getShortestPathTo(dGraph.shapeFileNodes.get("535114"));
    System.out.println("Path:" + path);
    for(int i = 0; i<= path.size()-1;i++){

    	System.out.println(path.get(i) );
}

}}


	

