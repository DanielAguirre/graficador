package graficacion;

import java.awt.Color;

public class Linea implements Instrucciones{

    private int x, y, x1, y1;
    private Color color;
    
    public Linea(int n1, int n2, int n3,int n4){
        x=n1;
        y=n2;
        x1=n3;
        y1=n4;
        
    }
    
    public Linea(int n1, int n2, int n3,int n4, Color color){
        x=n1;
        y=n2;
        x1=n3;
        y1=n4;
        this.color=color;
    }
    
    public void escalar(int escala){
        x*=escala;
        y*=escala;
        x1*=escala;
        y1*=escala;
    }
    
    public void trasladar(int n, int m){
        x+=n;
        y+=m;
        x1+=n;
        y1+=m;
    }

    public void rotar(int xR, int yR,int grados){
        int xp,yp,x1p,y1p;        
        xp=(xR+(x-xR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))-(y-yR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        yp=(xR+(y-yR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))+(x-xR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        x1p=(xR+(x1-xR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))-(y1-yR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        y1p=(yR+(y1-yR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))+(x1-xR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        x=xp;
        y=yp;
        x1=x1p;
        y1=y1p;        
    }
        
    public int punto1x(){
        return x;
    }
    
    public int punto1y(){
        return y;
    }
    
    public int punto2x(){
        return x1;
    }
    
    public int punto2y(){
        return y1;
    }
    
    public Color color(){
        return color;
    }
    
    @Override
    public String toString(){
        return "Ln ->" +x+"  "+y+","+x1+","+y1+" [ "+(new Lienzo().ejex()+x)+" , "+(new Lienzo().ejey()-y)+" , "+(new Lienzo().ejex()+x1)+" , "+(new Lienzo().ejey()-y1)+" ]";
    }
    
    public String instruccion(){
        return "linea "+x+","+y+","+x1+","+y1;
    }
        
}