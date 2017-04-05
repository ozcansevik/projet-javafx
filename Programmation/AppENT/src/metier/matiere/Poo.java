/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.matiere;

import metier.matiere.Matiere;
import metier.utilisateur.Professeur;


public class Poo extends Matiere{

    Poo(Professeur prof){
        super("Poo", 2.5F, prof, "#2E8B57");
    }

    @Override
    public boolean equals(Object o){

        if(o == this) return true;
        if(!(o instanceof Poo)) return false;

        return ((Matiere)o).getProfesseur().equals(this.getProfesseur());
    }

}
