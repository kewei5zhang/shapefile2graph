package shapefile.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureIterator;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;

/**
 * this class functioned a shapefile reader using the geotools' api, 
 *
 * @author s4366844 KeweiZhang
 *
 */

public class ShapeFileReader {
	
	public HashMap<String, Vertex> vertexCollection = new HashMap<String, Vertex>();
    public ArrayList<Edge> roadInfo = new ArrayList<>();

	/**
	 * the readNode() method opens .shp file that contains node information and assigns
	 * values to vertexCollection (hashmap<ID,Vertex>),
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void readNode() throws Exception {

        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        } 
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();       
        String typeName = store.getTypeNames()[0]; 
       
        Filter filter = CQL.toFilter("INCLUDE");
 
       if(typeName.equals("Nbeijing_point")){
           SimpleFeatureCollection result = featureSource.getFeatures(filter);
           FeatureIterator iterator = result.features();
           //ArrayList<String> adjoinInfo = new ArrayList<>();
   
    	   while(iterator.hasNext()){
    		   Vertex node = new Vertex(null);
    		  
        	   SimpleFeature feature = (SimpleFeature) iterator.next();
        	   
        	   node.setLabel((String)feature.getAttribute("ID"));
        	   node.setCross_flag((String)feature.getAttribute("CROSS_FLAG"));
        	   node.setCross_LID((String)feature.getAttribute("CROSS_LID"));
        	   node.setMainNodeID((String)feature.getAttribute("MAINNODEID"));
        	   node.setNode_LID((String)feature.getAttribute("NODE_LID"));
        	   node.setAdjoin_NID((String)feature.getAttribute("ADJOIN_NID"));

        	   vertexCollection.put(node.getLabel(), node);
        	   
    	   }
       }
       else if (typeName.equals("Rbeijing_polyline")) {
        	   System.out.println("Warning! This is a Road Shapefile");	   
         }	
       else {
		System.out.println("Read file failed!");
       }       
    }
	
	/**
	 * the readRoad() method opens .shp file that contains road information and assigns 
	 * values to roadInfo (ArrayList<Edge>)
	 * @throws Exception
	 */

	@SuppressWarnings("rawtypes")
	public void readRoad() throws Exception {
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }
 
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();
        String typeName = store.getTypeNames()[0]; 
       
        Filter filter = CQL.toFilter("INCLUDE");
 
       if(typeName.equals("Nbeijing_point")){           
        	   System.out.println("Warning! This is a Node shapefile!");	   
         }
       
       else if (typeName.equals("Rbeijing_polyline")) {
            
           SimpleFeatureCollection result = featureSource.getFeatures(filter);
           FeatureIterator iterator = result.features();
    	   while(iterator.hasNext()){
       		   SimpleFeature feature = (SimpleFeature) iterator.next();  
       		   String lID = (String)feature.getAttribute("ID");
       		   String snodeID = (String)feature.getAttribute("SNODEID");
       		   String enodeID = (String)feature.getAttribute("ENODEID");
       		   String length = (String)feature.getAttribute("LENGTH");
       		   String direction = (String)feature.getAttribute("DIRECTION");
       		    		   
       		   Vertex one = (Vertex)vertexCollection.get(snodeID);
       		   Vertex two = (Vertex)vertexCollection.get(enodeID);
       		   Double weight = Double.parseDouble(length);
       		   
       		   Edge road = new Edge(one, two, weight);
       		   road.setLID(lID);
        	   road.setSnodeID(snodeID);
        	   road.setEnodeID(enodeID);
        	   road.setLength(length);
        	   road.setDirection(direction);
       		   
        	   roadInfo.add(road);
         }
    	   Iterator it = vertexCollection.entrySet().iterator();
    	   while(it.hasNext()){
    		   Map.Entry pair = (Map.Entry)it.next();
    		   String adjoin_NID = ((Vertex)pair.getValue()).getAdjoin_NID();
    		   try{
    		   if(!adjoin_NID.equals("0")){ 
    		   Vertex one = (Vertex)pair.getValue();
    		   Vertex two = (Vertex)vertexCollection.get(((Vertex)pair.getValue()).getAdjoin_NID());			   
    		   Edge road = new Edge(one, two, (double)0);
    		   road.setDirection("1");
    		   roadInfo.add(road);
    		   }
    		   }
    		   catch(NullPointerException ex){
    			   
    		   }
    	   	}
    	}
       else {
		System.out.println("Read file failed!");
       }
	}
}
	
