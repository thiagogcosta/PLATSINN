/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaEstatica;

import extratormdadoswatson.DB.ConnectDB;
import extratormdadoswatson.InstanciaDinamica.Extracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiag
 */
public class TipoEstaticoDAO {
    
    public List<TipoEstatico> ListaTipoEstatico(){
        
        String sql = "SELECT * FROM tipoEstatico";
        List<TipoEstatico> tipose = new ArrayList<TipoEstatico>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);

            rset = prep.executeQuery();
            
            while(rset.next()){
                
                TipoEstatico ex = new TipoEstatico();
                ex.setId(rset.getInt("id_tipo_estatico"));
                ex.setNome(rset.getString("nome"));
                ex.setOnt_class_uri(rset.getString("ont_class_uri"));
                ex.setOnt_entidade_uri(rset.getString("ont_site_uri"));
                ex.setOnt_nome_uri(rset.getString("ont_nome_uri"));
                ex.setOnt_site_uri(rset.getString("ont_entidade_uri"));
                
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
        return tipose;
    } 
    
    public TipoEstatico obterTipoEstatico(InstanciaEstatica instEst){
        
        String sql = "SELECT * FROM tipoEstatico as t WHERE t.id_tipo_estatico = ?";
        List<TipoEstatico> tipose = new ArrayList<TipoEstatico>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
            prep.setInt(1,instEst.getFk_tipo_estatico());
             
            rset = prep.executeQuery();
            
            while(rset.next()){
                
                TipoEstatico ex = new TipoEstatico();
                ex.setId(rset.getInt("id_tipo_estatico"));
                ex.setNome(rset.getString("nome"));
                ex.setOnt_class_uri(rset.getString("ont_class_uri"));
                ex.setOnt_entidade_uri(rset.getString("ont_entidade_uri"));
                ex.setOnt_nome_uri(rset.getString("ont_nome_uri"));
                ex.setOnt_site_uri(rset.getString("ont_site_uri"));
                
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
