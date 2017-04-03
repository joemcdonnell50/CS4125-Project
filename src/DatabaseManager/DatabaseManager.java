/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bprieto
 */
public abstract class DatabaseManager {
    String url="jdbc:mysql://localhost:8889/CS4125Project?zeroDateTimeBehavior=convertToNull";
    String user="root";
    String password="root";

    /**
     *
     */
    Connection connexion;
    public DatabaseManager(){
         try {
            
            this.connexion=DriverManager.getConnection(this.url,this.user,this.password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnexion() {
        return connexion;
    }

    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }
    
   
    
    
     public void disconnect() {
        if (connexion != null) {
            try {
                connexion.close();
                connexion = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
}
            
    

