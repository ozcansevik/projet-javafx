/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.note;

import metier.matiere.FabriqueMatiere;
import metier.matiere.Matiere;
import metier.utilisateur.FabriqueProfesseur;
import metier.utilisateur.Professeur;


public abstract class FabriqueNote {

     public static Note create(int note,int coefficient,Professeur p, Matiere m){

         Note n;
         n= new Note(note,m,coefficient);

         return n;
    }


}
