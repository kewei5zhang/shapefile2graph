package shapefile.graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
   // ArrayList<Intersection> nodeCollection = new ArrayList<Intersection>();
	 HashMap<String, Intersection> nodeCollection = new HashMap<String, Intersection>();

	
    //roadCollection contain all route information;
    ArrayList<ArrayList<String>> roadInfo = new ArrayList<>();
//    
	
	
	public ShapeFileReader(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void readRoad() throws Exception {
        // display a data store file chooser dialog for shapefiles
//		   FileWriter fileWriter = new FileWriter("RoadInfo");
//		   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
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
//        	   System.out.printf("ID = %s, SnodeID = %s, EnodeID = %s, Length = %s", 
//        			   feature.getAttribute("ID"),
//        			   feature.getAttribute("SNODEID"),
//        			   feature.getAttribute("ENODEID"),
//        			   feature.getAttribute("LENGTH")
//        			   );
//        	   System.out.println();	   
         }
	}
       else {
		System.out.println("Read file failed!");
	}
          
    }
	public void readNode() throws Exception {
        // display a data store file chooser dialog for shapefiles
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
           SimpleFeatureCollection result = featureSource.getFeatures(filter);
           FeatureIterator iterator = result.features();
   
    	   while(iterator.hasNext()){
    		   Intersection point = new Intersection(null);
        	   SimpleFeature feature = (SimpleFeature) iterator.next();
        	   
        	   point.setLabel((String)feature.getAttribute("ID")); 
        	   
        	   //write into a file
        	   nodeCollection.put(point.getLabel(),point);
       
//        	   System.out.printf("NodeID = %s", 
//        			   feature.getAttribute("ID")
//        			   );
//        	   System.out.println();	
         }
    	   
       }
       else if (typeName.equals("Rbeijing_polyline")) {
        	   System.out.println("Warning! This is a Road Shapefile");	   
         }
	
       else {
		System.out.println("Read file failed!");
	}
          
    }
	
	
}
