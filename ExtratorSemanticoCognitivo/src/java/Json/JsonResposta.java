/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import com.google.gson.Gson;
import extratormdadoswatson.InstanciaDinamica.Extracao;
import extratormdadoswatson.InstanciaDinamica.LinkExtracao;
import extratormdadoswatson.InstanciaDinamica.TipoDinamico;
import extratormdadoswatson.InstanciaEstatica.Entidade;
import extratormdadoswatson.InstanciaEstatica.InstanciaEstatica;
import extratormdadoswatson.InstanciaEstatica.TipoEstatico;
import java.util.List;

/**
 *
 * @author thiag
 */
public class JsonResposta {
    
    public JsonResposta(){
       
    }
    
    
    //***************ATRIBUTOS**************
    List<TipoDinamico> listaTipoDinamico; 
    List<TipoEstatico> listaTipoEstatico; 
    List<Entidade> listaEntidade;
    List<Extracao> listaExtracao; 
    List<InstanciaEstatica> listaInstanciaEstatica;
    List<LinkExtracao> listaInstanciaDinamica;
    
    String resultado = "";
    
    //***************MÉTODOS****************
    public String getJsonTipoDinamico(List<TipoDinamico> l){
        listaTipoDinamico = l;
        
        try{
            Gson gson = new Gson();

            // converte objetos JAVA para JSON e retorna uma String
            resultado = gson.toJson(listaTipoDinamico);

        }catch(Exception e){
            System.out.println("ERRO JSON: "+e);
        }
        return resultado;
    }
    
    public String getJsonTipoEstatico(List<TipoEstatico> l){
        listaTipoEstatico = l;
        
        try{
            Gson gson = new Gson();

            // converte objetos JAVA para JSON e retorna uma String
            resultado = gson.toJson(listaTipoEstatico);

        }catch(Exception e){
            System.out.println("ERRO JSON: "+e);
        }
        return resultado;
    }
    
    public String getJsonEntidade(List<Entidade> l){
        listaEntidade = l;
                
        try{
            Gson gson = new Gson();

            // converte objetos JAVA para JSON e retorna uma String
            resultado = gson.toJson(listaEntidade);

        }catch(Exception e){
            System.out.println("ERRO JSON: "+e);
        }
        return resultado;
    }
    
    public String getJsonExtracao(List<Extracao> l){
        listaExtracao = l;
        
        try{
            Gson gson = new Gson();

            // converte objetos JAVA para JSON e retorna uma String
            resultado = gson.toJson(listaExtracao);

        }catch(Exception e){
            System.out.println("ERRO JSON: "+e);
        }
        return resultado;
    }
    
    public String getJsonInstanciaEstatica(List<InstanciaEstatica> l){
        listaInstanciaEstatica = l;
        
        try{
            Gson gson = new Gson();

            // converte objetos JAVA para JSON e retorna uma String
            resultado = gson.toJson(listaInstanciaEstatica);

        }catch(Exception e){
            System.out.println("ERRO JSON: "+e);
        }
        return resultado;
    }
    
    public String getJsonInstanciaDinamica(List<LinkExtracao> l){
        listaInstanciaDinamica = l;
        
        try{
            Gson gson = new Gson();

            // converte objetos JAVA para JSON e retorna uma String
            resultado = gson.toJson(listaInstanciaDinamica);

        }catch(Exception e){
            System.out.println("ERRO JSON: "+e);
        }
        return resultado;
    }
    
}
