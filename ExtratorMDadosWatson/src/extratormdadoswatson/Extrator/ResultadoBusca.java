/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.Extrator;

import java.util.ArrayList;

/**
 *
 * @author thiag
 */
public class ResultadoBusca {
    
    public ResultadoBusca(){
        
    }
    
    //*****************Atributos*****************
    private String tipoExtracao;
    private String URL;
    private ArrayList<MDado> mRdf = new ArrayList();
    
    
    //*****************Métodos*****************
    public String getTipoExtracao() {
        return tipoExtracao;
    }

    public void setTipoExtracao(String tipoExtracao) {
        this.tipoExtracao = tipoExtracao;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public ArrayList<MDado> getmRdf() {
        return mRdf;
    }
    
    public void setmRdf(ArrayList<MDado> mRdf) {
        this.mRdf = mRdf;
    }
}
