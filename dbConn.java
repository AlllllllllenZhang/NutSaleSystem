/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allen
 */
public class dbConn {
  static Connection con;
    static PreparedStatement pstmt;
 
    public static PreparedStatement getPreparedState(String databaseName,String sql){
     
       
     try {
        
             Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
             con=DriverManager.getConnection("jdbc:derby:"+databaseName+";create=true");
             pstmt=con.prepareStatement(sql);
         } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
         }
      return pstmt;
 }   
    public static Connection getConn(String databaseName){
     try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
             con=DriverManager.getConnection("jdbc:derby:"+databaseName+";create=true");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
            return con;
    
    }
 
}
