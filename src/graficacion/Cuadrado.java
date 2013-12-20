package graficacion;

import java.awt.Color;

public class Cuadrado implements Instrucciones{
    
    private int x1, y1,x2,y2;
    private Color color;
    
    public Cuadrado(int x1, int x2, int x3, int x4) {
        this.x1=x1;
        this.y1=x2;
        this.x2=x3;
        this.y2=x4;        
    }
    
    public Cuadrado(int x1, int x2, int x3, int x4, Color color) {
        this.x1=x1;
        this.y1=x2;
        this.x2=x3;
        this.y2=x4;
        this.color=color;
    }
    
    public void escalar(int escalar){
        x1*=escalar;
        y1*=escalar;
        x2*=escalar;
        y2*=escalar;
    }
        
    public void trasladar(int n, int m){
        x1+=n;
        y1+=m;
        x2+=n;
        y2+=m;
    }
    
    public void rotar(int xR,int yR,int grados){
        int xp1,yp1,xp2,yp2,xp3,yp3;
        xp1=(xR+(x1-xR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))-(y1-yR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        yp1=(yR+(y1-yR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))+(x1-xR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        xp2=(xR+(x2-xR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))-(y2-yR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        yp2=(yR+(y2-yR)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))+(x2-xR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        x1=xp1;
        y1=yp1;
        x2=xp2;
        y2=yp2;
    }
    
    public int x1(){
        return x1;
    }
    
    public int x2(){
        return y1;
    }
    
    public int x3(){
        return x2;
    }
    
    public int x4(){
        return y2;
    }
    
   public void  color(Color color){
       this.color=color;
   }
   public Color  color(){
       return color;
   }
    public String toString(){
        return "Cua -> " +x1+"  "+y1+" "+x2+" "+y1+" [ "+(new Lienzo().ejex()+x1)+" , "+(new Lienzo().ejey()-y1)+" , "+(new Lienzo().ejex()+x2)+" , "+(new Lienzo().ejey()-y1)+" ]"+
                "\n  -> "+x2+" "+y1+" "+x2+" "+y2+" [ "+(new Lienzo().ejex()+x2)+" , "+(new Lienzo().ejey()-y1)+" , "+(new Lienzo().ejex()+x2)+" , "+(new Lienzo().ejey()-y2)+" ]"+
                "\n  -> "+x2+" "+y2+" "+x1+" "+y2+" [ "+(new Lienzo().ejex()+x2)+" , "+(new Lienzo().ejey()-y2)+" , "+(new Lienzo().ejex()+x1)+" , "+(new Lienzo().ejey()-y2)+" ]"+
                "\n  -> "+x1+" "+y2+" "+x1+" "+y1+" [ "+(new Lienzo().ejex()+x1)+" , "+(new Lienzo().ejey()-y2)+" , "+(new Lienzo().ejex()+x1)+" , "+(new Lienzo().ejey()-y1)+" ]";
    }
    
    public String instruccion(){
        return "cuadrado "+x1+","+y1+","+x2+","+y2;
    }
    
}