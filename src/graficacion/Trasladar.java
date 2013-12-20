package graficacion;

class Trasladar implements Instrucciones {

    private int n,m;
    private Lienzo lienzo;
    
    public Trasladar(int n, int m) {
        this.n=n;
        this.m=m;
    }
    
    public int n(){
        return n;
    }    
    public int m(){
        return m;
    }
    
    public String instruccion(){
        return "trasladar "+n+","+m;
    }
}