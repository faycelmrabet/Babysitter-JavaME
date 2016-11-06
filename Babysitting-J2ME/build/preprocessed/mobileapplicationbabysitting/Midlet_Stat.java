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
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;


/**
 * @author Faycel
 */
public class Midlet_Stat extends MIDlet implements CommandListener,Runnable{
   Display disp = Display.getDisplay(this);
    FicheBabysitter [] fichebabysitter;
     List lst = new List("fiches", List.IMPLICIT);
    StringBuffer sb;
    int ch;
    int test ; 
  static int test2Faycel;
  static int test3Faycel ; 
  static int test4 ;
  
  
   HttpConnection httpConnection; //pour faire une connexion
    DataInputStream dataInputStream; //objet pour lire un flux de donnÃ©es
    StringBuffer stringBuffer = new StringBuffer(""); //mÃ©moire pour sauvegarder le flux
  
  
    public void startApp() {
         Thread th = new Thread(this);
            th.start();
    
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
    }

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
    public class StatistiqueFaycel extends Canvas{
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
        g.drawString("Statistique des fiches selon le sexe", 
                w /6 , h *13/15 , Graphics.TOP|Graphics.LEFT);
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
}
