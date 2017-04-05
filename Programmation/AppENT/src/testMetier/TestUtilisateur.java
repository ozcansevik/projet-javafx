/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMetier;

import java.io.IOException;
import metier.utilisateur.*;
import metier.groupe.Groupe;

import java.util.List;
import java.util.ArrayList;


public class TestUtilisateur {

    public static void start(){
        System.out.println("Coucou");
        Groupe g1 = new Groupe(1,2);

        try {
        Utilisateur e1 = FabriqueEtudiant.create("Reyes", "Adam");
        System.out.println(e1.toString());

        Utilisateur e2 = FabriqueEtudiant.create("Sevik", "Ozcan");
        Utilisateur a1 = new Admin("Root", "root");

        g1.addEtudiant((Etudiant)e1);
        g1.addEtudiant((Etudiant)e2);

        } catch(IOException e){
            System.err.println(e.getMessage());
        }


        g1.afficherEtudiants();
    }
}
