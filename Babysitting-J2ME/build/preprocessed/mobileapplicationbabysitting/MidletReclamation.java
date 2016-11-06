/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplicationbabysitting;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Faycel
 */
public class MidletReclamation extends MIDlet implements CommandListener , Runnable {
      Display disp =Display.getDisplay(this);
      Form f1 = new Form("Affichage des reclamations");
      Command cmdParse = new Command("Reclamations", Command.SCREEN, 0);
      Command cmdBack = new Command("Back", Command.BACK, 0);
      Reclamation [] reclamation; 
      List lst = new List("Réclamations", List.IMPLICIT);
      Form f = new Form("Liste des reclamations");
      Form form = new Form("Infos sur les fiches");
      Form loadingDialog = new Form("Please Wait");
      StringBuffer sb = new StringBuffer();
    public void startApp() {
        f.append("Cliquer sur Reclamation pour afficher");
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.setCommandListener(this);
        disp.setCurrent(f);
        
        
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Reclamation: \n");
            form.append(showReclamation(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }
    }

    public void run() {
       try{
        ReclamationHandler reclamationHandler= new ReclamationHandler();
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/getXmlReclamation_Attributes.php");
        DataInputStream dis = new DataInputStream(hc.openDataInputStream());
        parser.parse(dis, reclamationHandler);
       
        reclamation =reclamationHandler.getReclamation();
        
        if(reclamation.length>0){
            for(int i=0;i<reclamation.length;i++){
                lst.append(reclamation[i].getIdReclamation()+""
                        +reclamation[i].getTitre()+""
                        +reclamation[i].getMessage()+""
                        +reclamation[i].getDateReclamation()
                        ,null);
            }
        }
       
       }
       catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }
     private String showReclamation(int i){
         String res="";
         if(reclamation.length>0){
             
            sb.append("*");
            sb.append(reclamation[i].getIdReclamation());
            sb.append("\n");
             
             
             
            sb.append("*");
            sb.append(reclamation[i].getTitre());
            sb.append("\n");
            
            sb.append("*");
            sb.append(reclamation[i].getMessage());
            sb.append("\n");
            
            sb.append("*");
            sb.append(reclamation[i].getDateReclamation());
            sb.append("\n");
         }
         
        res = sb.toString();
        sb = new StringBuffer("");
         return res;
     }
}
