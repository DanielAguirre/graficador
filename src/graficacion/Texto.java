package graficacion;

import java.awt.Color;

public class Texto implements Instrucciones{
    
    private String texto="";
    private int x,y;
    private Color color;
    
    public Texto(String texto, int x, int y){
        this.texto=texto;
        this.x=x;
        this.y=y;
    }
    
    public Texto(String texto, int x, int y, Color color){
        this.texto=texto;
        this.x=x;
        this.y=y;
        this.color=color;
    }
    
    public String Texto(){
        return texto;
    }
    
    public int x(){
        return x;
    }
    
    public int y(){
        return y;
    }
    
    public void escalar(int escalar){
        x*=escalar;
        y*=escalar;
    }
    
    public void trasladar(int n ,int m){
        x+=n;
        y+=m;
    }
    
    public void rotar(int xr, int yr, int grados){
        int xp,yp;
        xp=(xr+(x-xr)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))-(y-y)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        yp=(xr+(y-yr)*(int)(Math.rint(Math.cos(Math.toRadians(grados)))))+(xr-xr)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        x=xp;
        y=xp;        
    }
    
    public Color color(){
        return color;
    }
    
    public String toString(){
        return "Text ->"+x+" "+y+" [ "+(new Lienzo().ejex()+x)+" , "+(new Lienzo().ejey()-y)+" ]";
    }
    
    public String instruccion(){
        return "texto "+x+","+y+","+texto;
    }
}