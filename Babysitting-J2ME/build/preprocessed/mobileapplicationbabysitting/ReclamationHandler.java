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
public class ReclamationHandler extends DefaultHandler {
    private Vector reclamations;
    
    String idReclamationTag="close"; 
    String titreTag="close";
    String messageTag="close";
    String dateReclamationTag="close";
  
    public ReclamationHandler(){
        reclamations = new Vector();
    }
    public Reclamation[] getReclamation(){
        Reclamation[] reclamationss = new Reclamation[reclamations.size()];
        reclamations.copyInto(reclamationss);
        return reclamationss;
    }
    private Reclamation currentReclamation;
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if(qName.equals("reclamation")){
    currentReclamation= new Reclamation();
    currentReclamation.setIdReclamation(attributes.getValue("idReclamation"));
    currentReclamation.setTitre(attributes.getValue("titre"));
    currentReclamation.setMessage(attributes.getValue("message"));
    currentReclamation.setDateReclamation("dateReclamation");
    }
    else if (qName.equals("idReclamation")) {
            idReclamationTag="open";
        }
    else if (qName.equals("titre")) {
            titreTag="open";
        }
    else if (qName.equals("message")) {
            messageTag="open";
        }
    else if (qName.equals("dateReclamation")) {
            dateReclamationTag="open";
        }
    }

public void endElement(String uri, String localName, String qName) throws SAXException {
    if(qName.equals("reclamation")){
        reclamations.addElement(currentReclamation);
        currentReclamation=null;
    }
    else if (qName.equals("idReclamation")) {
        idReclamationTag="close";
    }
    else if(qName.equals("titre")){
        titreTag="close";
    }
    else if(qName.equals("message")){
        messageTag="close";
    }
    else if(qName.equals("dateReclamation")){
        dateReclamationTag="close";
    }
}
 public void characters(char[] ch, int start, int length) throws SAXException {  
  if(currentReclamation!=null){
      if(idReclamationTag.equals("open")){
          String idReclamation = new String(ch,start,length).trim();
          currentReclamation.setIdReclamation(idReclamation);
      }
      if (titreTag.equals("open")) {
          String titre= new String(ch,start,length).trim();
          currentReclamation.setTitre(titre);
      }
      else if(messageTag.equals("open")){
          String message = new String(ch,start,length).trim();
          currentReclamation.setMessage(message);
      }
      else if (dateReclamationTag.equals("open")) {
          String dateReclamation = new String(ch,start,length).trim();
          currentReclamation.setDateReclamation(dateReclamation);
      }
  }
 
 }
}
