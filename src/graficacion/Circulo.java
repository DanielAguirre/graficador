package graficacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Circulo  implements Instrucciones{
    
    private int radio,x,y;
    private Color color;
    
    public Circulo( int x, int y, int radio) throws IOException{
        this.radio=radio;
        this.x=x;
        this.y=y;            
    }

    public Circulo(int x, int y, int radio, Color color) {
        this.radio=radio;
        this.x=x;
        this.y=y;
        this.color=color;
    }
            
    public void escalar(int n){
        x*=n;
        y*=n;
        radio*=n;
    }    
    
    public void trasladar(int n, int m){
        x+=n;
        y+=m;
//        radio*=n;
    }    
    
    public void rotar(int xR,int yR,int grados){
        int xp,yp;
        xp=(xR+(x-xR)*(int)(Math.cos(Math.toRadians(grados))))-(y-yR)*(int)(Math.sin(Math.toRadians(grados)));
        yp=(xR+(y-yR)*(int)(Math.cos(Math.toRadians(grados))))+(x-xR)*(int)(Math.sin(Math.toRadians(grados)));
        x=xp;
        y=yp;
    }
    
    public int x(){     
        return x;
    }
    
    public int y(){        
        return y;
    }
    
    public int radio(){
        return radio;
    }
    
    public Color color(){
        return color;
    }
    
    @Override
    public String toString(){
        return "Cir -> "+x+"  "+y+"  "+radio+" [ "+(new Lienzo().ejex()+x)+" , "+(new Lienzo().ejey()-y)+" , "+radio+" ]";
    }
    
    public String instruccion(){
        return "circulo "+ x+","+y+","+","+radio;
    }
    
}