package mobileapplicationbabysitting;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Faycel
 */
public class ParentHandler extends DefaultHandler{
    private Vector parents;
    String idTag="close";
   /* String usernameTag="close";
    String passwordTag="close";
    String emailTag="close";
    String nomTag="close";
    String prenomTag="close";
    String cinTag="close";
    String nbreEnfant="close";
    String adresseTag="close";
    String telephoneTag="close";
    String codePostaleTag="close";*/
    String nbreEnfantTag="close";
public ParentHandler(){
    parents = new Vector();
}

public Parent[] getParent(){
    Parent[] parentss= new Parent[parents.size()];
    parents.copyInto(parentss);
    return parentss; 
}

private Parent currentparent;
 public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
  if(qName.equals("user")){
     currentparent.setId(attributes.getValue("id"));
    /* currentparent.setUsername(attributes.getValue("username"));
     currentparent.setPassword(attributes.getValue("password"));
     currentparent.setNom(attributes.getValue("nom"));
     currentparent.setPrenom(attributes.getValue("prenom"));
     currentparent.setCin(attributes.getValue("cin"));
     currentparent.setAdresse(attributes.getValue("adresse"));
     currentparent.setCodepostale(attributes.getValue("codePostale"));
     currentparent.setTelephone(attributes.getValue("telephone"));*/
     currentparent.setNbreEnfant(attributes.getValue("nbreEnfant"));
     
  }
  else if(qName.equals("id")){
      idTag="open";
  }
/*  else if(qName.equals("username")){
      usernameTag="open";
  }
  else if(qName.equals("password")){
      passwordTag="open";
  }
  else if(qName.equals("email")){
      emailTag="open";
  }
  else if(qName.equals("nom")){
      nomTag="open";
  }
  else if(qName.equals("prenom")){
      prenomTag="open";
  }*/
  else if(qName.equals("nbreEnfant")){
      nbreEnfantTag="open";
  }/*
  else if(qName.equals("telephone")){
       telephoneTag="open";
  }
  else if(qName.equals("codePostale")){
      codePostaleTag="open";
  }
  else if(qName.equals("cin")){
      cinTag="open";
  }*/
 }
  public void EndElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
 if(qName.equals("user")){
        parents.addElement(currentparent);
        currentparent=null;
    }
    else if (qName.equals("id")) {
        idTag="open";
    }
  /*  else if(qName.equals("username")){
        usernameTag="open";
    }
   
    else if(qName.equals("password")){
        passwordTag="open";
    }
     else if(qName.equals("email")){
        emailTag="open";
    }
    else if(qName.equals("nom")){
        nomTag="open";
    }
  else if(qName.equals("prenom")){
         prenomTag="open";
    }
 */
  else if(qName.equals("nbreEnfant")){
      nbreEnfantTag="open";
  }/*
  else if(qName.equals("telephone")){
       telephoneTag="open";
  }
  else if(qName.equals("codePostale")){
      codePostaleTag="open";
  }
  else if(qName.equals("cin")){
      cinTag="open";
  }*/
  }
  public void characters(char[] ch, int start, int length) throws SAXException {  
  if(currentparent!=null){
      if(idTag.equals("open")){
          String id= new String(ch,start,length).trim();
          currentparent.setId(id);
      }
     /* if (usernameTag.equals("open")) {
          String username= new String(ch,start,length).trim();
          currentparent.setUsername(username);
      }
      else if(passwordTag.equals("open")){
          String password = new String(ch,start,length).trim();
          currentparent.setPassword(password);
      }
      else if ( emailTag.equals("open")) {
          String email = new String(ch,start,length).trim();
          currentparent.setEmail(email);
      }
      else if ( nomTag.equals("open")) {
          String nom = new String(ch,start,length).trim();
          currentparent.setNom(nom);
      }
      else if ( prenomTag.equals("open")) {
          String prenom = new String(ch,start,length).trim();
          currentparent.setPrenom(prenom);
      }*/
      else if ( nbreEnfantTag.equals("open")) {
          String nbreEnfant = new String(ch,start,length).trim();
          currentparent.setNbreEnfant(nbreEnfant);
      }/*
      else if ( telephoneTag.equals("open")) {
          String telephone = new String(ch,start,length).trim();
          currentparent.setTelephone(telephone);
      }
      else if ( codePostaleTag.equals("open")) {
          String codePostale = new String(ch,start,length).trim();
          currentparent.setCodepostale(codePostale);
      }
      else if ( cinTag.equals("open")) {
          String cin = new String(ch,start,length).trim();
          currentparent.setCin(cin);
      }*/
  }
 
 }
  
}