/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import java.security.MessageDigest;

/**
 *
 * @author thiag
 */
public class HashConteudo {
    
    //**************CONSTRUTOR**************
    public HashConteudo(String s){
        this.conteudo = s;
    }
    
    //***************ATRIBUTOS**************
    String conteudo = "";
    String resultado = ""; 
    
    //***************MÉTODOS**************
    public String getHash(){
        
        try{
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigestConteudo[] = algorithm.digest(conteudo.getBytes("UTF-8"));

            StringBuilder hexStringConteudo = new StringBuilder();
            //**************Evitar números negativos**************
            for (byte b : messageDigestConteudo) {
                     hexStringConteudo.append(String.format("%02X", 0xFF & b));
            }
            String conteudoHashHex = hexStringConteudo.toString();

            //System.out.println(conteudoHashHex);
            resultado = conteudoHashHex;
        
        }catch(Exception e){
            System.out.println("Erro HASH: "+e);
        }
        
        return resultado;
    }
}
