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
public class InstanciaEstaticaDAO {
    
    public boolean cadastrarInstanciaEstatica(InstanciaEstatica instEst) throws Exception{
        
        String sql = "INSERT INTO InstanciaEstatica (nome, site, cod_estatico, fk_id_entidade, fk_id_tipo_estatico) values (?,?,?,?,?)";
        Connection conn = null; 
        PreparedStatement prep = null;
        
        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        prep.setString(1,instEst.getNome());
        prep.setString(2,instEst.getSite());
        prep.setString(3,instEst.getCod_estatico());
        prep.setInt(4,instEst.getFk_id_entidade());
        prep.setInt(5,instEst.getFk_tipo_estatico());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
     
    public boolean deletarInstanciaEstatica(String cod) throws Exception{
        
        InstanciaEstatica intancia = obterInstanciaEstatica(cod);
                
        String sql = "DELETE FROM InstanciaEstatica WHERE id_instancia_estatica = ?";
        Connection conn = null; 
        PreparedStatement prep = null;

        conn = ConnectDB.createConnectionToMySQL();

        prep = conn.prepareStatement(sql);
        
        prep.setInt(1,intancia.getId());
        prep.executeUpdate();
        prep.close();
        
        return true;
    }
     
    
    public InstanciaEstatica obterUltimaInstanciaEstatica() throws Exception{
        
        String sql = "SELECT * FROM InstanciaEstatica as i order by i.id_instancia_estatica DESC limit 1";
        List<InstanciaEstatica> instancias = new ArrayList<InstanciaEstatica>();
        
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

                    InstanciaEstatica ex = new InstanciaEstatica();

                    ex.setId(rset.getInt("id_instancia_estatica"));
                    ex.setNome(rset.getString("nome"));
                    ex.setSite(rset.getString("site"));
                    ex.setCod_estatico(rset.getString("cod_estatico"));
                    ex.setFk_id_entidade(rset.getInt("fk_id_entidade"));
                    ex.setFk_tipo_estatico(rset.getInt("fk_id_tipo_estatico"));

                    instancias.add(ex);
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
        if(instancias.size()>0)
            return instancias.get(0);
        else
            return null;
    }
    
    public  List<InstanciaEstatica> listarInstanciaEstatica() throws Exception{
        
        String sql = "SELECT * FROM InstanciaEstatica";
        List<InstanciaEstatica> instancias = new ArrayList<InstanciaEstatica>();
        
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);

            rset = prep.executeQuery();
            
            while(rset.next()){
                
                InstanciaEstatica ex = new InstanciaEstatica();
                
                ex.setId(rset.getInt("id_instancia_estatica"));
                ex.setNome(rset.getString("nome"));
                ex.setSite(rset.getString("site"));
                ex.setCod_estatico(rset.getString("cod_estatico"));
                ex.setFk_id_entidade(rset.getInt("fk_id_entidade"));
                ex.setFk_tipo_estatico(rset.getInt("fk_id_tipo_estatico"));

                instancias.add(ex);
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
        
        if(instancias.size() > 0){
            return instancias;
        }else{
            return null;
        }
    } 
    
    public  InstanciaEstatica obterInstanciaEstatica(String codigo) throws Exception{
        
        String sql = "SELECT * FROM InstanciaEstatica as inst WHERE inst.cod_estatico = ?";
 
        Connection conn = null; 
        PreparedStatement prep = null;
        ResultSet rset = null;
        InstanciaEstatica ex = new InstanciaEstatica();
        
        try{
            conn = ConnectDB.createConnectionToMySQL();
 
            prep = conn.prepareStatement(sql);
            prep.setString(1,codigo);
            
            rset = prep.executeQuery();
            
            if(rset.first() == true){         
                ex.setId(rset.getInt("id_instancia_estatica"));
                ex.setNome(rset.getString("nome"));
                ex.setSite(rset.getString("site"));
                ex.setCod_estatico(rset.getString("cod_estatico"));
                ex.setFk_id_entidade(rset.getInt("fk_id_entidade"));
                ex.setFk_tipo_estatico(rset.getInt("fk_id_tipo_estatico"));
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
        return ex;
    } 
}
