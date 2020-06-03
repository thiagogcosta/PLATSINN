/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaEstatica;

import extratormdadoswatson.Fuseki.ConnectFUSEKI;
import java.util.Iterator;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;

/**
 *
 * @author thiag
 */
public class InstanciaEstaticaFUSEKI {
    
    String validade_onto = null;
    OntModel ontologia;
    ConnectFUSEKI conFuseki;
    String baseURI;
            
    public InstanciaEstaticaFUSEKI(){
        
        conFuseki = new ConnectFUSEKI();
        ontologia = conFuseki.getModelo();
        baseURI = conFuseki.getBaseURI();
            
        
        //******************************VALIDAR MODELO******************************  
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        InfModel modelInf = ModelFactory.createInfModel(reasoner,ontologia);
        ValidityReport vrp1 = modelInf.validate();
        
        if(vrp1.isValid()){
            validade_onto = "valido";
        }else{
            validade_onto = "invalido";
        }
        
    }
    
    public boolean verificarDuplicidadeInstanciaFuseki(InstanciaEstatica instanciaEstatica){ 
        boolean validate_processo = true;
        
        if(validade_onto.equalsIgnoreCase("valido")){
            TipoEstaticoDAO tipestdao = new TipoEstaticoDAO();
            TipoEstatico tipes = tipestdao.obterTipoEstatico(instanciaEstatica);
            
            OntClass intanciaEstaticaOnto = ontologia.getOntClass(tipes.getOnt_class_uri());
            Iterator lstInstances = intanciaEstaticaOnto.listInstances();
            
            //***********VERIFICO SE NÃO HÁ INSTÂNCIA COM O MESMO E-MAIL***********
            while(lstInstances.hasNext()) {
                String aux = ((Individual)lstInstances.next()).toString();
                if(instanciaEstatica.getSite().equalsIgnoreCase(aux)){
                    System.out.println("Site instancia: "+aux);
                    validate_processo = false;
                    break;
                }
            }  
        }
        
        return validate_processo;
    }
    
    public boolean cadastrarInstanciaFuseki(InstanciaEstatica instanciaEstatica){  
        
        boolean validate_processo = false;
        
        if(validade_onto.equalsIgnoreCase("valido")){
            TipoEstaticoDAO tipestdao = new TipoEstaticoDAO();
            TipoEstatico tipes = tipestdao.obterTipoEstatico(instanciaEstatica);
            EntidadeDAO ent = new EntidadeDAO();
  
            OntClass intanciaEstaticaOnto = ontologia.getOntClass(tipes.getOnt_class_uri());
            DatatypeProperty nomeOnto = ontologia.getDatatypeProperty(tipes.getOnt_nome_uri());
            DatatypeProperty siteOnto = ontologia.getDatatypeProperty(tipes.getOnt_site_uri());
            DatatypeProperty entidade = ontologia.getDatatypeProperty(tipes.getOnt_entidade_uri());
          
        
            //***************INSTANCIAR A ONTOLOGIA***************    
            Individual ind = intanciaEstaticaOnto.createIndividual(baseURI+instanciaEstatica.getCod_estatico());
            ontologia.createIndividual(ind);
            ind.addLiteral(nomeOnto, instanciaEstatica.getNome());
            ind.addLiteral(siteOnto, instanciaEstatica.getSite());
            ind.addLiteral(entidade, ent.obterEntidade(instanciaEstatica).getNome());
       
            conFuseki.setModelo(ontologia);
            
            validate_processo = true;
        }
        
        return validate_processo;
    }
    
    
    public boolean deletarInstanciaFuseki(String codigo){
        
        boolean validate_processo = false;

        if(validade_onto.equalsIgnoreCase("valido")){
            
            Individual ind = ontologia.getIndividual(baseURI+codigo);
            ind.remove();
            
            conFuseki.setModelo(ontologia);
            
            validate_processo = true;
        }
        
        return validate_processo;
    }
    
}
