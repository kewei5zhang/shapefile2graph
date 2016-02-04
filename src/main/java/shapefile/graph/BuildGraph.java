package shapefile.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import map.graph.DijkstraAlgorithm;
import map.graph.Graph;
import map.graph.Intersection;
import map.graph.Vertex;

public class BuildGraph {

	
	// nodeCollection contain all Intersections(Vertexes) with labels;
	public  HashMap<String, Vertex> shapeFileNodes = new HashMap<String, Vertex>();

   //roadCollection contain all route information;
  public  ArrayList<Road> shapeFileRoads = new ArrayList<>();
   
   
 public   ArrayList<ArrayList<String>> shapeFileAdjoinNodes = new ArrayList<>(); 
   
   
   ArrayList<String> pointsPair = new ArrayList<>();
   
   Graph graph = new Graph();
	
	public BuildGraph() throws Exception{
		ShapeFileReader sFReader = new ShapeFileReader();
		sFReader.readNode();
		sFReader.readRoad();
		shapeFileNodes = sFReader.vertexCollection;
		shapeFileRoads = sFReader.roadInfo;
		shapeFileAdjoinNodes = sFReader.adjoinNodes;
	}
	

	public void addVertexes() throws Exception{
		Iterator it = shapeFileNodes.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	        Map.Entry pair = (Map.Entry)it.next();
	        graph.addVertex((Vertex)pair.getValue(), true);
	    }	   
	}
	
	public void addEdges() throws Exception{	
		for(int i = 0; i <= shapeFileRoads.size()-1; i++){
			String labelOne = shapeFileRoads.get(i).getSnodeID();
			String labelTwo = shapeFileRoads.get(i).getEnodeID();
        	try{
        		Vertex one = (Vertex)shapeFileNodes.get(labelOne);
        		Vertex two = (Vertex)shapeFileNodes.get(labelTwo);
            	Double length = Double.parseDouble(shapeFileRoads.get(i).getLength());         	
            	graph.addEdge(one, two, length);
        	}
        	catch(NullPointerException e){
        	}
        }
        
        for(int i = 0; i <= shapeFileAdjoinNodes.size()-1; i++){
        	for(int j = 0; j <= 1;j++){       		
        		String k =  shapeFileAdjoinNodes.get(i).get(j) ;
        		pointsPair.add(k);
        	}
        	try{
        		String oneKey = pointsPair.get(0);
        		String twoKey = pointsPair.get(1);
            	pointsPair.clear();
            	Vertex one = (Vertex)shapeFileNodes.get(oneKey);
        		Vertex two = (Vertex)shapeFileNodes.get(twoKey);
            	Double length = (double) 0;        	
            	graph.addEdge(one, two, length);
        	}
        	catch(NullPointerException e){
        	}
        }
	}
	

	
	
}
	

