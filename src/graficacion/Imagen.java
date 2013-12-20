package graficacion;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import javax.swing.ImageIcon;

public class Imagen  implements Instrucciones{

    private Image imagen;    
    private int x,y, width,height;
    private String direccion="";
    
    public Imagen(int x, int y, int width, int height,String direccion, Image imagen ) {
        
//        Image image;
//        image = new ImageIcon("C:/Users/Daniel/Documents/sldr06.jpg").getImage();
////        Image image= new ImageIcon(direccion).getImage();
//        Image image=Toolkit.getDefaultToolkit ().getImage(new File("C:/Users\Daniel/Documents/sldr06.jpg").getPath());        
        this.imagen=imagen;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.direccion=direccion;
    }
    public Image imagen(){
        return imagen;
    }
    public int x(){
        return x;
    }
    public int y(){
        return y;
    }
    public int width(){
        return width;
    }
    public int height(){
        return height;
    }        
    public void escalar(int escalar){
        x*=escalar;
        y*=escalar;
    }
    public void rotar(int n, int m){
        x+=n;
        y+=m;
    }
    public String direccion(){
        return direccion;
    }    
    public String toString(){
        return "Img -> "+x+" , "+y+" [ "+(new Lienzo().ejex()+x)+" , "+(new Lienzo().ejex()+y)+" ]\n";
    }
    public String instruccion(){
        return "imagen "+x+","+y+","+width+","+height+","+direccion+"\n";
    }
}
