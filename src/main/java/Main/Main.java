/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import JFRame.Login;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

/**
 *
 * @author luigi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crée le JFrame Login.
        Login Frame = new Login();
        String s = "Bonjour";
        //Affiche le JFrame.
        Frame.setVisible(true);
    }
    
}
