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
public class Parent {
    private int id;
    private int cin;
    private int codepostale;
    private String username;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String adresse;
    private int telephone;
    private int nbreEnfant;
    private String typeUser;
    private int etat;

    public Parent(){
        
    }
    public Parent(int id, int cin,int codepostale, String nom, String prenom, String username, String password, String email, String adresse, int telephone, int nbreEnfant, String typeUser, int etat) {
        this.id = id;
        this.cin = cin;
        this.codepostale=codepostale;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.nbreEnfant = nbreEnfant;
        this.typeUser = typeUser;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public int getNbreEnfant() {
        return nbreEnfant;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public int getEtat() {
        return etat;
    }

     public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    public void setId(int id) {
        this.id = id;
    }

     public void setCin(String cin) {
        this.cin = Integer.parseInt(cin);
    }
    public void setCin(int cin) {
        this.cin = cin;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = Integer.parseInt(telephone);
    }

    public void setNbreEnfant(int nbreEnfant) {
        this.nbreEnfant = nbreEnfant;
    }
     public void setNbreEnfant(String nbreEnfant) {
        this.nbreEnfant =Integer.parseInt(nbreEnfant) ;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getCodepostale() {
        return codepostale;
    }

    public void setCodepostale(int codepostale) {
        this.codepostale = codepostale;
    }
   public void setCodepostale(String codepostale) {
        this.codepostale = Integer.parseInt(codepostale);
    }
   public int getNbreEnfant(int nbrEnfant){
       return nbreEnfant;
   }
   
public Parent getParent(){
    return null;
}
    


  
    
}
