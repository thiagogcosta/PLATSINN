/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaEstatica;

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
public class EntidadeDAO {
    
    public List<Entidade> listarEntidade(){
        
        String sql = "SELECT * FROM tipoentidade";
        List<Entidade> ent = new ArrayList<Entidade>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);

            rset = prep.executeQuery();
            
            while(rset.next()){
                
                Entidade ex = new Entidade(rset.getInt("id_entidade"),rset.getString("nome"));
                
                ent.add(ex);
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
        return ent;
    }
    
     public Entidade obterEntidade(InstanciaEstatica instEst){
        
        String sql = "SELECT * FROM tipoentidade as e WHERE e.id_entidade = ?";
        List<Entidade> tipose = new ArrayList<Entidade>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
            prep.setInt(1,instEst.getFk_id_entidade());
             
            rset = prep.executeQuery();
            
            while(rset.next()){
                
                Entidade ex = new Entidade(rset.getInt("id_entidade"),rset.getString("nome"));
               
                tipose.add(ex);
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
        return tipose.get(0);
    } 
}
