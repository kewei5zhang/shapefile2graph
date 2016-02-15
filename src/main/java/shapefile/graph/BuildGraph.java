package shapefile.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * this class build up a graph with data imported from ShapeFileReader class
 * @author s4366844 Kewei Zhang
 *
 */
public class BuildGraph {

	private  Graph graph = new Graph();	
	private  HashMap<String, Vertex> shapeFileNodes = new HashMap<String, Vertex>();
	private  ArrayList<Edge> shapeFileRoads = new ArrayList<>();

	public HashMap<String, Vertex> getShapeFileNodes() {
		return shapeFileNodes;
	}

	public void setShapeFileNodes(HashMap<String, Vertex> shapeFileNodes) {
		this.shapeFileNodes = shapeFileNodes;
	}

	public ArrayList<Edge> getShapeFileRoads() {
		return shapeFileRoads;
	}

	public void setShapeFileRoads(ArrayList<Edge> shapeFileRoads) {
		this.shapeFileRoads = shapeFileRoads;
	}
	
	public BuildGraph() throws Exception{
		ShapeFileReader sFReader = new ShapeFileReader();
		sFReader.readNode();
		sFReader.readRoad();
		shapeFileNodes = sFReader.vertexCollection;
		shapeFileRoads = sFReader.roadInfo;
	}
	
	/**
	 * the addVertexes() method adds all vertexes read from the node shapefile into a graph
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void addVertexes() throws Exception{
		Iterator it = shapeFileNodes.entrySet().iterator();
	    while (it.hasNext()) {  	
	        Map.Entry pair = (Map.Entry)it.next();
	        graph.addVertex((Vertex)pair.getValue(), true);
	    }	   
	}
	
	/**
	 * the addEdges() method adds all edges read from the road shapefile into a graph
	 * @throws Exception
	 */
	public void addEdges() throws Exception{	
		for(int i = 0; i <= shapeFileRoads.size()-1; i++){
        	try{
            	graph.addEdge(shapeFileRoads.get(i));
        	}
        	catch(NullPointerException e){
        	}
        } 
	}
	
	/**
	 * the printGraph function can print all edges and weight in the graph
	 * @return true if the map has successfully been printed out
	 * @return false is the shapeFileNodes hashmap is empty
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public boolean printGraph() throws Exception{
		if(!shapeFileNodes.isEmpty()){
		Iterator it2 = shapeFileNodes.entrySet().iterator();
	    while (it2.hasNext()) {
	        Map.Entry pair = (Map.Entry)it2.next();
	      System.out.println(pair.getValue());
	      	for(int i = 0; i < ((Vertex)pair.getValue()).getNeighborCount(); i++)
	      		System.out.println(((Vertex)pair.getValue()).getNeighbor(i));
	    }
		return true;	
		}
		else{
			return false;
		}
	}

	
	
}
	

