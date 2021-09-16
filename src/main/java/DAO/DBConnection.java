/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


/**
 *
 * @author luigi
 */
//C'est la classe qui possède les informations d'accès à la bdd.
public class DBConnection {
    private String con = "jdbc:mysql://localhost:3306/banque"; //hôte
    private String user = "root"; // utilisateur
    private String password = ""; // Mot de passe

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
    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }
    
    
    
    
}
