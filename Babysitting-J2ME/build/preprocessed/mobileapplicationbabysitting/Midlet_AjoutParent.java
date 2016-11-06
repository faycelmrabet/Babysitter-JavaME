/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplicationbabysitting;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;

/**
 * @author Faycel
 */
public class Midlet_AjoutParent extends MIDlet implements CommandListener{
   Display disp= Display.getDisplay(this);
    Form f1Faycel = new Form("Inscription Parent");
    TextField tfusername = new TextField("Username", null, 100, TextField.ANY);
    TextField tfemail = new TextField("Email", null, 100, TextField.ANY);
    TextField tfpassword = new TextField("Password",null,100,TextField.ANY);
    
   
    TextField tfnom = new TextField("Nom", null, 100, TextField.ANY);
    TextField tfprenom = new TextField("Prenom", null, 100, TextField.ANY);
    TextField tfcin= new TextField("CIN", null, 100, TextField.NUMERIC);
    TextField tfadresse = new TextField("Adresse", null, 100, TextField.ANY);
    TextField tfcodePostale = new TextField("Code postal", null, 100, TextField.NUMERIC);
    TextField tftelephone = new TextField("Telephone", null, 100, TextField.NUMERIC);
    TextField tfnbreEnfant = new TextField("Nombre d'enfant", null, 100, TextField.NUMERIC);
    
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);
    Form f2Faycel = new Form("Inscription terminée avec suucés");
    Form f3Faycel = new Form("Erreur");
Ticker tickParent= new Ticker("Bienvenue à NOUNOUKIDS");
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1:8888/babysitter/ajout_parent.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    public void startApp() {
        f1Faycel.append("Inscription parent");
        f1Faycel.append(tfusername);
        f1Faycel.append(tfpassword);
        f1Faycel.append(tfnom);
        f1Faycel.append(tfprenom);
        f1Faycel.append(tfemail);
        f1Faycel.append(tfcin);
        f1Faycel.append(tftelephone);
        f1Faycel.append(tfcodePostale);
        f1Faycel.append(tfadresse);
        f1Faycel.append(tfnbreEnfant);
        f1Faycel.addCommand(cmdValider);
        f1Faycel.setCommandListener(this);
        f1Faycel.addCommand(cmdBack);
        f1Faycel.setCommandListener(this);
       f1Faycel.setTicker(tickParent);
        disp.setCurrent(f1Faycel);
        
        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
     if (c == cmdValider) {
            Thread th = new Thread(new Runnable() {

                public void run() {
                         try{
           hc = (HttpConnection) Connector.open(url+"?username="+tfusername.getString().trim()+
                   "&password="+tfpassword.getString().trim()
                  +"&nom="+tfnom.getString().trim()
                   +"&prenom="+tfprenom.getString().trim()
                   +"&email="+tfemail.getString().trim()
                   +"&cin="+tfcin.getString().trim()
                   +"&adresse="+tfadresse.getString()
                   +"&codePostale="+tfcodePostale.getString().trim()
                   +"&telephone="+tftelephone.getString().trim()
                   +"&nbreEnfant="+tfnbreEnfant.getString().trim()
           );
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2Faycel);
                }else{
                    disp.setCurrent(f3Faycel);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                }
            });
            th.start();
        }
        if (c == cmdBack) {
            
            disp.setCurrent(f1Faycel);
        }
    }

}
