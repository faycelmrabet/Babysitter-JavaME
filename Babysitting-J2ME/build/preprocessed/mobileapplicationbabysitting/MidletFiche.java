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
public class MidletFiche extends MIDlet implements CommandListener, Runnable {
    Display disp =Display.getDisplay(this);
    Form f1 = new Form("Affichage des fiches");
    
    Command cmdParse = new Command("Fiches babysitters", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0); 
    FicheBabysitter []   fichebabysitter;
    List lst = new List("fiches", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos sur les fiches");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();

    public void startApp() {
        f.append("Cliquer sur fiches pour afficher");
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.setCommandListener(this);
        disp.setCurrent(f);
        
        f.append("Cliquer sur fiches pour afficher");
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
            form.append("Informations fiches: \n");
            form.append(showFiche(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }
    }

    public void run() {
        try{
            
            FicheBabysitterHandler ficheBabysitterHandler = new FicheBabysitterHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
             HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/getXmlFiches_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, ficheBabysitterHandler);
            
            fichebabysitter = ficheBabysitterHandler.getFichebabysitter();
            if(fichebabysitter.length>0){
                for (int i = 0; i < fichebabysitter.length; i++) {
                    lst.append(fichebabysitter[i].getIdfiche()+""
                      + fichebabysitter[i].getTypeb()+""
                       +fichebabysitter[i].getNbmissions()+""
                       +fichebabysitter[i].getNbenfants()+""
                        +fichebabysitter[i].getTarif()+""
                         +fichebabysitter[i].getDistance()+""
                          +fichebabysitter[i].getExperience()+""
                            +fichebabysitter[i].getSexe()+""
                            +fichebabysitter[i].getTelephone()+""
                            +fichebabysitter[i].getVille()+""
                            +fichebabysitter[i].getCodepostal()+""
                        +fichebabysitter[i].getPays()+"",null);
                }
            }
            
        }
       catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }
    private String showFiche(int i){
        String res = "";
        if (fichebabysitter.length>0) {
            sb.append("* ");
            sb.append(fichebabysitter[i].getIdfiche());
            sb.append("\n");
            
            sb.append("* ");
            sb.append(fichebabysitter[i].getTypeb());
            sb.append("\n");
            
            sb.append("* ");
            sb.append(fichebabysitter[i].getNbmissions());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getNbenfants());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getTarif());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getDistance());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getExperience());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getSexe());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getTelephone());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getVille());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getCodepostal());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichebabysitter[i].getPays());
            sb.append("\n");
        }
        
         res = sb.toString();
         sb = new StringBuffer("");
         return res;
    }
}
