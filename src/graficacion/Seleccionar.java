package graficacion;

import java.util.Vector;

class Seleccionar implements Instrucciones {
    Vector <Integer> numeros = new Vector<Integer>();
    
    public Seleccionar(Vector<Integer> numeros) {
        this.numeros=numeros;        
    }        
    
    public Vector<Integer> seleccionados(){
        return numeros;
    }
    
    public String instruccion(){
        String instruccion="seleccionar "+numeros.elementAt(0);
        
        for (int i=1;i<numeros.size();i++)
            instruccion+=","+numeros.elementAt(i);
            
        return instruccion;
    }
}
