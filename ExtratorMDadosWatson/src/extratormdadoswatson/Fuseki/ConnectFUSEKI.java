/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.Fuseki;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

/**
 *
 * @author thiag
 */
public class ConnectFUSEKI {
    
    DatasetAccessor accessor = null;
    OntModel model;
    String serviceURI = "http://localhost:3030/inovaOnto";
    String baseURI = "http://www.inovamarilia.com.br/ontologia#";
    
    public OntModel getModelo(){
        try{
            accessor = DatasetAccessorFactory.createHTTP(serviceURI);
                
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        
            accessor.getModel().write(buffer);
        
            InputStream inputStream = new ByteArrayInputStream(buffer.toByteArray());
        
        
            model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
       
            model.read(inputStream, null);
            
            
            //model.write(System.out);
        }catch(Exception e){
            System.out.println("ERRO: "+e);
        }
        return model;
    }
    
    public String getBaseURI(){
        return baseURI;
    }
    
    public void setModelo(OntModel m){
        try{
            accessor = DatasetAccessorFactory.createHTTP(serviceURI);
        }catch(Exception e){
            System.out.println("ERRO: "+e);
        }
        accessor.putModel(m);
    }
    
}
