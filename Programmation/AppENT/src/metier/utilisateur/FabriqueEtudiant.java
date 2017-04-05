/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.note.Note;


public abstract class  FabriqueEtudiant {

    public static Etudiant create(String nom, String prenom) throws IOException{

        if(nom.equals("") || nom == null || prenom.equals("") || prenom == null ){
            throw new IOException();
        }

        return new Etudiant(nom, prenom);
    }
}
