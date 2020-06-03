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
public class TipoEstatico {
    
    //Construtor
    public TipoEstatico(int i, String n){
        this.id = i; 
        this.nome = n;
    } 
    
    public TipoEstatico(){
        
    }
    
    //Atributos
    private int id;
    private String nome;
    private String ont_class_uri; 
    private String ont_site_uri;
    private String ont_nome_uri;
    private String ont_entidade_uri;

    
    //Métodos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getOnt_class_uri() {
        return ont_class_uri;
    }

    public void setOnt_class_uri(String ont_class_uri) {
        this.ont_class_uri = ont_class_uri;
    }
    
    public String getOnt_site_uri() {
        return ont_site_uri;
    }

    public void setOnt_site_uri(String ont_site_uri) {
        this.ont_site_uri = ont_site_uri;
    }

    public String getOnt_nome_uri() {
        return ont_nome_uri;
    }

    public void setOnt_nome_uri(String ont_nome_uri) {
        this.ont_nome_uri = ont_nome_uri;
    }

    public String getOnt_entidade_uri() {
        return ont_entidade_uri;
    }

    public void setOnt_entidade_uri(String ont_entidade_uri) {
        this.ont_entidade_uri = ont_entidade_uri;
    }
    
}
