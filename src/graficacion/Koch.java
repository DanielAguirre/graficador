
package graficacion;

class Koch implements Instrucciones {

    public int i;
        
    public Koch(int i) {
        this.i=i;
    }
     
   public int iteraciones(){
       return i;
   }
   
   public String instruccion(){
       return "Koch "+i;
   }
}
