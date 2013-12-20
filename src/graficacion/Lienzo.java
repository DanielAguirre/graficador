package graficacion;

import java.awt.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Lienzo  extends Canvas{    
        
    private int instrucciones, limite=0;
    private Color color;
    private String Arbol="", ArbolV="", ArbolNV="", historial="";
    private Vector <Instrucciones> dibujos = new Vector();
    private Vector<Integer> seleccionados;
    private int menorx=-489, mayorx=489, menory=-230,mayory=230;
    private double escalax=1, escalay=1;
    private int ejex=477,ejey=230;
    
    public void paint(Graphics g){
        //instrucciones=0;
        g.drawLine(ejex, 0, ejex, 428);
        g.drawLine(0, ejey, 945, ejey); 
        g.translate(ejex, ejey);
        
        for(int i=0;i<dibujos.size()-limite;i++){            
                if (dibujos.elementAt(i) instanceof Circulo)
                    draw_Circulo((Circulo)dibujos.elementAt(i), g); 
                if(dibujos.elementAt(i) instanceof Punto)
                    draw_Point((Punto)dibujos.elementAt(i), g);
                if(dibujos.elementAt(i) instanceof Linea)
                    draw_Line((Linea)dibujos.elementAt(i), g);
                if(dibujos.elementAt(i) instanceof Cuadrado)
                    draw_Cuadrado((Cuadrado)dibujos.elementAt(i), g);
                if(dibujos.elementAt(i) instanceof Triangulo)
                    draw_Triangulo((Triangulo)dibujos.elementAt(i), g);
                if(dibujos.elementAt(i) instanceof Texto)
                    draw_Text((Texto)dibujos.elementAt(i), g);
                if(dibujos.elementAt(i) instanceof Imagen)
                    draw_image((Imagen)dibujos.elementAt(i), g);                              
                if( dibujos.elementAt(i) instanceof Koch||dibujos.elementAt(i) instanceof Sierpinski)
                    fractal(dibujos.elementAt(i),g);            
                if(dibujos.elementAt(i) instanceof Escalar || dibujos.elementAt(i) instanceof Rotar || dibujos.elementAt(i) instanceof Trasladar || dibujos.elementAt(i) instanceof Desagrupar)
                    if(instrucciones>0){
                        transformar(dibujos.elementAt(i));
                        instrucciones--;
               } 
        }
   }    
    public String historial(){
        return historial;
    }    
    public void Color(color color){
        this.color=color.color();
        historial+=color.instruccion()+"\n";
    }    
    public void draw_Point(graficacion.Punto punto, Graphics g) {
        g.setColor(punto.color());
        if(validar(punto.centrox(),punto.centroy())){
            ArbolV+=punto.toString()+"\n";            
            g.fillOval((int)Math.rint(escalax*punto.centrox()), -(int)Math.rint(escalay*punto.centroy()), (int)Math.rint(escalax*2),(int)Math.rint(escalax*2));                    
        }
        else ArbolNV+=punto.toString()+"\n";
        Arbol+=punto.toString()+"\n";
    }    
    public void draw_Line(graficacion.Linea linea, Graphics g) {
        g.setColor(linea.color());
        if(validar(linea.punto1x(),linea.punto1y())&&validar(linea.punto2x(),linea.punto2y())){
            ArbolV+=linea.toString()+"\n";
            g.drawLine((int)Math.rint(escalax*linea.punto1x()), -(int)Math.rint(escalay*linea.punto1y()), (int)Math.rint(escalax*linea.punto2x()), -(int)Math.rint(escalay*linea.punto2y()));
        }
        else ArbolNV+=linea.toString()+"\n";
        Arbol+=linea.toString()+"\n";
    }
    public void draw_Cuadrado(graficacion.Cuadrado cuadrado, Graphics g) {
        g.setColor(cuadrado.color());
        if(validar(cuadrado.x1(),cuadrado.x2())&&validar(cuadrado.x3(),cuadrado.x4())){ 
            ArbolV+=cuadrado.toString()+"\n";
            g.drawLine((int)Math.rint(escalax*cuadrado.x1()),-(int)Math.rint(escalay*cuadrado.x2()),(int)Math.rint(escalax*cuadrado.x3()),-(int)Math.rint(escalay*cuadrado.x2()));
            g.drawLine((int)Math.rint(escalax*cuadrado.x3()),-(int)Math.rint(escalay*cuadrado.x2()),(int)Math.rint(escalax*cuadrado.x3()),-(int)Math.rint(escalay*cuadrado.x4()));
            g.drawLine((int)Math.rint(escalax*cuadrado.x3()),-(int)Math.rint(escalay*cuadrado.x4()),(int)Math.rint(escalax*cuadrado.x1()),-(int)Math.rint(escalay*cuadrado.x4()));        
            g.drawLine((int)Math.rint(escalax*cuadrado.x1()),-(int)Math.rint(escalay*cuadrado.x4()),(int)Math.rint(escalax*cuadrado.x1()),-(int)Math.rint(escalay*cuadrado.x2()));        
        }
        else ArbolNV+=cuadrado.toString()+"\n";
        Arbol+=cuadrado.toString()+"\n";
    }
    public void draw_Triangulo(graficacion.Triangulo triangulo, Graphics g) {
        g.setColor(triangulo.color());
        if(validar(triangulo.x(),triangulo.y())&&validar(triangulo.x1(),triangulo.y1())&&validar(triangulo.x2(),triangulo.y2())){
            ArbolV+=triangulo.toString()+"\n";
            g.drawLine((int)Math.rint(escalax*triangulo.x()),  -(int)Math.rint(escalay*triangulo.y()), (int)Math.rint(escalax*triangulo.x1()), -(int)Math.rint(escalay*triangulo.y1()));
            g.drawLine((int)Math.rint(escalax*triangulo.x1()), -(int)Math.rint(escalay*triangulo.y1()), (int)Math.rint(escalax*triangulo.x2()), -(int)Math.rint(escalay*triangulo.y2()));
            g.drawLine((int)Math.rint(escalax*triangulo.x2()), -(int)Math.rint(escalay*triangulo.y2()), (int)Math.rint(escalax*triangulo.x()), -(int)Math.rint(escalay*triangulo.y()));
        }
        else ArbolNV+=triangulo.toString()+"\n";
        Arbol+=triangulo.toString()+"\n";
    }
    public void draw_Circulo(graficacion.Circulo circulo, Graphics g) {
      g.setColor(circulo.color());
       if(validar(circulo.x(),circulo.y())){
           ArbolV+=circulo.toString() +"\n";           
           g.drawOval((int)Math.rint(escalax*circulo.x()), -(int)Math.rint(escalay*circulo.y()), circulo.radio()*2, circulo.radio()*2);       
       }
       else  ArbolNV+=circulo.toString()+"\n";       
       Arbol+=circulo.toString()+"\n";
    }    
    public void draw_Text(Texto texto, Graphics g){
        g.setColor(texto.color());
        if(validar(texto.x(),texto.y())){
            ArbolV+=texto.toString()+"\n";
            g.setFont( new Font( "Serif", Font.BOLD, (int)Math.rint(escalax*14) ) );
            g.drawString(texto.Texto(), (int)Math.rint(escalax*texto.x()), -(int)Math.rint(escalay*texto.y()));
        } 
        else ArbolNV+=texto.toString()+"\n";
        Arbol+=texto.toString()+"\n";        
    }
    public void draw_image(Imagen imagen, Graphics g){
        if(validar(imagen.x()-ejex,imagen.y()-ejey)){
            ArbolV+=imagen.toString();            
            g.drawImage(imagen.imagen(),imagen.x()-ejex ,imagen.y()-ejey,(int)Math.rint(escalax*imagen.width()),(int)Math.rint(escalay*imagen.height())  , this);
        }
        else ArbolNV+=imagen.toString();
        Arbol+=imagen.toString();        
    }
    public void eliminar(){         
        for(int i =dibujos.size()-limite;i<dibujos.size();i++)
            dibujos.remove(i);        
        limite=0;
    }
    public void restar_limite(){
        limite++;
        repaint();
    }
    public void sumar_limite(){
        limite= limite==0?0:limite-1;
        repaint();
    }
    public int ejex(){
        return ejex ;
    }    
    public int ejey(){
        return ejey ;
    }    
    public void Circulo(Circulo c ){        
        dibujos.add(new Circulo(c.x(), c.y(),c.radio(),color));
        historial+=c.instruccion()+"\n";
        repaint();
    }    
    public void Punto(Punto p){            
        dibujos.add(new Punto (p.centrox(), p.centroy(),color));
        historial+=p.instruccion()+"\n";
        repaint();
    }    
    public void Linea(Linea l){
        dibujos.add(new Linea(l.punto1x(), l.punto1y(), l.punto2x(), l.punto2y(),color));
        historial+=l.instruccion()+"\n";
        repaint();
    }
    public void Cuadrado(Cuadrado c){        
        dibujos.add(new Cuadrado(c.x1(),c.x2(),c.x3(),c.x4(), color));
        historial+=c.instruccion()+"\n";
        repaint();
    }    
    public void Triangulo(Triangulo t){        
        dibujos.add(new Triangulo(t.x(),t.y(),t.x1(),t.y1(),t.x2(),t.y2(), color));
        historial+=t.instruccion()+"\n";
        repaint();
    }    
    public void Texto(Texto texto){
        dibujos.add(new Texto( texto.Texto(),texto.x(),texto.y(), color));
        historial+=texto.instruccion()+"\n";
        repaint();
    }
    public void Imagen(Imagen imagen) {
        dibujos.add(new Imagen(imagen.x(),imagen.y(),imagen.width(),imagen.height(),imagen.direccion(),imagen.imagen()));     
        historial+=imagen.instruccion();
        repaint();
    }
    public void Escalar(Escalar e){
        instrucciones++;
        dibujos.add(new Escalar(e.escalar()));
        historial+=e.isntruccion()+"\n";
        transformar(e);
    }    
    public void Trasladar(Trasladar t){
        instrucciones++;
        dibujos.add(new Trasladar(t.n(),t.m()));
        historial+=t.instruccion()+"\n";
        transformar(t);
    }    
    public void Rotar(Rotar r){
        instrucciones++;
        dibujos.add(new Rotar(r.XR(),r.YR(),r.grados()));
        historial+=r.instruccion()+"\n";
        transformar(r);
    }    
    public void Koch(Koch koch) {        
        instrucciones+=1;
        dibujos.add(new Koch(koch.iteraciones()));
        historial=koch.instruccion();        
        repaint();
    }
    public void sierpinski(Sierpinski sierpinski) {        
        instrucciones++;
        dibujos.add(new Sierpinski(sierpinski.iteraciones()));
        repaint();
    }
    public void Desagrupar(Desagrupar desagrupar) {
        instrucciones++;
        dibujos.add(new Desagrupar());
        historial+="Desagrupar";
        transformar(desagrupar);
    }    
    public String arbol(){
        return Arbol;
    }    
    public String vistav(){
        return ArbolV;
    }    
    public String vistanv(){
        return ArbolNV;
    }        
    public boolean validar(int puntoX, int puntoy ){
        return puntoX>=menorx && puntoX<=mayorx && puntoy>=menory&& puntoy<=mayory? true : false;
    }
    public void limites(Vista vista) {
        dibujos.add(new Vista(vista.superiorx(),vista.superiory(),vista.inferiorx(),vista.inferiory()));
        mayorx=vista.superiorx();
        mayory=vista.superiory();
        menorx=vista.inferiorx();
        menory=vista.inferiory();         
        escalax=935/(vista.superiorx()-vista.inferiorx());
        escalay=410/(vista.superiory()-vista.inferiory());
        ejex=(int) ((int)(escalax*menorx)<0?Math.abs((escalax*menorx)):(escalax*menorx));
        ejey=(int) ((int) -(escalay*menory)>=0?(428+(escalay*menory)):(428-(escalay*menory)));
        historial+=vista.instruccion()+"\n";
        repaint();
    } 
    public void seleccionados(Seleccionar s){
        this.seleccionados=s.seleccionados();
        historial+=s.instruccion()+"\n";
    }    
    public void fractal(Instrucciones fractal,Graphics g) {
        try{
            for(int i=0;i<seleccionados.size();i++){
                if(seleccionados.elementAt(i)-1<=dibujos.size()){
                    if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Linea)
                        if(fractal instanceof Koch)
                            koch((Koch)fractal,(Linea)(dibujos.elementAt(seleccionados.elementAt(i)-1)), g);                    
                    if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Cuadrado){
                        if(fractal instanceof Koch)
                            koch((Koch)fractal,(Cuadrado)(dibujos.elementAt(seleccionados.elementAt(i)-1)), g);
                    dibujos.remove(seleccionados.elementAt(i)-1);
                    }
                    
                    if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Triangulo){
                        if(fractal instanceof Koch){
                            koch((Koch)fractal,(Triangulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)), g);
                            dibujos.remove(seleccionados.elementAt(i)-1);}
                    
                         if(fractal instanceof Sierpinski)                             
                             sierpinski((Triangulo)dibujos.elementAt(seleccionados.elementAt(i)-1),(Sierpinski)fractal,g);
                    }
                }
            }
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(this, "N0 ha sido Seleccionado Ningun Elemento", Arbol,JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void koch(graficacion.Koch koch, graficacion.Linea linea, Graphics g) {        
        paintRecursivo(g,koch.iteraciones(),linea.punto1x(),linea.punto1y(),linea.punto2x(),linea.punto2y(), linea.color(),true);
    }
    public void koch(graficacion.Koch koch, Cuadrado cuadrado, Graphics g) {        
        paintRecursivo(g,koch.iteraciones(),cuadrado.x1(),-cuadrado.x2(),cuadrado.x3(),-    cuadrado.x2(), cuadrado.color(),false);
        paintRecursivo(g,koch.iteraciones(),cuadrado.x1(),cuadrado.x2(),cuadrado.x1(),cuadrado.x4(),cuadrado.color(),true);
        paintRecursivo(g,koch.iteraciones(),cuadrado.x3(),-cuadrado.x4(),cuadrado.x1(),-cuadrado.x4(),cuadrado.color() , false);
        paintRecursivo(g,koch.iteraciones(),cuadrado.x3(),cuadrado.x4(),cuadrado.x3(),cuadrado.x2(),cuadrado.color(),true);
    } 
    public void koch(graficacion.Koch koch, Triangulo triangulo, Graphics g) {        
        paintRecursivo(g, koch.iteraciones(),triangulo.x(),triangulo.y(),triangulo.x1(),triangulo.y1(), triangulo.color(),true);
        paintRecursivo(g, koch.iteraciones(),triangulo.x1(),triangulo.y1(),triangulo.x2(),triangulo.y2(), triangulo.color(),true);
        paintRecursivo(g, koch.iteraciones(),triangulo.x(),-triangulo.y(),triangulo.x2(),-triangulo.y2(), triangulo.color(),false);        
    }    
    private void sierpinski(Triangulo triangulo,Sierpinski sierpinski, Graphics g) {
        draw_sierpinski(g, sierpinski.iteraciones(), triangulo.x(),triangulo.y(),triangulo.x2(),triangulo.y2());
    }
    public void draw_sierpinski(Graphics g, int i,double xp12, double yp12, double xp22, double yp22 ){
        double dx=(xp22-xp12)/2.;
        double dy=(yp22-yp12)/2.;
        double xp32=xp12+dx-2*dy*(Math.sin(3.14/3.));
        double yp32=yp12+dy+2*dx*(Math.sin(3.14/3.));

        double dx1=(xp22+xp12)/2.;
        double dy1=(yp22+yp12)/2.;
        double dx2=(xp32+xp22)/2.;
        double dy2=(yp32+yp22)/2.;
        double dx3=(xp12+xp32)/2.;
        double dy3=(yp12+yp32)/2.;

        if(i<=0){
            Linea(new Linea((int)xp12,(int)yp12,(int)xp22,(int)yp22));
            Linea(new Linea((int)xp22,(int)yp22,(int)xp32,(int)yp32));
            Linea(new Linea((int)xp32,(int)yp32,(int)xp12,(int)yp12));
//         g.drawLine((int)xp12,-(int)yp12,(int)xp22,-(int)yp22);
//         g.drawLine((int)xp22,-(int)yp22,(int)xp32,-(int)yp32);
//         g.drawLine((int)xp32,-(int)yp32,(int)xp12,-(int)yp12);
        }
        else{
         draw_sierpinski(g,i-1,xp12,yp12,dx1,dy1);
         draw_sierpinski(g,i-1,dx1,dy1,xp22,yp22);
         draw_sierpinski(g,i-1,dx3,dy3,dx2,dy2);
        }
    }
    private void paintRecursivo(Graphics g, int i, double xp12, double yp12, double xp22, double yp22, Color color, boolean positivo ) {
        double dx=(xp22-xp12)/3.;
        double dy=(yp22-yp12)/3.;
        double xx=xp12+3*dx/2.-dy*(Math.sin(3.14/3.));
        double yy=yp12+3*dy/2.+dx*(Math.sin(3.14/3.));
        if(i<=0){
            g.setColor(color);            
            if(positivo)
                Linea(new Linea((int)xp12,(int)yp12,(int)xp22,(int)yp22));     
            else
                Linea(new Linea((int)xp12,-(int)yp12,(int)xp22,-(int)yp22));
        }
        else{
            paintRecursivo(g,i-1,xp12,yp12,xp12+dx,yp12+dy, color,positivo);
            paintRecursivo(g,i-1,xp12+dx,yp12+dy,xx,yy, color,positivo);
            paintRecursivo(g,i-1,xx,yy,xp22-dx,yp22-dy,color,positivo);
            paintRecursivo(g,i-1,xp22-dx,yp22-dy,xp22,yp22, color,positivo);
        }   
    }
    public void transformar(Instrucciones transformar) {
        for(int i=0;i<seleccionados.size();i++){       
            if(seleccionados.elementAt(i)-1<=dibujos.size()){
                if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Circulo){
                    if(transformar instanceof Rotar)
                        rotar((Rotar)transformar,(Circulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Escalar)
                        escalar((Escalar)transformar,(Circulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Trasladar)
                        trasladar((Trasladar)transformar,(Circulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                }
                if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Cuadrado){
                    if(transformar instanceof Rotar)
                        rotar((Rotar)transformar,(Cuadrado)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Escalar)
                        escalar((Escalar)transformar,(Cuadrado)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Trasladar)
                        trasladar((Trasladar)transformar,(Cuadrado)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Desagrupar)
                        desagrupar((Desagrupar)transformar,(Cuadrado)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                        dibujos.remove(seleccionados.elementAt(i)-1);
                    }   
                 if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Linea){
                    if(transformar instanceof Rotar)
                        rotar((Rotar)transformar,(Linea)(dibujos.elementAt(seleccionados.elementAt(i)-1)));                 
                    if(transformar instanceof Escalar)
                        escalar((Escalar)transformar,(Linea)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Trasladar)
                        trasladar((Trasladar)transformar,(Linea)(dibujos.elementAt(seleccionados.elementAt(i)-1)));                        
                    }
                 if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Punto){
                    if(transformar instanceof Rotar)
                        rotar((Rotar)transformar,(Punto)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Escalar)
                        escalar((Escalar)transformar,(Punto)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Trasladar)
                        trasladar((Trasladar)transformar,(Punto)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    }
                 if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Triangulo){
                    if(transformar instanceof Rotar)
                        rotar((Rotar)transformar,(Triangulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Escalar)
                        escalar((Escalar)transformar,(Triangulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Trasladar)
                        trasladar((Trasladar)transformar,(Triangulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Desagrupar){
                        desagrupar((Desagrupar)transformar,(Triangulo)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                        dibujos.remove(seleccionados.elementAt(i)-1);
                        }
                    }
                 if(dibujos.elementAt(seleccionados.elementAt(i)-1) instanceof Texto){
                    if(transformar instanceof Rotar)
                        rotar((Rotar)transformar,(Texto)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Escalar)
                        escalar((Escalar)transformar,(Texto)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    if(transformar instanceof Trasladar)
                        trasladar((Trasladar)transformar,(Texto)(dibujos.elementAt(seleccionados.elementAt(i)-1)));
                    }
            }
            else
                JOptionPane.showMessageDialog(this, "La Opcion: "+seleccionados.elementAt(i)+" No Se a Encontrado", "OPcion No enxontrada",1);            
        }
        seleccionados.removeAllElements();
        repaint();
    }        
    public void rotar(Rotar r, Circulo circulo){
        circulo.rotar(r.XR(),r.YR(),r.grados());
    }
    public  void escalar(Escalar escalar,Circulo circulo) {
        circulo.escalar(escalar.escalar());
    }
    public void trasladar(Trasladar trasladar, Circulo circulo) {
        circulo.trasladar(trasladar.n(), trasladar.m());
    }
    public void rotar(Rotar rotar, graficacion.Cuadrado cuadrado) {
        cuadrado.rotar(rotar.XR(),rotar.YR(),rotar.grados());
    }
    public void trasladar(Trasladar trasladar, graficacion.Cuadrado cuadrado) {
        cuadrado.trasladar(trasladar.n(), trasladar.m());
    }
    public void escalar(Escalar escalar, graficacion.Cuadrado cuadrado) {
        cuadrado.escalar(escalar.escalar());
    }    
    public void rotar(Rotar rotar, Linea linea) {
        linea.rotar(rotar.XR(),rotar.YR(),rotar.grados());        
    }
    public void trasladar(Trasladar trasladar, Linea linea) {
        linea.trasladar(trasladar.n(), trasladar.m());
    }
    public void escalar(Escalar escalar, Linea linea) {
        linea.escalar(escalar.escalar());
    }
    public void rotar(Rotar rotar, Punto punto) {
        punto.rotar(rotar.XR(), rotar.YR(), rotar.grados());
    }
    public void trasladar(Trasladar trasladar, Punto punto) {      
        punto.trasladar(trasladar.n(), trasladar.m());
    }
    public void escalar(Escalar escalar, Punto punto) {
        punto.escalar(escalar.escalar());
    }    
    public void rotar(Rotar rotar, Triangulo triangulo) {
        triangulo.rotar(rotar.XR(),rotar.YR(),rotar.grados());
    }
    public  void trasladar(Trasladar trasladar, Triangulo triangulo) {
        triangulo.trasladar(trasladar.n(), trasladar.m());
    }
    public void escalar(Escalar escalar, Triangulo triangulo) {
        triangulo.escalar(escalar.escalar());
    }    
    public void rotar(Rotar rotar, Texto texto) {
        texto.rotar(rotar.XR(),rotar.YR(),rotar.grados());
    }
    public void trasladar(Trasladar trasladar, Texto texto) {
        texto.trasladar(trasladar.n(), trasladar.m());
    }
    public void escalar(Escalar escalar, Texto texto) {
        texto.escalar(escalar.escalar());
    }    
    public  void desagrupar(graficacion.Desagrupar desagrupar, graficacion.Triangulo triangulo) {
        Linea(new Linea(triangulo.x(),triangulo.y(),triangulo.x1(),triangulo.y1()));
        Linea(new Linea(triangulo.x1(),triangulo.y1(),triangulo.x2(),triangulo.y2()));
        Linea(new Linea(triangulo.x2(),triangulo.y2(),triangulo.x(),triangulo.y()));
    }
    public  void desagrupar(graficacion.Desagrupar desagrupar, Cuadrado cuadrado) {
        Linea (new Linea(cuadrado.x1(),cuadrado.x2(),cuadrado.x3(),cuadrado.x2()));
        Linea (new Linea(cuadrado.x3(),cuadrado.x2(),cuadrado.x3(),cuadrado.x4()));
        Linea (new Linea(cuadrado.x3(),cuadrado.x4(),cuadrado.x1(),cuadrado.x4()));
        Linea (new Linea(cuadrado.x1(),cuadrado.x4(),cuadrado.x1(),cuadrado.x2()));        
    }
}