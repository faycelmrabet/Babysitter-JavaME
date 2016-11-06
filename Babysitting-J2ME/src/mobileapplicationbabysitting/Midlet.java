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
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * @author Faycel
 */
public class Midlet extends MIDlet implements CommandListener{
    Display disp = Display.getDisplay(this);
    String[] tabRFO ={"Reclamations","Fiches des Babysitters","Statistiques des fiches"};
    //String []tabParent={"Deposer une réclamation","Deposer une offre mission","Envoyer un SMS"};
     Command cmdParseF = new Command("Reclamations", Command.SCREEN, 0);
     Command cmdBackF1= new Command("Back", Command.BACK, 0);
     Command cmdSuppF = new Command("Supprimer", Command.SCREEN, 0);
     
     
   // int update=0;
   // int delete=0;
    int IndexF;
    int Index1F;
    FicheBabysitter []   fichebabysitter;
    Reclamation [] reclamation; 
    Ticker tck = new Ticker("Bienvenue à NOUNOU KIDS");
    Ticker tckParent = new Ticker("Bienvenue à l'espace parent");
    Image[] imgMenuF = new Image[3];
    List lstReclamtionsF=new List("Liste des réclamations", List.IMPLICIT);
    List lstFichesF=new List("Liste des fiches",List.IMPLICIT);
    List MenuAdminF;
    Form afficherFicheG = new Form("Fiches");
    Form afficherReclamtionsF = new Form("Reclamations");
    Form formreclamationsF = new Form ("Infos réclamations");
    Form formfichesF = new Form("infos fiches");
    Form suppFiche=new Form("Fiche supprimée ");
    Form suppReclamation = new Form("Réclamtion supprimée");
    Form formEspaceParentF=new Form("Espace parent");
    
    int idF;
    int id1F;
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb= new StringBuffer();
    int ch;
    
    
   //statistique
List lst = new List("fiches", List.IMPLICIT);
    
    int test ; 
  static int test2Faycel;
  static int test3Faycel ; 
  static int test4 ;
  
  
   HttpConnection httpConnection; //pour faire une connexion
    DataInputStream dataInputStream; //objet pour lire un flux de donnÃ©es
    StringBuffer stringBuffer = new StringBuffer("");
 
    
    public void startApp() {
        try{
            imgMenuF[0]=Image.createImage("/mobileapplicationbabysitting/reclamation.png");
            imgMenuF[1]=Image.createImage("/mobileapplicationbabysitting/fiches.png");  
            imgMenuF[2]=Image.createImage("/mobileapplicationbabysitting/stat.png");
            
             MenuAdminF=new List("Application Baby sitter", List.IMPLICIT, tabRFO, imgMenuF);
             MenuAdminF.setCommandListener(this);
             disp.setCurrent(new SplashScreen(this));
             afficherFicheG.addCommand(cmdBackF1);
             afficherFicheG.addCommand(cmdParseF);
             afficherReclamtionsF.addCommand(cmdBackF1);
             afficherReclamtionsF.addCommand(cmdParseF);
             afficherFicheG.setCommandListener(this);
             afficherReclamtionsF.setCommandListener(this);
             lstFichesF.setCommandListener(this);
             lstReclamtionsF.setCommandListener(this);
             lstFichesF.addCommand(cmdBackF1);
             lstReclamtionsF.addCommand(cmdBackF1);
             formreclamationsF.setCommandListener(this);
             formreclamationsF.addCommand(cmdSuppF);
             formreclamationsF.addCommand(cmdBackF1);
             
             formfichesF.setCommandListener(this);
             formfichesF.addCommand(cmdBackF1);
             formfichesF.addCommand(cmdSuppF);
       suppFiche.setCommandListener(this);
             suppFiche.append("Fiche supprimé avec succés ");
             suppFiche.addCommand(cmdBackF1);
      suppReclamation.setCommandListener(this);
      suppReclamation.addCommand(cmdBackF1);
       suppReclamation.append("Réclamation supprimé avec succés");
             

             
             
             
        
             
             
        }
        catch(Exception ex){
           ex.printStackTrace();
        }
   
    
    
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
  public class SplashScreen extends Canvas implements Runnable{
private Image imgSplash;
private MIDlet midlet;
public SplashScreen( Midlet midlet){
    this.midlet=midlet;
    try{
       imgSplash= Image.createImage("/mobileapplicationbabysitting/splash.jpg");
        Thread t = new Thread(this);
        t.start(); 
    }
    catch(IOException ex){
        ex.printStackTrace();
    }
}
       protected void paint(Graphics g) {
        int w = getWidth();
        int h =getHeight();
        g.setColor(0x000000);
        g.fillRect(0,0, w, h);
        g.drawImage(imgSplash, w / 2, h / 2,
                Graphics.HCENTER | Graphics.VCENTER); 
       }
       public void dismiss(){
           if (isShown())
            Display.getDisplay(midlet).setCurrent(MenuAdminF);
       }
        public void run() {
        try {
            Thread.sleep(3000);//set for 3 seconds
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
        dismiss();
        }

        
      
  }
    public void commandAction(final Command c, Displayable d) {
        if(c==cmdBackF1){
            disp.setCurrent(MenuAdminF);
        }
        if(c==cmdSuppF & d==formfichesF){
            Thread th = new Thread(new Runnable() {

                public void run() {
                    try{
                        id1F=lstFichesF.getSelectedIndex();
                        HttpConnection hc =(HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/supprimer_fiches.php?idfiche="+fichebabysitter[id1F].getIdfiche());
                        DataInputStream dis= new DataInputStream(hc.openDataInputStream());
                        while((ch = dis.read())!=-1){
                            sb.append((char)ch);
                        }
                         if("OK".equals(sb.toString().trim())){
                            disp.setCurrent(suppFiche);
                        }
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            });
            th.start();
        }
    
        if(c==cmdSuppF & d==formreclamationsF){
            Thread th = new Thread(new Runnable() {

                public void run() {
                   try{
                        idF=lstReclamtionsF.getSelectedIndex();
                        HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/supprimer_reclamation.php?idReclamation="+reclamation[idF].getIdReclamation());
                        DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                        }
                        if("OK".equals(sb.toString().trim())){
                            disp.setCurrent(suppReclamation);
                        }
                   }
                   catch(Exception ex){
                       
                   }
                
                }
            });
            th.start();
        }   
        
        if(c==List.SELECT_COMMAND & d==MenuAdminF){
            
           
            if("Reclamations".equals(MenuAdminF.getString(MenuAdminF.getSelectedIndex())))
             {   
           
                 Thread th=new Thread(new Runnable() {
                      
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
                lstReclamtionsF.append("Identifiant: "+reclamation[i].getIdReclamation()+"\n"
                        +"Titre: "+reclamation[i].getTitre()+"\n"
                        +"Message: "+reclamation[i].getMessage()+"\n"
                        +"Date: "+reclamation[i].getDateReclamation()+"\n"
                        ,null);
            }
        }
       
       }
       catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        
        disp.setCurrent(lstReclamtionsF);
                 }
             });
             th.start();
               
             }
           
            if("Statistiques des fiches".equals(MenuAdminF.getString(MenuAdminF.getSelectedIndex()))){
                Thread th=new Thread(new Runnable() {

                    public void run() {
                    
                         String url = "http://127.0.0.1:8888/babysitter/statFicheFaycel_Femme.php";
        try {
            httpConnection = (HttpConnection) Connector.open(url);//Ã©tablir
            dataInputStream = httpConnection.openDataInputStream();//rÃ©cupÃ©rer la rÃ©ponse

            while ((ch=dataInputStream.read())!=-1) {
                      stringBuffer.append((char)ch); //cenversion de ascii en char
            }
           test2Faycel=Integer.parseInt(stringBuffer.toString().trim());
                               

           // System.out.println(test2);
            
            stringBuffer = new StringBuffer("");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String url2="http://127.0.0.1:8888/babysitter/statFicheFaycel_Homme.php";
        try{
              httpConnection = (HttpConnection) Connector.open(url);//Ã©tablir
            dataInputStream = httpConnection.openDataInputStream();//rÃ©cupÃ©rer la rÃ©ponse

            while ((ch=dataInputStream.read())!=-1) {
                      stringBuffer.append((char)ch); //cenversion de ascii en char
            }
           test3Faycel=Integer.parseInt(stringBuffer.toString().trim());
                               

           // System.out.println(test2);
             disp.setCurrent(new StatistiqueFaycel());
            stringBuffer = new StringBuffer("");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
                        
                    }
                });
                th.start();
            }
             if("Fiches des Babysitters".equals(MenuAdminF.getString(MenuAdminF.getSelectedIndex())))
             {Thread th= new Thread(new Runnable() {

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
                    lstFichesF.append("Identifiant:"+fichebabysitter[i].getIdfiche()+"\n"
                      +"Type:"+ fichebabysitter[i].getTypeb()+"\n"
                       +"Mission:"+fichebabysitter[i].getNbmissions()+"\n"
                       +"N.missions:"+fichebabysitter[i].getNbenfants()+"\n"
                        +"Tarif:"+fichebabysitter[i].getTarif()+"\n"
                         +"Distance:"+fichebabysitter[i].getDistance()+"\n"
                          +"Experience:"+fichebabysitter[i].getExperience()+"\n"
                            +"Sexe:"+fichebabysitter[i].getSexe()+"\n"
                            +"Telephone:"+fichebabysitter[i].getTelephone()+"\n"
                            +"Ville:"+fichebabysitter[i].getVille()+"\n"
                            +"Code postal:"+fichebabysitter[i].getCodepostal()+"\n"
                        +"Pays:"+fichebabysitter[i].getPays()+"\n",null);
                }
            }
            
        }
       catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
                       disp.setCurrent(lstFichesF);
                 }
             });
             th.start();}
             
     
       
        }
           if (c == List.SELECT_COMMAND & d==lstReclamtionsF) {
            formreclamationsF.append("Informations réclamations: \n");
            formreclamationsF.append(showReclamation(lstReclamtionsF.getSelectedIndex()));
            formreclamationsF.addCommand(cmdSuppF);
            
            disp.setCurrent(formreclamationsF);
            
        }
            if (c == List.SELECT_COMMAND & d==lstFichesF) {
            formfichesF.append("Informations fiches: \n");
            formfichesF.append(showFiche(lstFichesF.getSelectedIndex()));
            formfichesF.addCommand(cmdSuppF);
            
            disp.setCurrent(formfichesF);
        }
            
              
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
     
     
     //statistique
   
       public class StatistiqueFaycel extends Canvas implements CommandListener{
int h=getHeight();
int w=getWidth();
int rColor[] = {0xff0000, 0xffa500, 0xffff00, 0xadff2f, 0x32cd32,0x0000ff, 0x9400d3, 0xffffff};
int babysitter[] = {test2Faycel,test3Faycel};
String sexeBabysitter[]={"Homme","Femme"};
         
         int x=0,y;
         float z;
        
        
        protected void paint(Graphics g) {
             g.setColor(255 , 255, 255);
        g.fillRect(0, 0, w, h);
        for(int i=0;i<2;i++)
            y+=babysitter[i];
        z=360/y;
        for (int i = 0; i <2/* rColor.length*/; i++) {
            
            g.setColor(rColor[i]);
           
        //    System.out.println(babysitter[0]);
        // System.out.println(Integer.parseInt(sb3.toString()));
            
            g.fillArc(w * 1/7, w*1/7, w* 2/3 , w * 2/3,x ,(int)z*babysitter[i]);
           x+=z*babysitter[i];
           if(i%2==0)
           {
               g.fillRect(1,w*6/7+7*i,10,10);
               g.setColor(0, 0, 0);
               g.drawString(sexeBabysitter[i],15, w*6/7+7*i,Graphics.TOP|Graphics.LEFT);
           }
           else
           {
                g.fillRect(70,w*6/7+7*(i-1),10,10);
                g.setColor(0, 0, 0);
               g.drawString(sexeBabysitter[i],100, w*6/7+7*(i-1),Graphics.TOP|Graphics.LEFT);
           }
        }
        g.drawString("Statistique des fiches", 
                w /10 , h *13/15 , Graphics.TOP|Graphics.LEFT);
        }

        public void commandAction(Command c, Displayable d) {
if(c==cmdBackF1)  {
    disp.setCurrent(MenuAdminF);
}}
        
    }
     
     
}
