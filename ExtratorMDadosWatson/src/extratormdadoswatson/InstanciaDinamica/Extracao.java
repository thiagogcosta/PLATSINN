/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import java.util.Date;

/**
 *
 * @author thiag
 */
public class Extracao {
    
    //**********************Construtor*******************
    public Extracao(){
    
    };
    //**********************Atributos********************
    private int id;
    private String linkDominio; 
    private String linkExtracao;
    private int limiteInferior;
    private int limiteSuperior;
    private String hashExtracao;
    private String horario;
    private int fk_id_tipo;
    
    //**********************Métodos**********************
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkDominio() {
        return linkDominio;
    }

    public void setLinkDominio(String linkDominio) {
        this.linkDominio = linkDominio;
    }

    public int getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(int limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public int getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(int limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public String getHashExtracao() {
        return hashExtracao;
    }

    public void setHashExtracao(String hashExtracao) {
        this.hashExtracao = hashExtracao;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getFk_id_tipo() {
        return fk_id_tipo;
    }
    
    public void setFk_id_tipo(int fk_id_tipo) {
        this.fk_id_tipo = fk_id_tipo;
    }
    
    public String getLinkExtracao() {
        return linkExtracao;
    }

    public void setLinkExtracao(String linkExtracao) {
        this.linkExtracao = linkExtracao;
    }
}
