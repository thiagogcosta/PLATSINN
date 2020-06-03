/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import extratormdadoswatson.DB.ConnectDB;
import extratormdadoswatson.InstanciaDinamica.Extracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiag
 */
public class ExtracaoDAO {
    
    public boolean cadastrarExtracao(Extracao e) throws Exception{
        
        String sql = "INSERT INTO extracao (link_dominio, link_extracao, hash_extracao,limite_inferior,limite_superior,fk_id_tipo,horario) values (?,?,?,?,?,?,?)";
        Connection conn = null; 
        PreparedStatement prep = null;
        
        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        prep.setString(1,e.getLinkDominio());
        prep.setString(2,e.getLinkExtracao());
        prep.setString(3,e.getHashExtracao());
        prep.setInt(4,e.getLimiteInferior());
        prep.setInt(5,e.getLimiteSuperior());
        prep.setInt(6,e.getFk_id_tipo());
        prep.setString(7,e.getHorario());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
    
    public boolean atualizarExtracao(int limiteinf, int limitesup, int id) throws Exception{
        
        String sql = "UPDATE extracao SET limite_inferior = ?, limite_superior = ? WHERE id_extracao = ?";
        
        Connection conn = null; 
        PreparedStatement prep = null;
        
        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        prep.setInt(1,limiteinf);
        prep.setInt(2,limitesup);
        prep.setInt(3, id);
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
    
    public Extracao obterUltimaExtracao(){
        
        String sql = "SELECT * FROM extracao AS e ORDER BY e.id_extracao DESC LIMIT 1";
        List<Extracao> extracoes = new ArrayList<Extracao>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);

            rset = prep.executeQuery();
            
            while(rset.next()){
                
                Extracao ex = new Extracao();
                
                ex.setId(rset.getInt("id_extracao"));
                ex.setLinkDominio(rset.getString("link_dominio"));
                ex.setLinkExtracao(rset.getString("link_extracao"));
                ex.setLimiteInferior(rset.getInt("limite_inferior"));
                ex.setLimiteSuperior(rset.getInt("limite_superior"));
                ex.setHashExtracao(rset.getString("hash_extracao"));
                ex.setHorario(rset.getString("horario"));
                ex.setFk_id_tipo(rset.getInt("fk_id_tipo"));
                
                extracoes.add(ex);
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
        if(extracoes.size()>0)
            return extracoes.get(0);
        else
            return null;
    } 
    
    public Extracao obterExtracao(String dominio){
        
        String sql = "SELECT * FROM extracao AS e WHERE e.link_extracao = ?";
        List<Extracao> extracoes = new ArrayList<Extracao>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
            prep.setString(1,dominio);
            
            rset = prep.executeQuery();
            
            while(rset.next()){
                
                Extracao ex = new Extracao();
                
                ex.setId(rset.getInt("id_extracao"));
                ex.setLinkDominio(rset.getString("link_dominio"));
                ex.setLinkExtracao(rset.getString("link_extracao"));
                ex.setLimiteInferior(rset.getInt("limite_inferior"));
                ex.setLimiteSuperior(rset.getInt("limite_superior"));
                ex.setHashExtracao(rset.getString("hash_extracao"));
                ex.setHorario(rset.getString("horario"));
                ex.setFk_id_tipo(rset.getInt("fk_id_tipo"));
                
                extracoes.add(ex);
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
        if(extracoes.size()>0)
            return extracoes.get(0);
        else
            return null;
    }
    
    public List<Extracao> listarExtracao(){
        
        String sql = "SELECT * FROM extracao";
        List<Extracao> extracoes = new ArrayList<Extracao>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
           
            rset = prep.executeQuery();
            
            while(rset.next()){
                
                Extracao ex = new Extracao();
                
                ex.setId(rset.getInt("id_extracao"));
                ex.setLinkDominio(rset.getString("link_dominio"));
                ex.setLinkExtracao(rset.getString("link_extracao"));
                ex.setLimiteInferior(rset.getInt("limite_inferior"));
                ex.setLimiteSuperior(rset.getInt("limite_superior"));
                ex.setHashExtracao(rset.getString("hash_extracao"));
                ex.setHorario(rset.getString("horario"));
                ex.setFk_id_tipo(rset.getInt("fk_id_tipo"));
                
                extracoes.add(ex);
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
        if(extracoes.size()>0)
            return extracoes;
        else
            return null;
    }
    
    public boolean deletarExtracao(String link) throws Exception{
        
        Extracao l = obterExtracao(link);
                
        String sql = "DELETE FROM extracao WHERE id_extracao = ?";
        Connection conn = null; 
        PreparedStatement prep = null;

        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        
        prep.setInt(1,l.getId());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
}
