/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

/**
 *
 * @author thiag
 */
public class TipoDinamico {
    
    //Construtor
    public TipoDinamico(){
        
    }
    
    //Atributos
    private int id;
    private String nome;
    private String ont_class_uri; 
    private String ont_dominio_uri;
    private String ont_link_uri;
    private String ont_titulo_uri;
    private String ont_descricao_uri;
    
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

    public String getOnt_dominio_uri() {
        return ont_dominio_uri;
    }

    public void setOnt_dominio_uri(String ont_dominio_uri) {
        this.ont_dominio_uri = ont_dominio_uri;
    }

    public String getOnt_link_uri() {
        return ont_link_uri;
    }

    public void setOnt_link_uri(String ont_link_uri) {
        this.ont_link_uri = ont_link_uri;
    }

    public String getOnt_titulo_uri() {
        return ont_titulo_uri;
    }

    public void setOnt_titulo_uri(String ont_titulo_uri) {
        this.ont_titulo_uri = ont_titulo_uri;
    }

    public String getOnt_descricao_uri() {
        return ont_descricao_uri;
    }

    public void setOnt_descricao_uri(String ont_descricao_uri) {
        this.ont_descricao_uri = ont_descricao_uri;
    }

    
}
