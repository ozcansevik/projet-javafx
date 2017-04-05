/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import metier.utilisateur.*;


public abstract class XMLSaveDataManager {

    public static void saveUtilisateurs(List<IUtilisateur> utilisateurs){
        try (XMLEncoder oos = new XMLEncoder(new FileOutputStream("src/persistance/utilisateurs.xml"))) {
            List<XMLUtilisateur> bn = utilisateurs.stream().map(u -> typeOfUtilisateur(u)).collect(Collectors.toList());
            oos.writeObject(bn);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static XMLUtilisateur typeOfUtilisateur(IUtilisateur u){

        if(u instanceof Etudiant){
            return new XMLEtudiant(u);
        } else if(u instanceof Professeur){
            return new XMLProfesseur(u);
        } else {
            return new XMLAdmin(u);
        }
    }
}
