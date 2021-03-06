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
public class Dado {
    
    //atributo
    private String tipo;
    private String tag; 
    private String conteudo;
    private int profundidade;
    private String bUri;
    private String css;
    
    //construtor
    public Dado(String tipo, String tag, String conteudo, String bUri, String css, int profundidade){
        this(tipo, tag, conteudo,bUri, css);
        setProfundidade(profundidade);
    }
    
    public Dado(String tipo, String tag, String conteudo, String bUri, String css){
        this(tipo, tag, conteudo,bUri);
        setCss(css);
    }
    
    public Dado(String tipo, String tag, String conteudo, String bUri){
        this(tipo, tag, conteudo);
        setBUri(bUri);
    }
    
    public Dado(String tipo, String tag, String conteudo){
        setTipo(tipo);
        setTag(tag);
        setConteudo(conteudo);
    }
    
    //métodos
    public String getTipo(){
        return tipo;
    }
    
    public String getTag(){
        return tag;
    }
    
    public String getConteudo(){
        return conteudo;
    }
    
    public String getBUri(){
        return bUri;
    }
    
    public int getProfundidade(){
        return profundidade;
    }
    
    public String getCss(){
        return css;
    }
    
    public void setTipo(String tp){
        this.tipo = tp;
    }
    
    public void setTag(String tg){
        this.tag = tg;
    }
    
    public void setConteudo(String c){
        this.conteudo = c;
    }
    
    public void setBUri(String bUri){
        this.bUri = bUri;
    }
    
    public void setProfundidade(int p){
        this.profundidade = p;
    }
    
    public void setCss(String c){
        this.css = c;
    }
}
