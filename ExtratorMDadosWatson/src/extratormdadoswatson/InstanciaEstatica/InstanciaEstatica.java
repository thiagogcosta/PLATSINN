/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaEstatica;

/**
 *
 * @author thiag
 */
public class InstanciaEstatica {
    
    //*************Construtor*************
    
    public InstanciaEstatica(String n, String s, String c, int fki, int fkt){
        this.nome = n;
        this.site = s; 
        this.cod_estatico = c;
        this.fk_id_entidade = fki;
        this.fk_tipo_estatico = fkt;
    }
    
    public InstanciaEstatica(){
        
    }

    
    //*************Atributos*************
    private int id;
    private String nome; 
    private String site;
    private String cod_estatico; 
    private int fk_id_entidade;
    private int fk_tipo_estatico;
     
    
    //*************Métodos*************
    public int getId(){
        return id; 
    }
    
    public String getSite(){
        return site; 
    }
    
    public String getNome(){
        return nome; 
    }
    
    public void setId(int i) {
        this.id = i;
    }
    
    public void setNome(String n) {
        this.nome = n;
    }
     
    public void setSite(String s) {
        this.site = s;
    }
     
    public String getCod_estatico() {
        return cod_estatico;
    }

    public void setCod_estatico(String cod_estatico) {
        this.cod_estatico = cod_estatico;
    }

    public int getFk_id_entidade() {
        return fk_id_entidade;
    }

    public void setFk_id_entidade(int fk_id_entidade) {
        this.fk_id_entidade = fk_id_entidade;
    }

    public int getFk_tipo_estatico() {
        return fk_tipo_estatico;
    }

    public void setFk_tipo_estatico(int fk_tipo_estatico) {
        this.fk_tipo_estatico = fk_tipo_estatico;
    }
}
