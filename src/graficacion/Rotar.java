package graficacion;

class Rotar implements Instrucciones {
    
    private int grados,x,y;
                   
    Rotar(int x, int y, int grados) {
        this.grados=grados;
    }
    
    public int XR(){
        return x;
    }
    
    public int YR(){
        return y;
    }
    public int grados(){
        return grados;
    }
    
    public String instruccion(){
        return "rotar "+x+","+y+"y"+grados;
    }
}
