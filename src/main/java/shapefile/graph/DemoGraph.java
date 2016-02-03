package shapefile.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import map.graph.DijkstraAlgorithm;
import map.graph.Graph;
import map.graph.Intersection;
import map.graph.Vertex;



public class DemoGraph{
	
	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception {
		Graph graph = new Graph();
		ShapeFileReader sFReader = new ShapeFileReader();
		
		 HashMap<String, Intersection> shapeFileIntersections = new HashMap<String, Intersection>();
    	//ArrayList<Intersection> shapeFileIntersections = null;
    	ArrayList<ArrayList<String>> shapeFileRoadInfo = null;
    	ArrayList<String> pointsPair = new ArrayList<>();
		
    	sFReader.readNode();
    	sFReader.readRoad();
    	
		shapeFileIntersections = sFReader.nodeCollection;
		shapeFileRoadInfo = sFReader.roadInfo;
		
		//iterate the hashmap and add vertexes
		 Iterator it = shapeFileIntersections.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        graph.addVertex((Vertex) pair.getValue(), true);
		    }	
		   
		//Add edges    
		Intersection one = new Intersection(null);
		Intersection two = new Intersection(null);

        for(int i = 0; i <= shapeFileRoadInfo.size()-1; i++){
        	for(int j = 0; j <= 1;j++){       		
        		String k =  shapeFileRoadInfo.get(i).get(j) ;
        		pointsPair.add(k);
        	}
        	try{
        		String oneKey = pointsPair.get(0);
        		String twoKey = pointsPair.get(1);
            	pointsPair.clear();
            	//System.out.print(shapeFileIntersections.get(oneKey));
        		one = (Intersection)shapeFileIntersections.get(oneKey);
        		two = (Intersection)shapeFileIntersections.get(twoKey);
            	Double length = Double.parseDouble(shapeFileRoadInfo.get(i).get(2));         	
            	graph.addEdge(one, two, length);
        	}
        	catch(NullPointerException e){
        	}
        }
	// print all vertexes and their edges
        Iterator it2 = shapeFileIntersections.entrySet().iterator();
        while (it2.hasNext()) {
	        Map.Entry pair = (Map.Entry)it2.next();
	      System.out.println(pair.getValue());
	      	for(int i = 0; i < ((Vertex)pair.getValue()).getNeighborCount(); i++)
	      		System.out.println(((Vertex)pair.getValue()).getNeighbor(i));
	    }	
        
      //compute shortest path
        DijkstraAlgorithm.computePaths(shapeFileIntersections.get("230803"));
        System.out.println("Distance to " + 469314 + " : " + shapeFileIntersections.get("469314").minDistance);
        List<Vertex> path = DijkstraAlgorithm.getShortestPathTo(shapeFileIntersections.get("469314"));
        System.out.println("Path:" + path);
        for(int i = 0; i<= path.size()-1;i++){
//        	String latString = null;
//        	String lngString = null;
//        	latString = oldBeijingIntersections.get(Integer.parseInt(path.get(i).getLabel())).getLatitude();
//        	lngString = oldBeijingIntersections.get(Integer.parseInt(path.get(i).getLabel())).getLongitude();
        	System.out.println(path.get(i) );
	}
	}
}

	

