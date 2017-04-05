/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.matiere;

import metier.matiere.Matiere;
import metier.utilisateur.Professeur;

public class Algorithme extends Matiere{


    Algorithme(Professeur prof){
        super("Algorithme", 1.5F, prof, "#DC143C");
    }

    @Override
    public boolean equals(Object o){

        if(o == this) return true;
        if(!(o instanceof Algorithme)) return false;

        return ((Matiere)o).getProfesseur().equals(this.getProfesseur());
    }
}
