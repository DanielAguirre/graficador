package graficacion;

class Escalar implements Instrucciones {
    
    private int escalar;
    
    public Escalar(int escalar) {
        this.escalar=escalar;
    }
    
    public int escalar(){
        return escalar;
    }
    
    public String isntruccion(){
        return "escalar "+escalar;
    }
}
