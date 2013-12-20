package graficacion;

import java.util.Vector;


public class ListaLimite implements Instrucciones{

    private Vector <Figura>dibujos;
    
    public ListaLimite(Vector <Figura>dibujos){    
        this.dibujos=dibujos;
    }
    
    public ListaLimite(){
    }
}
