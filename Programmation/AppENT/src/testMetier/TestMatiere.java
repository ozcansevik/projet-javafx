/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMetier;

import metier.matiere.*;
import metier.utilisateur.*;


public abstract class TestMatiere {

    public static void start(){
        Matiere m = (Matiere)FabriqueMatiere.createAlgorithme(FabriqueProfesseur.createForTest("Cedric", "Bouhours"));
        m = (Matiere)FabriqueMatiere.createAlgorithme(FabriqueProfesseur.createForTest("Cedric", "Bouhours"));
        FabriqueMatiere.getProgramme().afficherListMatiere();
    }
}
