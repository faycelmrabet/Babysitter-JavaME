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
public class Midlet_chercherReclamation extends MIDlet implements CommandListener,Runnable {
     Display disp = Display.getDisplay(this);
    String tfrechercheF;

    TextField titreReclamationFaycel = new TextField("titre", null, 100, TextField.ANY);

    Command cmdParseFaycelReclamation = new Command("Rechercher", Command.SCREEN, 0);
    Command cmdBackF11BackFaycel = new Command("Back", Command.BACK, 0);
    Reclamation[] reclamation;
    List lstReclamationFaycel = new List("Rechercher", List.IMPLICIT);
    Form fReclamationFaycel = new Form("Rechercher Reclamation");
    Form formReclamationFaycel = new Form("Infos Reclamation");
    Form loadingDialogFaycelReclamation = new Form("Please Wait");
    StringBuffer sbRF = new StringBuffer();
    public void startApp() {
    fReclamationFaycel.append(titreReclamationFaycel);
        fReclamationFaycel.append("Entrer Le titre la reclamation");
        fReclamationFaycel.addCommand(cmdParseFaycelReclamation);
        fReclamationFaycel.setCommandListener(this);
        lstReclamationFaycel.setCommandListener(this);
        formReclamationFaycel.addCommand(cmdBackF11BackFaycel);
        formReclamationFaycel.setCommandListener(this);
         disp.setCurrent(fReclamationFaycel);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
    if (c == cmdParseFaycelReclamation) {
           disp.setCurrent(loadingDialogFaycelReclamation);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            formReclamationFaycel.append("Informations Reclamation: \n");
            formReclamationFaycel.append(showReclamation(lstReclamationFaycel.getSelectedIndex()));
            disp.setCurrent(formReclamationFaycel);
        }

        if (c == cmdBackF11BackFaycel) {
            formReclamationFaycel.deleteAll();
            disp.setCurrent(lstReclamationFaycel);
        }
    }

    public void run() {
        try{
            ReclamationHandler reclamationHandler = new ReclamationHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/getXmlReclamation_AttributesRecherche.php"+"?tfrecherche="+titreReclamationFaycel.getString());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, reclamationHandler);
            // display the result
            reclamation = reclamationHandler.getReclamation(); 
            if(reclamation.length>0){
            for(int i=0;i<reclamation.length;i++){
                lstReclamationFaycel.append(" "+reclamation[i].getIdReclamation()+""+"\n"
                        +"Le titre de la reclamation est : "+reclamation[i].getTitre()+""+"\n"
                        +"Le message : "+reclamation[i].getMessage()+"\n"
                        +"Date:"+ reclamation[i].getDateReclamation()
                        ,null);
            }
            disp.setCurrent(lstReclamationFaycel);
        }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    private String showReclamation(int i){
         String res="";
         if(reclamation.length>0){
             
            sbRF.append("*");
            sbRF.append(reclamation[i].getIdReclamation());
            sbRF.append("\n");
             
             
             
            sbRF.append("*");
            sbRF.append(reclamation[i].getTitre());
            sbRF.append("\n");
            
            sbRF.append("*");
            sbRF.append(reclamation[i].getMessage());
            sbRF.append("\n");
            
            sbRF.append("*");
            sbRF.append(reclamation[i].getDateReclamation());
            sbRF.append("\n");
         }
         
        res = sbRF.toString();
        sbRF = new StringBuffer("");
         return res;
     }
}
