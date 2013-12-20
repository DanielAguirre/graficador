package graficacion;


public class Vista implements Instrucciones{
    
    private int x1 ,y1 ,x2, y2, mayorx, mayory,menorx,menory;
    
    public Vista(int x1, int y1, int x2, int y2){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;        
        ordenar();
    }
    
    public void ordenar(){
        mayorx=x1>x2?x1:x2;
        menorx=x1>x2?x2:x1;
        mayory=y1>y2?y1:y2;
        menory=y1>y2?y2:y1;
    }
    
    public int superiorx(){
        return mayorx;
    }
    
    public int superiory(){
        return mayory;
    }
    
    public int inferiorx(){
        return menorx;
    }
    
    public int inferiory(){
        return menory;
    }
    
    public String instruccion(){
        return "vista "+mayorx+","+mayory+","+menorx+","+menory+",";
    }
}