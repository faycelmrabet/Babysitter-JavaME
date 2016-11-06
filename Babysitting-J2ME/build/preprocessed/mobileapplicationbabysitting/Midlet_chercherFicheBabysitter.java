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
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Faycel
 */
public class Midlet_chercherFicheBabysitter extends MIDlet implements CommandListener,Runnable{
    Display disp = Display.getDisplay(this);
    Command cmdParseFicheFaycel = new Command("Rechercher", Command.SCREEN, 0);
    Command cmdBackFicheFaycel = new Command("Back", Command.BACK, 0);
    FicheBabysitter[] fichesBabysitters;
    List lstFicheFaycel = new List("Rechercher", List.IMPLICIT);
    Form fFicheFaycel = new Form("Rechercher fiche");
    Form formFichesFaycel = new Form("Infos fiches");
    Form loadingDialogFicheFaycel = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();    
    String tfrechercheFicheFaycel;
    TextField paysFaycel = new TextField("Pays", null, 100, TextField.ANY);
    public void startApp() {
    fFicheFaycel.append(paysFaycel);
        fFicheFaycel.append("Entrer Le pays : ");
        fFicheFaycel.addCommand(cmdParseFicheFaycel);
        fFicheFaycel.setCommandListener(this);
        lstFicheFaycel.setCommandListener(this);
        formFichesFaycel.addCommand(cmdBackFicheFaycel);
        formFichesFaycel.setCommandListener(this);
         disp.setCurrent(fFicheFaycel);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
    if (c == cmdParseFicheFaycel) {
            disp.setCurrent(loadingDialogFicheFaycel);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            formFichesFaycel.append("Informations fiche: \n");
            formFichesFaycel.append(showFiche(lstFicheFaycel.getSelectedIndex()));
            disp.setCurrent(formFichesFaycel);
        }

        if (c == cmdBackFicheFaycel) {
            formFichesFaycel.deleteAll();
            disp.setCurrent(lstFicheFaycel);
        }
    }

    public void run() {
        try{
            FicheBabysitterHandler ficheBabysitterHandler = new FicheBabysitterHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/getXmlFiches_AttributesRechercher.php"+"?tfrecherche="+paysFaycel.getString());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, ficheBabysitterHandler);
            fichesBabysitters =ficheBabysitterHandler.getFichebabysitter();
             if(fichesBabysitters.length>0){
                for (int i = 0; i < fichesBabysitters.length; i++) {
                    lstFicheFaycel.append(fichesBabysitters[i].getIdfiche()+""
                      + fichesBabysitters[i].getTypeb()+""
                       +fichesBabysitters[i].getNbmissions()+""
                       +fichesBabysitters[i].getNbenfants()+""
                        +fichesBabysitters[i].getTarif()+""
                         +fichesBabysitters[i].getDistance()+""
                          +fichesBabysitters[i].getExperience()+""
                            +fichesBabysitters[i].getSexe()+""
                            +fichesBabysitters[i].getTelephone()+""
                            +fichesBabysitters[i].getVille()+""
                            +fichesBabysitters[i].getCodepostal()+""
                        +fichesBabysitters[i].getPays()+"",null);
                }
                disp.setCurrent(lstFicheFaycel);
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private String showFiche(int i){
        String res = "";
        if (fichesBabysitters.length>0) {
            sb.append("* ");
            sb.append(fichesBabysitters[i].getIdfiche());
            sb.append("\n");
            
            sb.append("* ");
            sb.append(fichesBabysitters[i].getTypeb());
            sb.append("\n");
            
            sb.append("* ");
            sb.append(fichesBabysitters[i].getNbmissions());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getNbenfants());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getTarif());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getDistance());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getExperience());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getSexe());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getTelephone());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getVille());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getCodepostal());
            sb.append("\n");
            
            sb.append("*");
            sb.append(fichesBabysitters[i].getPays());
            sb.append("\n");
        }
        
         res = sb.toString();
         sb = new StringBuffer("");
         return res;
    }
}
