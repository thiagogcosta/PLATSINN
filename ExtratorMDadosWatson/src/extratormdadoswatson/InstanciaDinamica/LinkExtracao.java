/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import java.util.Date;

/**
 * 
 * Primeiramente extraiu toda a informação do site os dois hashs...
 * Caso forem iguais os hashs não faço nada...
 * Se tiver link a mais coloco no próximo D que tiver para ser inserido;
 * Caso tenha link que foi excluído eu removo o individual da ontologia;
 * Caso tenha link que foi modificado eu removo o individual faço as alterações e adiciono novamente.
 *
 * CRIAR banco com blacklist;
 * @author thiag
 */
public class LinkExtracao {
    
    //**********************Construtor*******************
    public LinkExtracao(){
        
    };

    //**********************Atributos**********************
    private int id_linkExtracao;
    private String linkIndividual;
    private String codDinamico;
    private String hashExtracao;
    private int fk_id_extracao;
    
    //**********************Métodos**********************
    public int getId_linkExtracao() {
        return id_linkExtracao;
    }

    public void setId_linkExtracao(int id_linkExtracao) {
        this.id_linkExtracao = id_linkExtracao;
    }

    public String getLinkIndividual() {
        return linkIndividual;
    }

    public void setLinkIndividual(String linkIndividual) {
        this.linkIndividual = linkIndividual;
    }

    public String getCodDinamico() {
        return codDinamico;
    }

    public void setCodDinamico(String codDinamico) {
        this.codDinamico = codDinamico;
    }

    public String getHashExtracao() {
        return hashExtracao;
    }

    public void setHashExtracao(String hashExtracao) {
        this.hashExtracao = hashExtracao;
    }

    public int getFk_id_extracao() {
        return fk_id_extracao;
    }
    
    public void setFk_id_extracao(int fk_id_extracao) {
        this.fk_id_extracao = fk_id_extracao;
    }
}
