/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JFRame.Login;
import Hashage.HashFunctions;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luigi
 */
public class LoginDAO {
    private PreparedStatement pst;
    private Connection con;
    
    public boolean checkLogin(Login l) throws NoSuchAlgorithmException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnection connect = new DBConnection();
            con = DriverManager.getConnection(connect.getCon(),connect.getUser(),connect.getPassword());
            pst=con.prepareStatement("SELECT idClient,Password FROM client where idClient = ?");
            pst.setInt(1, l.getId());
            
            //J'exécute ma requête et je récupère le résultat.
            ResultSet rs = pst.executeQuery();
            //Je vais à la première ligne du résultat de ma requête.
            if(rs.next()){
                System.out.println(rs.getString("idClient"));
                System.out.println(rs.getString("Password"));
                String passwordDB = rs.getString("Password");
                System.out.println(l.getPassword());
                //Je vérifie le mot de passe et stocke le résultat de la vérification.
                boolean checkPwd = new HashFunctions().checkPassword(l.getPassword(), rs.getString("Password"));
                System.out.println(new HashFunctions().Hash(l.getPassword()));
                if(rs.getInt("idClient") == l.getId() && checkPwd == true){
                    return true;
                }
                else
                    return false;
            }
            else
                    return false;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public PreparedStatement getPst() {
        return pst;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }
}
