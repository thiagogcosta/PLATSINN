/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaEstatica;

import extratormdadoswatson.Fuseki.ConnectFUSEKI;
import extratormdadoswatson.InstanciaDinamica.Extracao;
import extratormdadoswatson.InstanciaDinamica.ExtracaoDAO;
import extratormdadoswatson.InstanciaDinamica.LinkExtracao;
import extratormdadoswatson.InstanciaDinamica.LinkExtracaoDAO;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;

/**
 *
 * @author thiag
 */
public class InstanciaEstaticaCD {
    
    //*****************CADASTRAR INSTÂNCIA ESTÁTICA****************
    public boolean criarInstanciaEstatica(String n, String s, int id_e, int id_t) throws Exception{
        
        boolean validate = false;
        
        InstanciaEstaticaDAO esd = new InstanciaEstaticaDAO();
        InstanciaEstaticaFUSEKI ef = new InstanciaEstaticaFUSEKI();
        InstanciaEstatica est = new InstanciaEstatica();
           
        est.setNome(n);
        est.setSite(s);
        est.setFk_id_entidade(id_e);
        est.setFk_tipo_estatico(id_t);
        
        
        try{
            InstanciaEstatica ultInstancia = esd.obterUltimaInstanciaEstatica();
            
            if(ultInstancia != null){
                //****************RETIRO A PRIMEIRA LETRA****************
                int num = Integer.parseInt(ultInstancia.getCod_estatico().substring(1)) + 1;
                System.out.println(num);
                est.setCod_estatico("E"+num);

                //*******************************************************
                List<InstanciaEstatica> list = esd.listarInstanciaEstatica();
                int contadorSiteIgual = 0;
                for(int i =0; i < list.size(); i++){
                    if(list.get(i).getSite().equalsIgnoreCase(est.getSite())){
                       contadorSiteIgual++; 
                       break;
                    }
                }

                if(contadorSiteIgual == 0 && ef.verificarDuplicidadeInstanciaFuseki(est) == true){

                    boolean validate1 = ef.cadastrarInstanciaFuseki(est);

                    boolean validate2 = esd.cadastrarInstanciaEstatica(est);

                    if(validate1 == true && validate2 == true){
                        validate = true;
                        System.out.println("INSTANCIA ESTATICA CADASTRADA COM SUCESSO!");
                    }else{
                        System.out.println("ERRO AO CADASTRAR INSTANCIA ESTATICA!");
                    }

                }else{
                    System.out.println("INSTANCIA ESTATICA DUPLICADA!");
                    System.out.println("ERRO AO CADASTRAR INSTANCIA ESTATICA!");
                }
            }else{
                est.setCod_estatico("E1");

                boolean validate1 = ef.cadastrarInstanciaFuseki(est);

                boolean validate2 = esd.cadastrarInstanciaEstatica(est);

                if(validate1 == true && validate2 == true){
                    validate = true;
                    System.out.println("INSTANCIA ESTATICA CADASTRADA COM SUCESSO!");
                }else{
                    System.out.println("ERRO AO CADASTRAR INSTANCIA ESTATICA!");
                }  
        }
           
        }catch (IndexOutOfBoundsException e){
            System.out.println("ERRO: "+e);
        }catch(Exception e){
            System.out.println("ERRO: "+e);
        }
        
        return validate;
    }
    
    //*****************DELETAR INSTÂNCIA ESTÁTICA****************
    public boolean deletarInstanciaEstatica(String codEst) throws Exception{
        
        boolean validate = false;
        boolean verificacao_utilizacao = false;
        
        ExtracaoDAO ex = new ExtracaoDAO();
        List<Extracao> listExt = ex.listarExtracao();
        
        InstanciaEstaticaDAO instDAO = new InstanciaEstaticaDAO();
        List<InstanciaEstatica> listInst = instDAO.listarInstanciaEstatica();
        
        if(listExt != null){
            for(int i=0; i<listExt.size(); i++){
                for(int j=0; j<listInst.size(); j++){
                    if(listInst.get(j).getCod_estatico().equalsIgnoreCase(codEst) && listInst.get(j).getSite().equalsIgnoreCase(listExt.get(i).getLinkDominio())){      
                        verificacao_utilizacao = true;
                    }
                }
            }
        }
        if(verificacao_utilizacao == false){
        
            InstanciaEstaticaDAO esd = new InstanciaEstaticaDAO();
            InstanciaEstaticaFUSEKI ef = new InstanciaEstaticaFUSEKI();

            boolean validate1 = ef.deletarInstanciaFuseki(codEst);

            boolean validate2 = esd.deletarInstanciaEstatica(codEst);

            if(validate1 == true && validate2 == true){
                validate = true;
                System.out.println("INSTANCIA ESTATICA DELETADA COM SUCESSO!");
            }else{
                System.out.println("ERRO AO DELETAR INSTANCIA ESTATICA!");
            }
        }
        return validate;
        
    }
    
    //*****************LISTAR INSTÂNCIA ESTÁTICA****************
    //public void listarInstanciaEstatica()
    
}
