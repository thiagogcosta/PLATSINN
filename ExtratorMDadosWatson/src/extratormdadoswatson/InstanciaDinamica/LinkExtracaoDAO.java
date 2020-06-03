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
public class LinkExtracaoDAO {
    
    public boolean cadastrarLinkExtracao(LinkExtracao li) throws Exception{
        
        String sql = "INSERT INTO linkextracao (linkIndividual,codDinamico,hashExtracao,fk_id_extracao) values (?,?,?,?)";
        Connection conn = null; 
        PreparedStatement prep = null;
        
        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        prep.setString(1,li.getLinkIndividual());
        prep.setString(2,li.getCodDinamico());
        prep.setString(3,li.getHashExtracao());
        prep.setInt(4,li.getFk_id_extracao());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
    
     public boolean deletarLinkExtracao(int id) throws Exception{
                
        String sql = "DELETE FROM linkextracao WHERE id_linkExtracao = ?";
        Connection conn = null; 
        PreparedStatement prep = null;

        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        
        prep.setInt(1,id);
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
     
    public boolean deletarLinkExtracao(String link) throws Exception{
        
        LinkExtracao l = obterLinkExtracao(link);
                
        String sql = "DELETE FROM linkextracao as l WHERE l.id_linkExtracao = ?";
        Connection conn = null; 
        PreparedStatement prep = null;

        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        
        prep.setString(1,l.getLinkIndividual());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
    
    public LinkExtracao obterLinkExtracao(String link){
        
        String sql = "SELECT * FROM linkextracao as l WHERE l.linkIndividual = ?";
        List<LinkExtracao> links = new ArrayList<LinkExtracao>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
            prep.setString(1,link);
             
            rset = prep.executeQuery();
            
            while(rset.next()){
                
                LinkExtracao l = new LinkExtracao();
                l.setId_linkExtracao(rset.getInt("id_linkExtracao"));
                l.setLinkIndividual(rset.getString("linkIndividual"));
                l.setCodDinamico(rset.getString("codDinamico"));
                l.setHashExtracao(rset.getString("hashExtracao"));
                l.setFk_id_extracao(rset.getInt("fk_id_extracao"));
                
                links.add(l);
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
        
        if(links.size() > 0){
         return links.get(0);   
        }else{
            return null;
        }
    } 
    
    public List<LinkExtracao> ListaLinkExtracao(){
        
        String sql = "SELECT * FROM linkextracao";
        List<LinkExtracao> links = new ArrayList<LinkExtracao>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);

            rset = prep.executeQuery();
            
            while(rset.next()){
                
                LinkExtracao l = new LinkExtracao();
                l.setId_linkExtracao(rset.getInt("id_linkExtracao"));
                l.setLinkIndividual(rset.getString("linkIndividual"));
                l.setCodDinamico(rset.getString("codDinamico"));
                l.setHashExtracao(rset.getString("hashExtracao"));
                l.setFk_id_extracao(rset.getInt("fk_id_extracao"));
                
                links.add(l);
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

        if(links.size() > 0){
          return links;
        }else{
            return null;
        }
    } 
    
    public LinkExtracao obterUltimaLinkExtracao() throws Exception{
        
        String sql = "SELECT * FROM linkextracao as i order by i.id_linkExtracao DESC limit 1";
        List<LinkExtracao> links = new ArrayList<LinkExtracao>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);

            rset = prep.executeQuery();
            
            //**************CASO TIVER ALGO NO BD**************
            //rset.first() => aponta para o primeiro lugar do vetor, logo caso tem algo, faz:
            if(rset.first() == true){
                do{

                    LinkExtracao ex = new LinkExtracao();
                    ex.setId_linkExtracao(rset.getInt("id_linkExtracao"));
                    ex.setLinkIndividual(rset.getString("linkIndividual"));
                    ex.setHashExtracao(rset.getString("hashExtracao"));
                    ex.setCodDinamico(rset.getString("codDinamico"));
                    ex.setFk_id_extracao(rset.getInt("fk_id_extracao"));
                  
                    links.add(ex);
                }while(rset.next());
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
        if(links.size()>0)
            return links.get(0);
        else
            return null;
    }
    
}
