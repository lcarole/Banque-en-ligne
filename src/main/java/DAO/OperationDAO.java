/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Hashage.HashFunctions;
import JFRame.Login;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luigi
 */
public class OperationDAO {
    private PreparedStatement pst;
    private Connection con;
    
    public void createOperation(int idCompte, float montantVire, int idDestinataire) throws NoSuchAlgorithmException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnection connect = new DBConnection();
            con = DriverManager.getConnection(connect.getCon(),connect.getUser(),connect.getPassword());
            //Je modifie le solde du compte débiteur.
            pst=con.prepareStatement("UPDATE compte SET Solde = ROUND(Solde - ?,2) WHERE idCompte = ? ");
            pst.setFloat(1, montantVire);
            pst.setInt(2, idCompte);
            
            //J'exécute ma requête.
            pst.executeUpdate();
            
            //J'insère l'opération effectué dans la table Operationdu débiteur.
            pst=con.prepareStatement("INSERT INTO `operation` (`Montant`, `libelle`, `idCompte`) VALUES (-?,?,?)");
            pst.setFloat(1, montantVire);
            pst.setString(2, "Virement débité");
            pst.setInt(3, idCompte);
            //J'exécute ma requête.
            pst.executeUpdate();
            
            
            //Je modifie le solde du compte créditeur.
            pst=con.prepareStatement("UPDATE compte SET Solde = ROUND(Solde + ?,2) WHERE idCompte = ? ");
            pst.setFloat(1, montantVire);
            pst.setInt(2, idDestinataire);
            
            //J'exécute ma requête.
            pst.executeUpdate();
            
            //J'insère l'opération effectué dans la table Operation du créditeur.
            pst=con.prepareStatement("INSERT INTO `operation` (`Montant`, `libelle`, `idCompte`) VALUES (?,?,?)");
            pst.setFloat(1, montantVire);
            pst.setString(2, "Somme créditée");
            pst.setInt(3, idDestinataire);
            //J'exécute ma requête.
            pst.executeUpdate();
            
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PreparedStatement getPst() {
        return pst;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }
}
