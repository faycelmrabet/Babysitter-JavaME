/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplicationbabysitting;

/**
 *
 * @author Faycel
 */
public class Reclamation {
    
       private int idReclamation ; 
       private String titre;
       private String message;
       private String nom_babysitter;
       private int idUser ; 
       private String dateReclamation ; 
    
      public Reclamation(){}

    public Reclamation(int idReclamation, String titre, String message, String nom_babysitter, int idUser, String dateReclamation) {
        this.idReclamation = idReclamation;
        this.titre = titre;
        this.message = message;
        this.nom_babysitter = nom_babysitter;
        this.idUser = idUser;
        this.dateReclamation = dateReclamation;
    }

    public Reclamation(String titre, String message, String nom_babysitter, int idUser, String dateReclamation) {
        this.titre = titre;
        this.message = message;
        this.nom_babysitter = nom_babysitter;
        this.idUser = idUser;
        this.dateReclamation = dateReclamation;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public String getTitre() {
        return titre;
    }

    public String getMessage() {
        return message;
    }

    public String getNom_babysitter() {
        return nom_babysitter;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getDateReclamation() {
        return dateReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }
    public void setIdReclamation(String idReclamation) {
        this.idReclamation = Integer.parseInt(idReclamation);
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNom_babysitter(String nom_babysitter) {
        this.nom_babysitter = nom_babysitter;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDateReclamation(String dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", titre=" + titre + ", message=" + message + ", nom_babysitter=" + nom_babysitter + ", idUser=" + idUser + ", dateReclamation=" + dateReclamation + '}';
    }

    public Reclamation getReclamation(){
        return null;
    }
     
}
