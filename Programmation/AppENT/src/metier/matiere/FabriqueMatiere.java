/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.matiere;

import java.util.Iterator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.utilisateur.Professeur;


public abstract class FabriqueMatiere {

    private static ProgrammeDesMatieres programme = new ProgrammeDesMatieres();

    public static IMatiere createAlgorithme(Professeur Prof){
        Algorithme a = new Algorithme(Prof);

        if(programme.containsMatiere(a)){
            return programme.getMatiere(a);
        }else{
            programme.addMatiere(a);
            return a;
        }
    }

    public static IMatiere createExpression(Professeur Prof){
        Expression e = new Expression(Prof);

        if(programme.containsMatiere(e)){
            return programme.getMatiere(e);
        }else{
            programme.addMatiere(e);
            return e;
        }
    }

    public static IMatiere createPoo(Professeur Prof){
        Poo p = new Poo(Prof);

        if(programme.containsMatiere(p)){
            return programme.getMatiere(p);
        }else{
            programme.addMatiere(p);
            return p;
        }
    }

    // Si on ajoute une matiere, il faut ajouter un create correspondant

    public static ProgrammeDesMatieres getProgramme(){
        return programme;
    }
}
