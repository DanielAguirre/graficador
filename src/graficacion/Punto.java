package graficacion;

import java.awt.Color;

class Punto implements Instrucciones{
    
    private int x,y;
    private Color color;
    
    public Punto(int n1, int n2) {
        x=n1;
        y=n2;       
    }

    public Punto(int x, int y, Color color) {
        this.x=x;
        this.y=y;
        this.color=color;
    }
    
    public int centrox(){
        return x;
    }
    
    public int centroy(){
        return y;
    }
        
    public void escalar(int escala){
        x*=escala;
        y*=escala;
    }
    
    public void trasladar(int n, int m){
        x+=n;
        y+=m;
    }

    public Color color(){
        return color;
    }
    public void rotar(int xR ,int yR,int grados){
        int xp,yp;
        xp=(xR+(x-xR)*(int)Math.rint(Math.cos(Math.toRadians(grados))))-(y-yR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        yp=(xR+(y-yR)*(int)Math.rint(Math.cos(Math.toRadians(grados))))+(x-xR)*(int)(Math.rint(Math.sin(Math.toRadians(grados))));
        x=xp;
        y=yp;
    }
        
    public String toString(){
        return "PT ->" +x+"  "+y+ " ["+(new Lienzo().ejex()+x)+" , "+(new Lienzo().ejey()-y)+"]";
    }
    
    public String instruccion(){
        return "punto "+x+","+y;
    }
}