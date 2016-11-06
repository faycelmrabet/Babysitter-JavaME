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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * @author Faycel
 */
public class PieChartMIDlet extends MIDlet implements CommandListener,Runnable{

    Display disp = Display.getDisplay(this);
    Reclamation [] reclamations;
     List lst = new List("fiches", List.IMPLICIT);
    StringBuffer sb;
    Statistique stat=new Statistique();
    
    public void startApp() {
               Thread th = new Thread(this);
            th.start();
   
 
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    public class Statistique extends Canvas {
      
        int w=getWidth();
        int h=getHeight();
         int rColor[] = {0xff0000, 0xffa500, 0xffff00, 0xadff2f, 0x32cd32,
0x0000ff, 0x9400d3, 0xffffff};
         int x=0,y;
         float z;
        
        
        protected void paint(Graphics g) {
            g.setColor(255 , 255, 255);
            g.fillRect(0, 0, w, h);
            System.out.println(reclamations.length);
            for(int i=0;i<4;i++)
            y+=reclamations[i].getIdReclamation();
            z=360/y+1;
            
             for(int i = 0; i <4; i++){
                   g.setColor(rColor[i]);
                    
                    System.out.println(reclamations.length);
            System.out.println(reclamations[0].getIdReclamation());
            g.fillArc(0, 0, w, w,x ,(int)z*reclamations[i].getIdReclamation());
           x+=z*reclamations[i].getIdReclamation();
           if(i % 2 == 0)   
           {
               g.fillRect(1,w+10*i,10,10);
               g.setColor(0, 0, 0);
               g.drawString(reclamations[i].getTitre(),15, w+10*i,Graphics.TOP|Graphics.LEFT);
           }
           else
           {
                g.fillRect(100,w+10*(i-1),10,10);
                g.setColor(0, 0, 0);
               g.drawString(reclamations[i].getTitre(),115, w+10*(i-1),Graphics.TOP|Graphics.LEFT);
           }
             }
            
        }
        
    }
    public void commandAction(Command c, Displayable d) {
        
    }

    public void run() {
     try{
        ReclamationHandler reclamationHandler= new ReclamationHandler();
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1:8888/babysitter/getXmlReclamation_Attributes.php");
        DataInputStream dis = new DataInputStream(hc.openDataInputStream());
        parser.parse(dis, reclamationHandler);
       
        reclamations =reclamationHandler.getReclamation();
        
        if(reclamations.length>0){
            for(int i=0;i<reclamations.length;i++){
                lst.append(reclamations[i].getIdReclamation()+""
                        +reclamations[i].getTitre()+""
                        +reclamations[i].getMessage()+""
                        +reclamations[i].getDateReclamation()
                        ,null);
            }
        }
       
       }
       catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(stat);   
         
         
        
    }
    private String showReclamation(int i){
         String res="";
         if(reclamations.length>0){
             
            sb.append("*");
            sb.append(reclamations[i].getIdReclamation());
            sb.append("\n");
             
             
             
            sb.append("*");
            sb.append(reclamations[i].getTitre());
            sb.append("\n");
            
            sb.append("*");
            sb.append(reclamations[i].getMessage());
            sb.append("\n");
            
            sb.append("*");
            sb.append(reclamations[i].getDateReclamation());
            sb.append("\n");
         }
         
        res = sb.toString();
        sb = new StringBuffer("");
         return res;
     }
  
}
 
