package graficacion;

import java.awt.Color;

public class Triangulo implements Instrucciones{
    
    private int x,y,x1,y1,x2,y2;
    private Color color;
    
    public Triangulo(int x, int y, int x1, int y1, int x2, int y2  ){
        this.x=x;
        this.y=y;
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;    
    }
    
    public Triangulo(int x, int y, int x1, int y1, int x2, int y2 , Color color ){
        this.x=x;
        this.y=y;
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.color=color;
    }
    
    public int x(){
        return x;
        
    }
    
    public int y(){
        return y;
    }
    
    public int x1(){
        return x1;
    }
    public int y1(){
        return y1;
    }
    
    public int x2(){
        return x2;
    }
    
    public int y2(){
        return y2;
    }    
    
    public Color color(){
        return color;
    }
    
    public void escalar(int escala){
        x*=escala;
        y*=escala;
        x1*=escala;
        y1*=escala;
        x2*=escala;
        y2*=escala;
    }
    
    public void trasladar(int n, int m){
        x+=n;
        y+=m;
        x1+=n;
        y1+=m;
        x2+=n;
        y2+=m;
    }

    public void rotar(int xR,int yR, int grados){
        int xp,yp,xp1,yp1,yp2,xp2;
        xp=(xR+(x-xR)*(int)(Math.cos(Math.toRadians(grados))))-(y-yR)*(int)(Math.sin(Math.toRadians(grados)));
        yp=(xR+(y-yR)*(int)(Math.cos(Math.toRadians(grados))))+(x-xR)*(int)(Math.sin(Math.toRadians(grados)));
        xp1=(xR+(x1-xR)*(int)(Math.cos(Math.toRadians(grados))))-(y1-yR)*(int)(Math.sin(Math.toRadians(grados)));
        yp1=(xR+(y1-yR)*(int)(Math.cos(Math.toRadians(grados))))+(x1-xR)*(int)(Math.sin(Math.toRadians(grados)));
        xp2=(xR+(x2-xR)*(int)(Math.cos(Math.toRadians(grados))))-(y2-yR)*(int)(Math.sin(Math.toRadians(grados)));
        yp2=(xR+(y2-yR)*(int)(Math.cos(Math.toRadians(grados))))+(x2-xR)*(int)(Math.sin(Math.toRadians(grados)));        
        x=xp;                                                                
        y=yp;
        x1=xp1;
        y1=xp1;
        x2=xp2;
        y2=yp2;
    }
    
    public String toString(){
       return "Tri -> " +x+" "+y+" "+x1+" "+y1 +" "+"  -> "+x1+" "+y1 + " "+x2+" "+y2+"  ->"+" "+x2+" "+y2+" "+x+" "+y;
   }
    public String instruccion(){
        return "triangulo "+x+","+y+","+x1+","+y1+","+x2+","+y2;
    }
}