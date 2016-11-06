/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplicationbabysitting;

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
public class Midlet_Babysitter extends MIDlet implements CommandListener,Runnable{

    Display disp = Display.getDisplay(this);
    Command cmdParse = new Command("Babysitters", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
     Babysitter [] babysitters;
    List lst = new List("Babysitters", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos babysitter");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    public void startApp() {
        f.append("Click BABYSITTER to get your personnes_list");
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.setCommandListener(this);
        disp.setCurrent(f);
        
        f.append("Click BABYSITTER to get your personnes_list");
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
            form.append("Informations Personne: \n");
            form.append(ShowBabysitter(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }
    }

    public void run() {
        try{
            BabysitterHandler babysitterHandler = new BabysitterHandler();
         SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/getXmlBabysitters_Attributes.php");
            babysitters =babysitterHandler.getBabysitter();
            if(babysitters.length>0){
              for(int i=0;i<babysitters.length;i++){
                  lst.append(babysitters[i].getId()+
                          ""+babysitters[i].getNom()+
                          ""+babysitters[i].getPrenom()+
                          ""+babysitters[i].getEmail()+
                          ""+babysitters[i].getAdresse()+
                          ""+babysitters[i].getCodePostale()+
                          ""+babysitters[i].getTelephone()+
                          ""+babysitters[i].getNote(), null);
              }   
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        disp.setCurrent(lst);
    }
    private String ShowBabysitter(int i ){
        String res="";
        if (babysitters.length > 0) {
            sb.append("* ");
            sb.append(babysitters[i].getId());
            sb.append("\n");
            sb.append("* ");
            sb.append(babysitters[i].getNom());
            sb.append("\n");
            sb.append("* ");
            sb.append(babysitters[i].getPrenom());
            sb.append("\n");
            sb.append("* ");
            sb.append(babysitters[i].getEmail());
            sb.append("\n");
            sb.append("* ");
            sb.append(babysitters[i].getAdresse());
            sb.append("\n");
            sb.append("* ");
            sb.append(babysitters[i].getCodePostale());
            sb.append("\n");
            sb.append("* ");
            sb.append(babysitters[i].getTelephone());
            sb.append("\n");
            sb.append("* ");
            sb.append(babysitters[i].getNote());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        
        return res;
    }
}
