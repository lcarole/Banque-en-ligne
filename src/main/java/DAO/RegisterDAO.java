/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Hashage.HashFunctions;
import JFRame.Register;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author luigi
 */
public class RegisterDAO {
    private PreparedStatement pst;
    private Connection con;
    
    //Vérifie l'existence de l'utilisateur
    public boolean ifExist(Register r){
        try {
            //Initialise la connexion à la bdd
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnection connect = new DBConnection();
            con = DriverManager.getConnection(connect.getCon(),connect.getUser(),connect.getPassword());
            // Création de la requête.
            pst=con.prepareStatement("SELECT Nom,Prenom FROM client where nom = ? and prenom = ?");
            // Insertion des paramètres dans la requête.
            pst.setString(1, r.getNom());
            pst.setString(2, r.getPrenom());
            // Exécution de la requête et récupération du résultat.
            ResultSet rs = pst.executeQuery();
            //Si la requête a renvoyer un résultat, la fonction renvoie true. Sinon, elle renvoie false.
            if(rs.next() == true){
                return true;
            }
            else
                return false;
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Crée l'utilisateur et l'ajoute à la bdd.
    public int createUser(Register r) throws NoSuchAlgorithmException{
        try {
            // Connexion à la bdd.
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnection connect = new DBConnection();
            con = DriverManager.getConnection(connect.getCon(),connect.getUser(),connect.getPassword());
            String hashPassword = new HashFunctions().Hash(r.getPassword());
            // Création de la requête.
            pst=con.prepareStatement("INSERT INTO `client` (`Nom`,`Prenom`,`Email`,`Password`,`Adresse`,`Sexe`,`Date_de_naissance`) "
                    + "VALUES (?,?,?,?,?,?,?)");
            // Insertion des paramètres dans la requête.
            pst.setString(1,r.getNom());
            pst.setString(2,r.getPrenom());
            pst.setString(3,r.getMail());
            pst.setString(4,hashPassword);
            pst.setString(5,r.getAdresse());
            pst.setString(6,r.getSexe());
            pst.setDate(7,java.sql.Date.valueOf(r.getDate()));
            
            // Exécution de la requête.
            pst.executeUpdate();
            //Récupération de l'ID inséré.
            pst=con.prepareStatement("SELECT MAX(idClient) FROM client");
            ResultSet rs = pst.executeQuery();
            //Je vais à la première ligne du résultat de ma requête.
            rs.next();
            // Récupère l'id du nouveau client et lui crée un compte.
            int id = rs.getInt("MAX(idClient)");
            this.createNewCompte(id);
            JOptionPane.showMessageDialog(null, "Compte client crée. Votre identifiant est :" + id,"Création réussie",JOptionPane.INFORMATION_MESSAGE);
            return id;
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    // Fonction qui crée un compte après la création d'un client.
    public void createNewCompte(int idClient){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnection connect = new DBConnection();
            con = DriverManager.getConnection(connect.getCon(),connect.getUser(),connect.getPassword());
            //Je prépare la requête d'insertion.
            pst=con.prepareStatement("Insert into `compte` (`solde`,`idClient`,`libelle`) VALUES (?,?,'Compte courant')");
            pst.setFloat(1, 150);
            pst.setInt(2, idClient);
            pst.executeUpdate();
            
        } 
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
