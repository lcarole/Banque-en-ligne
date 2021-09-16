/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JFRame.SelectCompte;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luigi
 */
public class SelectCompteDAO {
    private PreparedStatement pst;
    private Connection con;
    
    // Mise à jour du tableau des comtpes.
    public void tableUpdate(JTable tableCompte, int idClient){
    int cpt;
        try {
            //Connexion à la bdd.
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBConnection connect = new DBConnection();
            con = DriverManager.getConnection(connect.getCon(),connect.getUser(),connect.getPassword());
            // Création de la reuête.
            pst=con.prepareStatement("SELECT * FROM compte WHERE idClient = ?");
            // Insertion des paramètres.
            pst.setInt(1, idClient);
            //Exécution de la requête.
            ResultSet rs = pst.executeQuery();
            
            ResultSetMetaData rsmd=rs.getMetaData();  //Je récupère mes données
            cpt=rsmd.getColumnCount();    //Je recupère le nombre de colonnes dans la table
            DefaultTableModel  dtm=(DefaultTableModel)tableCompte.getModel();
            dtm.setRowCount(0);
            
            while(rs.next()){
                
                Vector vect = new Vector();
                
                for(int i=1;i<=cpt;i++){
                    vect.add(rs.getInt("idCompte"));
                    vect.add(rs.getFloat("Solde"));
                }
                dtm.addRow(vect);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SelectCompteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
