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
public class Babysitter {
   private int id;
   private String nom;
   private String prenom;
   private String email;
   private String adresse;
   private int codePostale;
   private int telephone;
   private int note;

   public Babysitter() {} 

    public Babysitter(int id, String nom, String prenom, String email, String adresse, int codePostale, int telephone, int note) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.telephone = telephone;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCodePostale() {
        return codePostale;
    }

    public int getTelephone() {
        return telephone;
    }

    public int getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostale(int codePostale) {
        this.codePostale = codePostale;
    }
public void setCodePostale(String codePostale) {
        this.codePostale = Integer.parseInt(codePostale);
    }
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
public void setTelephone(String telephone) {
        this.telephone = Integer.parseInt(telephone);
    }
    public void setNote(int note) {
        this.note = note;
    }
    public void setNote(String note) {
        this.note = Integer.parseInt(note);
    }
     public Babysitter getBabysitter(){
         return null;
     }

    public String toString() {
        return "Babysitter{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + ", codePostale=" + codePostale + ", telephone=" + telephone + ", note=" + note + '}';
    }
     
     
}
