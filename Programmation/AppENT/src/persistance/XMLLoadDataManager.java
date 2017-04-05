/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import metier.utilisateur.IUtilisateur;


public abstract class XMLLoadDataManager {

    public static List<IUtilisateur> loadUtilisateur() {
        List<IUtilisateur> result = null;
        try (XMLDecoder ois = new XMLDecoder(new FileInputStream("src/persistance/utilisateurs.xml"))) {
            result = ((ArrayList<XMLUtilisateur>) ois.readObject()).stream().map(n -> n.getModel()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
