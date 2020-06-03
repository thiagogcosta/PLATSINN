/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.Extrator;

/**
 *
 * @author thiago
 */

public class Link {
    
    //atributos
    private String url;
    private int profundidade;
    
    //construtor
    public Link(String u, int p){
        setUrl(u);
        setProfundidade(p);
    }
    
    //métodos
    public void setUrl(String u){
        url = u;
    }
    
    public void setProfundidade(int p){
        profundidade = p;
    }
    
    public String getUrl(){
        return url;
    }
    
    public int getProfundidade(){
        return profundidade;
    }
}
