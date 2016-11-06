/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplicationbabysitting;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Faycel
 */
public class BabysitterHandler extends DefaultHandler{
    
    private Vector babysitters;
      String idTag="close";
      String nomTag="close";
      String prenomTag="close";
      String emailTag="close";
      String adresseTag="close";
      String codePostaleTag="close";
      String telephoneTag="close";
      String noteTag="close";
      
      public BabysitterHandler(){
          babysitters = new Vector();
      }
     public Babysitter[] getBabysitter(){
      Babysitter[] babysitterss= new Babysitter[babysitters.size()];
      babysitters.copyInto(babysitterss);
         return babysitterss;
     }
     
     private Babysitter currentBabysitter;
         public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
             if (qName.equals("babysitter")){
            currentBabysitter = new Babysitter();
             currentBabysitter.setId(attributes.getValue("id"));
             currentBabysitter.setNom(attributes.getValue("nom"));
             currentBabysitter.setPrenom(attributes.getValue("prenom"));
              currentBabysitter.setEmail(attributes.getValue("email"));
             currentBabysitter.setAdresse(attributes.getValue("adresse"));
             currentBabysitter.setCodePostale(attributes.getValue("codePostale"));
             currentBabysitter.setTelephone(attributes.getValue("telephone"));
             currentBabysitter.setNote(attributes.getValue("note"));
             }
             else if(qName.equals("id")){
                 idTag="open";
             }
             else if(qName.equals("nom")){
                 nomTag="open";
             }
             
             else if(qName.equals("prenom")){
                 prenomTag="open";
             }
             else if(qName.equals("email")){
                 emailTag="open";
             }
             else if(qName.equals("adresse")){
                 adresseTag="open";
             }
             else if(qName.equals("codePostale")){
                 adresseTag="open";
             }
             else if(qName.equals("telephone")){
                 telephoneTag="open";
             }
             else if(qName.equals("note")){
                noteTag="open";
             }
         
         }
          public void endElement(String uri, String localName, String qName) throws SAXException {
             
          
          if (qName.equals("babysitter")){
            babysitters.addElement(currentBabysitter);
            currentBabysitter=null;
             }
             else if(qName.equals("id")){
                 idTag="close";
             }
             else if(qName.equals("nom")){
                 nomTag="close";
             }
             
             else if(qName.equals("prenom")){ 
                 prenomTag="close";
             }
             else if(qName.equals("email")){
                 emailTag="close";
             }
             else if(qName.equals("adresse")){
                 adresseTag="close";
             }
             else if(qName.equals("codePostale")){
                 adresseTag="close";
             }
             else if(qName.equals("telephone")){
                 telephoneTag="close";
             }
             else if(qName.equals("note")){
                noteTag="close";
             }
          
          }
          public void Characters(char[] ch, int start, int length) throws SAXException{
              if(currentBabysitter == null){
                  if(idTag.equals("open")){
                      String id = new String(ch, start, length).trim();
                      currentBabysitter.setId(id);
                  }else 
                  if(nomTag.equals("open")){
                      String nom= new String(ch, start, length).trim();
                      currentBabysitter.setNom(nom);
                  }else
                  if(prenomTag.equals("open")){
                      String prenom = new String(ch, start, length).trim();
                      currentBabysitter.setPrenom(prenom);
                  }else 
                      if(emailTag.equals("open")){
                      String email = new String(ch, start, length).trim();
                      currentBabysitter.setEmail(email);
                  }else 
                        if(adresseTag.equals("open")){
                      String adresse = new String(ch, start, length).trim();
                      currentBabysitter.setAdresse(adresse);
                  }else 
                            if(codePostaleTag.equals("open")){
                      String codePostale = new String(ch, start, length).trim();
                      currentBabysitter.setCodePostale(codePostale);
                  }else 
                                if(telephoneTag.equals("open")){
                      String telephone = new String(ch, start, length).trim();
                      currentBabysitter.setTelephone(telephone);
                  }else 
                  if(noteTag.equals("open")){
                      String note = new String(ch, start, length).trim();
                      currentBabysitter.setNote(note);
                  }
                  
                  
                      
              }
          }
          

}
         
         
         
         

