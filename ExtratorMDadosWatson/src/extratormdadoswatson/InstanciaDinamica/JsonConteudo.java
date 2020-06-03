/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import com.google.gson.Gson;
import extratormdadoswatson.Extrator.MDado;
import java.util.ArrayList;

/**
 *
 * @author thiag
 */
public class JsonConteudo {
    
    //**************CONSTRUTOR**************
    public JsonConteudo(ArrayList<MDado> s){
        this.conteudo = s;
    }
    
    //***************ATRIBUTOS**************
    ArrayList<MDado> conteudo; 
    String resultado = "";
    
    //***************MÉTODOS****************
    public String getJson(){
        
        try{
            Gson gson = new Gson();

            // converte objetos JAVA para JSON e retorna uma String
            resultado = gson.toJson(conteudo);

        }catch(Exception e){
            System.out.println("ERRO JSON: "+e);
        }
        return resultado;
    }
   
}
