/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hashage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luigi
 */
public class HashFunctions {
    
    // Fonction qui prend en paramètres l'algorithme de hashage et le mdp afin de procéder au hashage du mdp.
    public String Hash(String password) throws NoSuchAlgorithmException{
        //Hache le mot de passe en byte en utilisant l'algorithme de hachage SHA-256.    
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        // convertir le mot de passe actuellement en bytes en hexadécimal.
        StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        String hashedPassword = s.toString();
        return hashedPassword;
    }
    
    // Fonction qui vérifie si le mot de passe saisie par l'utilisateur
    // et celui de la bdd sont identiques.
    public boolean checkPassword(String password, String storedPwd) throws NoSuchAlgorithmException{
        String saisie = this.Hash(password);
        if(saisie.equals(storedPwd)){
            //System.out.println("Connexion réussie");
            return true;
        }
        else{
            //System.out.println("Le mot de passe est erronée.");
            return false;
        }
    }
}
