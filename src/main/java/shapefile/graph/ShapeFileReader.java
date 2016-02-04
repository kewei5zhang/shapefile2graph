package shapefile.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;










import map.graph.Intersection;

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

	// nodeCollection contain all Intersections(Vertexes) with labels;
	 HashMap<String, Intersection> nodeCollection = new HashMap<String, Intersection>();

    //roadCollection contain all route information;
    ArrayList<ArrayList<String>> roadInfo = new ArrayList<>();
    
    //adjoinNodes save all nodes pairs with an adjoin nodes;
    ArrayList<ArrayList<String>> adjoinNodes = new ArrayList<>(); 

	public ShapeFileReader(){
		
	}
	
	@SuppressWarnings("unchecked")
	
	public void readNode() throws Exception {
	
		//PrintWriter writer = new PrintWriter("NodeInfo");

        // display a data store file chooser dialog for shapefiles
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        } 
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();       
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
       // System.out.println(SHAPE_TYPE);
        String typeName = store.getTypeNames()[0]; 
       
        Filter filter = CQL.toFilter("INCLUDE");
 
       if(typeName.equals("Nbeijing_point")){
           SimpleFeatureCollection result = featureSource.getFeatures(filter);
           FeatureIterator iterator = result.features();
           ArrayList<String> adjoinInfo = new ArrayList<>();
   
    	   while(iterator.hasNext()){
    		   Intersection point = new Intersection(null);
        	   SimpleFeature feature = (SimpleFeature) iterator.next();

        	   point.setLabel((String)feature.getAttribute("ID")); 
        	   
        	   //write into a file
        	   nodeCollection.put(point.getLabel(),point);
       
        	   if((String)feature.getAttribute("ADJOIN_NID") != ("0")){
        		   ArrayList<String> adjoinBuffer= new ArrayList<>();
        		   
        		   adjoinInfo.add((String)feature.getAttribute("ID"));
            	   adjoinInfo.add((String)feature.getAttribute("ADJOIN_NID"));
            	   adjoinBuffer = (ArrayList<String>) adjoinInfo.clone();
         			adjoinNodes.add(adjoinBuffer);
         			adjoinInfo.clear();
            	   
        	   }
        		   
//        	   writer.printf("NodeID = %s, Cross_flag = %s, Cross_LID = %s, mainNodeID = %s, subNodeID = %s, subNodeID2 = %s, Node_LID = %s, Adjoin_NID = %s\n", 
//        			   feature.getAttribute("ID"),
//        			   feature.getAttribute("CROSS_FLAG"),
//        			   feature.getAttribute("CROSS_LID"),
//        			   feature.getAttribute("MAINNODEID"),
//        			   feature.getAttribute("SUBNODEID"),
//        			   feature.getAttribute("SUBNODEID2"),
//        			   feature.getAttribute("NODE_LID"),
//        			   feature.getAttribute("ADJOIN_NID")
//        			   );
         }
//    	   writer.close();
    	   
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
        // display a data store file chooser dialog for shapefiles
		//PrintWriter writer = new PrintWriter("RoadInfo");
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }
 
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();
       
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
         System.out.println(SHAPE_TYPE);
        String typeName = store.getTypeNames()[0]; 
       
        Filter filter = CQL.toFilter("INCLUDE");
 
       if(typeName.equals("Nbeijing_point")){
           
        	   System.out.println("Warning! This is a Node shapefile!");	   
         }
       
       else if (typeName.equals("Rbeijing_polyline")) {
           
           SimpleFeatureCollection result = featureSource.getFeatures(filter);
           FeatureIterator iterator = result.features();
           ArrayList<String> rdinfo= new ArrayList<>();
    	   while(iterator.hasNext()){
    		   ArrayList<String> rdbuffer = new ArrayList<>();
    
       			
       		   SimpleFeature feature = (SimpleFeature) iterator.next();  
        	   rdinfo.add((String)feature.getAttribute("SNODEID"));
        	   rdinfo.add((String)feature.getAttribute("ENODEID"));
        	   rdinfo.add((String)feature.getAttribute("LENGTH"));
        	   rdbuffer = (ArrayList<String>) rdinfo.clone();
      			roadInfo.add(rdbuffer);
      			rdinfo.clear();
//        	  
//      			writer.printf("ID = %s, SnodeID = %s, EnodeID = %s, Length = %s, Direction = %s, \n", 
//        			   feature.getAttribute("ID"),
//        			   feature.getAttribute("SNODEID"),
//        			   feature.getAttribute("ENODEID"),
//        			   feature.getAttribute("LENGTH"),
//        			   feature.getAttribute("DIRECTION")
//        			   );
//        	  
         }
//    	   writer.close();
	}
       else {
		System.out.println("Read file failed!");
       }
      
    }
	
}
