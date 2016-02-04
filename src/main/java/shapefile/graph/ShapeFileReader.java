package shapefile.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;











import map.graph.Intersection;
import map.graph.Vertex;

import org.geotools.data.Query;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.FeatureIterator;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.ecql.ECQL;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.util.TypeName;


public class ShapeFileReader {

	ArrayList<Node> nodeInfo = new ArrayList<>();
	// nodeCollection contain all Intersections(Vertexes) with labels;
	 HashMap<String, Vertex> vertexCollection = new HashMap<String, Vertex>();

    //roadCollection contain all route information;
    ArrayList<Road> roadInfo = new ArrayList<>();
    
    //adjoinNodes save all nodes pairs with an adjoin nodes;
    ArrayList<ArrayList<String>> adjoinNodes = new ArrayList<>(); 

	public ShapeFileReader(){
		
	}
	
	@SuppressWarnings("unchecked")
	
	public void readNode() throws Exception {

        // display a data store file chooser dialog for shapefiles
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        } 
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();       
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
        String typeName = store.getTypeNames()[0]; 
       
        Filter filter = CQL.toFilter("INCLUDE");
 
       if(typeName.equals("Nbeijing_point")){
           SimpleFeatureCollection result = featureSource.getFeatures(filter);
           FeatureIterator iterator = result.features();
           //ArrayList<String> adjoinInfo = new ArrayList<>();
   
    	   while(iterator.hasNext()){
    		   Node node = new Node();
    		  
        	   SimpleFeature feature = (SimpleFeature) iterator.next();
        	   
        	   node.setID((String)feature.getAttribute("ID"));
        	   node.setCross_flag((String)feature.getAttribute("CROSS_FLAG"));
        	   node.setCross_LID((String)feature.getAttribute("CROSS_LID"));
        	   node.setMainNodeID((String)feature.getAttribute("MAINNODEID"));
        	   node.setNode_LID((String)feature.getAttribute("NODE_LID"));
        	   node.setAdjoin_NID((String)feature.getAttribute("ADJOIN_NID"));
        	   
        	   nodeInfo.add(node);
        	   Vertex vertex = new Vertex(node.getID());
        	   vertexCollection.put(vertex.getLabel(), vertex);
        	   
        	   ArrayList<String> adjoinInfo = new ArrayList<>();
        	   if((String)feature.getAttribute("ADJOIN_NID") != ("0")){
        		   ArrayList<String> adjoinBuffer= new ArrayList<>();
        		   
        		   adjoinInfo.add((String)feature.getAttribute("ID"));
            	   adjoinInfo.add((String)feature.getAttribute("ADJOIN_NID"));
            	   adjoinBuffer = (ArrayList<String>) adjoinInfo.clone();
         			adjoinNodes.add(adjoinBuffer);
         			adjoinInfo.clear();
            	   }
    	   }
       }
       else if (typeName.equals("Rbeijing_polyline")) {
        	   System.out.println("Warning! This is a Road Shapefile");	   
         }	
       else {
		System.out.println("Read file failed!");
       }       
    }
	
	@SuppressWarnings("unchecked")
	public void readRoad() throws Exception {
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }
 
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
        String typeName = store.getTypeNames()[0]; 
       
        Filter filter = CQL.toFilter("INCLUDE");
 
       if(typeName.equals("Nbeijing_point")){           
        	   System.out.println("Warning! This is a Node shapefile!");	   
         }
       
       else if (typeName.equals("Rbeijing_polyline")) {
           
           SimpleFeatureCollection result = featureSource.getFeatures(filter);
           FeatureIterator iterator = result.features();
    	   while(iterator.hasNext()){
    		   Road road = new Road();
       		   SimpleFeature feature = (SimpleFeature) iterator.next();  
       		 
        	   road.setLID((String)feature.getAttribute("ID"));
        	   road.setSnodeID((String)feature.getAttribute("SNODEID"));
        	   road.setEnodeID((String)feature.getAttribute("ENODEID"));
        	   road.setLength((String)feature.getAttribute("LENGTH"));
        	   road.setDirection((String)feature.getAttribute("DIRECTION"));
       		   
        	   roadInfo.add(road);
         }
	}
       else {
		System.out.println("Read file failed!");
       }
      
    }
	
}
