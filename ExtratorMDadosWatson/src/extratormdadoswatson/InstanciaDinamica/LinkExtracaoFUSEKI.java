/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import extratormdadoswatson.Fuseki.ConnectFUSEKI;
import extratormdadoswatson.InstanciaEstatica.EntidadeDAO;
import extratormdadoswatson.InstanciaEstatica.InstanciaEstatica;
import extratormdadoswatson.InstanciaEstatica.InstanciaEstaticaDAO;
import extratormdadoswatson.InstanciaEstatica.TipoEstatico;
import extratormdadoswatson.InstanciaEstatica.TipoEstaticoDAO;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;

/**
 *
 * @author thiag
 */
public class LinkExtracaoFUSEKI {
    
    String validade_onto = null;
    OntModel ontologia;
    ConnectFUSEKI conFuseki;
    String baseURI;
            
    public LinkExtracaoFUSEKI(){
        
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
    
   public boolean cadastrarInstanciaDinamicaFuseki(LinkExtracao link, String titulo, String dominio, String descricao, int id_tipo) throws Exception{  
        
        boolean validate_processo = false;
        
        if(validade_onto.equalsIgnoreCase("valido")){
            TipoDinamicoDAO tipodindao = new TipoDinamicoDAO();
            TipoDinamico tipes = tipodindao.obterTipoDinamico(id_tipo);
            EntidadeDAO ent = new EntidadeDAO();
  
            System.out.println("*****************LINK ONT FUSEKI***********************");
            System.out.println("intanciaDinamicaOnto: "+tipes.getOnt_class_uri());
            System.out.println("dominioOnto: "+tipes.getOnt_dominio_uri());
            System.out.println("linkOnto: "+tipes.getOnt_link_uri());
            System.out.println("tituloOnto: "+tipes.getOnt_titulo_uri());
            System.out.println("descricaoOnto: "+tipes.getOnt_descricao_uri());
            System.out.println("*******************************************************");
            
            
            OntClass intanciaDinamicaOnto = ontologia.getOntClass(tipes.getOnt_class_uri());
            DatatypeProperty dominioOnto = ontologia.getDatatypeProperty(tipes.getOnt_dominio_uri());
            DatatypeProperty linkOnto = ontologia.getDatatypeProperty(tipes.getOnt_link_uri());
            DatatypeProperty tituloOnto = ontologia.getDatatypeProperty(tipes.getOnt_titulo_uri());
            DatatypeProperty descricaoOnto = ontologia.getDatatypeProperty(tipes.getOnt_descricao_uri());
          
            System.out.println("*****************LINK EXTRACAO FUSEKI***********************");
            System.out.println("dominioOnto: "+dominio);
            System.out.println("linkOnto: "+link.getLinkIndividual());
            System.out.println("tituloOnto: "+titulo);
            System.out.println("descricaoOnto: "+descricao);
            System.out.println("*******************************************************");
            
            
            //***************INSTANCIAR A ONTOLOGIA***************    
            Individual ind = intanciaDinamicaOnto.createIndividual(baseURI+link.getCodDinamico());
            ontologia.createIndividual(ind);
            ind.addLiteral(dominioOnto, dominio);
            ind.addLiteral(linkOnto, link.getLinkIndividual());
            ind.addLiteral(tituloOnto, titulo);
            ind.addLiteral(descricaoOnto, descricao);
            
            //***************RECUPERAR INSTÂNCIA ESTÁTICA***************   
            InstanciaEstaticaDAO inst = new InstanciaEstaticaDAO();
            List<InstanciaEstatica> listInst= inst.listarInstanciaEstatica();
            
            String codigoEstatico = "";
            for(int i = 0; i < listInst.size(); i++){
                System.out.println("******************************************************");
                System.out.println("CODIGO ESTATICO: "+codigoEstatico);
                System.out.println("SITE: "+listInst.get(i).getSite());
                System.out.println("DOMINIO: "+dominio);
                System.out.println("******************************************************");
                if(listInst.get(i).getSite().contains(dominio)){
                    codigoEstatico = listInst.get(i).getCod_estatico();
                    break;
                }
            }
            //**********************************************************
            
            Individual indFornecedor = ontologia.getIndividual(baseURI+codigoEstatico);
                    
            String codFornecedor = codigoEstatico.replace("E", "F");
            
            Property obj = ontologia.createProperty(baseURI+codFornecedor);
            ind.addProperty(obj, indFornecedor);                                    
            
            conFuseki.setModelo(ontologia);
            
            validate_processo = true;
        }
        
        return validate_processo;
    }
    
    
    public boolean deletarInstanciaDinamica(String codigo){
        
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
