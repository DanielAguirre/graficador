package graficacion;

import java.awt.Color;

public class color  implements Instrucciones{    
    
    private Color Color;
    private String color="";
    public color(String color){        
        this.color+=color;
          if(color.equalsIgnoreCase("rojo"))
              this.Color=new Color(255, 0, 0);
          if(color.equalsIgnoreCase("azul"))
              this.Color=new Color(0, 0, 255);
          if(color.equalsIgnoreCase("verde"))
              this.Color=new Color(0, 255, 0);
          if(color.equalsIgnoreCase("negro"))
              this.Color=new Color(0, 0, 0);
          if(color.equalsIgnoreCase("blanco"))
              this.Color=new Color(255, 255, 255);
          if(color.equalsIgnoreCase("gris"))
              this.Color=new Color(128, 128, 128);
          if(color.equalsIgnoreCase("rosa"))
              this.Color=new Color(255, 175, 175);
          if(color.equalsIgnoreCase("naranja"))
              this.Color=new Color(255, 200, 0);
          if(color.equalsIgnoreCase("amarillo"))
              this.Color=new Color(255, 255, 0);
          if(color.equalsIgnoreCase("amarillo"))
              this.Color=new Color(0, 255, 0);
        }
    public Color color(){
        return Color;
    }      
    public String instruccion(){        
        return "color "+color+"\n";
    }
}
