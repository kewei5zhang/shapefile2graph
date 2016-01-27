package org.geotools.tutorial.quickstart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a map frame.
 * <p>
 * This is the GeoTools Quickstart application used in documentationa and tutorials. *
 */
public class Quickstart {

    /**
     * GeoTools Quickstart demo application. Prompts the user for a shapefile and displays its
     * contents on the screen in a map frame
     */
	public static void main(String[] args) throws Exception {
		
	}
	
	
    public void readRoad() throws Exception {
        // display a data store file chooser dialog for shapefiles
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }
 
        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();
       
        SimpleFeatureType SHAPE_TYPE = featureSource.getSchema();
        String typeName = store.getTypeNames()[0];
        
      // System.out.println(SHAPE_TYPE);
        
        String name = SHAPE_TYPE.getGeometryDescriptor().getLocalName();
        
       // System.out.println(name);
        
        Filter filter = CQL.toFilter("include");
       
       SimpleFeatureCollection result = featureSource.getFeatures(filter);
      
       FeatureIterator iterator = result.features();
           while(iterator.hasNext()){
        	   SimpleFeature feature = (SimpleFeature) iterator.next();
        	   
        	   System.out.printf("ID = %s, SnodeID = %s, EnodeID = %s, Length = %s", 
        			   feature.getAttribute("ID"),
        			   feature.getAttribute("SNODEID"),
        			   feature.getAttribute("ENODEID"),
        			   feature.getAttribute("LENGTH")
        			   );
        	   System.out.println();
        	   
           }
 
         

    }
}
