/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import extratormdadoswatson.DB.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiag
 */
public class TipoDinamicoDAO {
    
    public boolean cadastrarTipoDinamico(TipoDinamico td) throws Exception{
        
        String sql = "INSERT INTO tipodinamico (nome,ont_class_uri,ont_dominio_uri,ont_link_uri,ont_titulo_uri,ont_descricao_uri) values (?,?,?,?,?,?)";
        Connection conn = null; 
        PreparedStatement prep = null;
        
        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        prep.setString(1,td.getNome());
        prep.setString(2,td.getOnt_class_uri());
        prep.setString(3,td.getOnt_dominio_uri());
        prep.setString(4,td.getOnt_link_uri());
        prep.setString(5,td.getOnt_titulo_uri());
        prep.setString(6,td.getOnt_descricao_uri());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
    
    public List<TipoDinamico> ListaTipoDinamico(){
        
        String sql = "SELECT * FROM tipodinamico";
        List<TipoDinamico> tipose = new ArrayList<TipoDinamico>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);

            rset = prep.executeQuery();
            
            while(rset.next()){
                
                TipoDinamico td = new TipoDinamico();
                td.setId(rset.getInt("id_tipo_dinamico"));
                td.setNome(rset.getString("nome"));
                td.setOnt_class_uri(rset.getString("ont_class_uri"));
                td.setOnt_titulo_uri(rset.getString("ont_titulo_uri"));
                td.setOnt_link_uri(rset.getString("ont_link_uri"));
                td.setOnt_dominio_uri(rset.getString("ont_dominio_uri"));
                td.setOnt_descricao_uri(rset.getString("ont_descricao_uri"));
                
                tipose.add(td);
            }
            
        }catch(Exception e){
            System.out.println("ERRO CONEXAO: "+e);
        }finally{
 
            try{
                if(rset != null){
                    rset.close();
                }
                if(prep != null){
                    prep.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } 

        if(tipose.size() > 0){
          return tipose;
        }else{
            return null;
        }
    } 
    
    //****************OBTER TIPO_DINAMICO PARA DELETAR DO BANCO************************
    public TipoDinamico obterTipoDinamico(int id){
        
        String sql = "SELECT * FROM tipodinamico as t WHERE t.id_tipo_dinamico = ?";
        List<TipoDinamico> tipose = new ArrayList<TipoDinamico>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
            prep.setInt(1,id);
             
            rset = prep.executeQuery();
            
            while(rset.next()){
                
                TipoDinamico td = new TipoDinamico();
                td.setId(rset.getInt("id_tipo_dinamico"));
                td.setNome(rset.getString("nome"));
                td.setOnt_class_uri(rset.getString("ont_class_uri"));
                td.setOnt_titulo_uri(rset.getString("ont_titulo_uri"));
                td.setOnt_link_uri(rset.getString("ont_link_uri"));
                td.setOnt_dominio_uri(rset.getString("ont_dominio_uri"));
                td.setOnt_descricao_uri(rset.getString("ont_descricao_uri"));
                
                tipose.add(td);
            }
            
        }catch(Exception e){
            System.out.println("ERRO CONEXAO: "+e);
        }finally{
 
            try{
                if(rset != null){
                    rset.close();
                }
                if(prep != null){
                    prep.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
        
        if(tipose.size() > 0){
         return tipose.get(0);   
        }else{
            return null;
        }
    } 
    
    //****************OBTER TIPO_DINAMICO PARA DELETAR DO BANCO****************
    public TipoDinamico obterTipoDinamico(String nome){
        
        String sql = "SELECT * FROM tipodinamico as t WHERE t.nome = ?";
        List<TipoDinamico> tipose = new ArrayList<TipoDinamico>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
            prep.setString(1,nome);
             
            rset = prep.executeQuery();
            
            while(rset.next()){
                
                TipoDinamico td = new TipoDinamico();
                td.setId(rset.getInt("id_tipo_dinamico"));
                td.setNome(rset.getString("nome"));
                td.setOnt_class_uri(rset.getString("ont_class_uri"));
                td.setOnt_titulo_uri(rset.getString("ont_titulo_uri"));
                td.setOnt_link_uri(rset.getString("ont_link_uri"));
                td.setOnt_dominio_uri(rset.getString("ont_dominio_uri"));
                td.setOnt_descricao_uri(rset.getString("ont_descricao_uri"));
                
                tipose.add(td);
            }
            
        }catch(Exception e){
            System.out.println("ERRO CONEXAO: "+e);
        }finally{
 
            try{
                if(rset != null){
                    rset.close();
                }
                if(prep != null){
                    prep.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
        
        if(tipose.size() > 0){
         return tipose.get(0);   
        }else{
            return null;
        }
    } 
    
    public boolean deletarTipoDinamico(String nome) throws Exception{
        
        TipoDinamico tp = obterTipoDinamico(nome);
                
        String sql = "DELETE FROM tipodinamico as td WHERE id_tipo_dinamico = ?";
        Connection conn = null; 
        PreparedStatement prep = null;

        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        
        prep.setInt(1,tp.getId());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
    
}
