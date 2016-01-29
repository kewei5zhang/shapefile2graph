package shapefile.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import map.graph.Graph;
import map.graph.Intersection;



public class DemoGraph{
	
	public static void main(String[] args) throws Exception {
		Graph graph = new Graph();
		ShapeFileReader sFReader = new ShapeFileReader();
		
    	ArrayList<Intersection> shapeFileIntersections = null;
    	ArrayList<ArrayList<String>> shapeFileRoadInfo = null;
    	ArrayList<Integer> pointsPair = new ArrayList<>();
		
    	sFReader.readNode();
    	sFReader.readRoad();
    	
		shapeFileIntersections = sFReader.nodeCollection;
		shapeFileRoadInfo = sFReader.roadInfo;
//		
//	    int i = 2000177176;
//	    System.out.print(i);
		
		 for( int i = 0; i <= shapeFileIntersections.size() - 1; i++){
	        	System.out.println(shapeFileIntersections.get(i));
	        }
		
	    for( int i = 0; i <= shapeFileIntersections.size() - 1; i++){
        	graph.addVertex(shapeFileIntersections.get(i), true);
        }

	    
	    for(int i = 0; i <= shapeFileRoadInfo.size()-1; i++){

        	System.out.println(shapeFileRoadInfo.get(i));
        	
        }
//	    

	    
	    
	    

	    
//        for(int i = 0; i <= shapeFileRoadInfo.size()-1; i++){
//        	for(int j = 0; j <= 1;j++){       		
//        		int k = Integer.parseInt(shapeFileRoadInfo.get(i).get(j)) ;
//        		pointsPair.add(k);
//        	}
//        	
//        	
//        	
//        	Intersection one = shapeFileIntersections.get(pointsPair.get(0));
//        	Intersection two = shapeFileIntersections.get(pointsPair.get(1));
//        	Double length = Double.parseDouble(shapeFileRoadInfo.get(i).get(2));
//        	
//        	graph.addEdge(one, two, length);
//        	pointsPair.clear();
//        }
//        
//        for(int i = 0; i <= shapeFileIntersections.size()-1; i++){
//        	System.out.println(shapeFileIntersections.get(i));
//        	
//        	for(int j = 0; j< shapeFileIntersections.get(i).getNeighborCount(); j++)
//        		System.out.println(shapeFileIntersections.get(i).getNeighbor(j));       		
//        }     
//        System.out.println();
//		
	}
}
