package graficacion;

class Sierpinski implements Instrucciones {

    private int iteraciones;    
    
    public Sierpinski( int iteraciones) {        
        this.iteraciones=iteraciones;
    }
    
    public int iteraciones(){
        return iteraciones;
    }
    
    public String instruccion(){
        return "sierpinski "+iteraciones;
    }
}
