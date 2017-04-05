/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import javafx.beans.property.StringProperty;


public interface IUtilisateur {

    default public String getNom(){return nomProperty().get();};
    default public void setNom(String value){nomProperty().set(value);};

    default public String getPrenom(){return prenomProperty().get();};
    default public void setPrenom(String value){prenomProperty().set(value);};

    default public String getIdentifiant(){return identifiantProperty().get();};
    default public void setIdentifiant(String value){identifiantProperty().set(value);};

    default public String getMdp(){return mdpProperty().get();};
    default public void setMdp(String value){mdpProperty().set(value);};

    public StringProperty nomProperty();
    public StringProperty prenomProperty();
    public StringProperty identifiantProperty();
    public StringProperty mdpProperty();
}
