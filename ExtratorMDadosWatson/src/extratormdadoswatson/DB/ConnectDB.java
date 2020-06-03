/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.DB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author thiag
 */
public class ConnectDB {
    
   private static final String usuario = "root";
 
   private static final String senha = "";
   
   //?autoReconnect=true&useSSL=false, para tirar a mensagem de Warning
   private static final String DB_URL = "jdbc:mysql://localhost:3306/inovadb?autoReconnect=true&useSSL=false";
   
   public static Connection createConnectionToMySQL() throws Exception{
      
      Class.forName("com.mysql.jdbc.Driver"); 

      Connection connection = DriverManager.getConnection(DB_URL, usuario, senha);

      return connection;   
   }
  
}
