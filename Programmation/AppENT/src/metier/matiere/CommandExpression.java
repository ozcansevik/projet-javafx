/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.matiere;

import metier.utilisateur.Professeur;


public class CommandExpression extends CommandMatiere{

    public IMatiere execute(Professeur Prof){
        return FabriqueMatiere.createExpression(Prof);
    }

    @Override
    public String toString(){
        return "Expression";
    }

}
