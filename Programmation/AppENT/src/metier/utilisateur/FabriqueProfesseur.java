/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.groupe.IGroupe;


public abstract class FabriqueProfesseur {

    public static Professeur create(String nom, String prenom, ObservableList<IGroupe> groupe) throws IOException{

        if(nom.equals("") || nom == null || prenom.equals("") || prenom == null || groupe.size() == 0){
            throw new IOException();
        }

        return new Professeur(nom, prenom, groupe);
    }

    public static Professeur createForTest(String nom, String prenom){
        return new Professeur(nom, prenom, null);
    }
}
