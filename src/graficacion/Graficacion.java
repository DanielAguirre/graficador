package graficacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Graficacion  extends JFrame implements ActionListener, MouseListener{
        
    private Lienzo lienzo;
    private Scanner flujo;
    private JFileChooser archivo;
    private File file, name;
    private FileWriter guardarA;
    private JTextField Comando;
    private JTextArea Consola;
    private JScrollPane scroll;
    private JButton abrir,guardar,undo ,redo,bmp;
    private JMenu figuras, ayuda;
    private JMenuItem Ipunto,Icirculo,Itriangulo,Ilinea,Icuadrado;
    private String Comandos="", aux="";  
    private parser p;
    private Image imagen;
    private Vector <Instrucciones> instruccion;
    private int ancho,largo;
    
    public Graficacion(){
        
        lienzo = new Lienzo(); 
        Comando = new JTextField(50);
        Consola = new JTextArea();
        scroll= new JScrollPane(Consola);
        archivo = new JFileChooser();
        abrir=new JButton(new ImageIcon(getClass().getResource("/Imagenes/open.jpg")));
        guardar=new JButton(new ImageIcon(getClass().getResource("/Imagenes/save.gif")));
        undo=new JButton(new ImageIcon(getClass().getResource("/Imagenes/undo.png")));
        redo=new JButton(new ImageIcon(getClass().getResource("/Imagenes/redo.png")));
        bmp=new JButton(new ImageIcon(getClass().getResource("/Imagenes/bmp_icon.jpg")));

        undo.setToolTipText("Deshacer");
        redo.setToolTipText("Repetir");
        bmp.setToolTipText("Mapa de Bits");
        abrir.setToolTipText("Abrir");
        guardar.setToolTipText("Guardar");

        lienzo.setBackground(Color.white);
        Consola.setEditable(true);
        
        abrir.setBounds(10,5,20,20);    
        guardar.setBounds(30,5,20,20);
        bmp.setBounds(50,5,20,20);
        undo.setBounds(70,5,20,20);
        redo.setBounds(90,5,20,20);
        lienzo.setBounds(15, 30, 950, 430);
        Comando.setBounds(15, 480, 950 , 25);
        scroll.setBounds(15, 510, 950, 130);
        lienzo.addMouseListener(this);
        abrir.addActionListener(this);
        guardar.addActionListener(this);
        bmp.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        Comando.addActionListener(this);
        Consola.setEditable(false);        
        
        Comando.setFont(new java.awt.Font("Tahoma",0,16));
        Consola.setFont(new java.awt.Font("Tahom", 5, 16));
                
        add(abrir);
        add(guardar);
        add(bmp);
        add(undo);
        add(redo);
        add(lienzo);
        add(Comando);       
        add(scroll);

//        getContentPane().setBackground(new Color(123,45,23, 200));
        getContentPane().setBackground(new Color(123,5,3, 250));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo.jpg")).getImage());
        setLayout(null);        
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {     
        new Graficacion();    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Comando){            
            analizar(Comando.getText());            
        }
        if(e.getSource()==abrir)
            Abrir();
        if(e.getSource()==guardar)
            guardar(file);
        if(e.getSource()==bmp)
            imagen();
        if(e.getSource()==undo)
            lienzo.restar_limite();
        if(e.getSource()==redo)
            lienzo.sumar_limite();
    }
    
    public void Abrir(){
        try {
            if(archivo.showOpenDialog(this) ==JFileChooser.APPROVE_OPTION){
                file=archivo.getSelectedFile();                
                flujo = new Scanner(file);
                while(flujo.hasNext())
                    analizar(flujo.nextLine());
            }
        } catch (FileNotFoundException ex) {}
    }        
    
    private void seleccionados(Seleccionar seleccionar) {
        lienzo.seleccionados(seleccionar);
    }
    
    public void analizar(String accion){
        try {
                p = new parser(new Autocad(new StringReader(accion)));                
                p.parse();
                instruccion=p.getInstruccion();
                for(Instrucciones i:instruccion ){
                    lienzo.eliminar();
                    if(i instanceof Comando)
                       Consola.setText(Comandos);
                    if(i instanceof Lista)
                       Consola.setText(lienzo.arbol());
                    if(i instanceof ListaV)
                        Consola.setText(lienzo.vistav());
                    if(i instanceof ListaNV)
                        Consola.setText(lienzo.vistanv());
                    if(i instanceof Vista)
                        lienzo.limites((Vista)i);
                    if(i instanceof Seleccionar)
                         seleccionados((Seleccionar)i);
                    if(i instanceof Circulo)
                         lienzo.Circulo((Circulo)i);
                    if(i instanceof Cuadrado)
                         lienzo.Cuadrado((Cuadrado)i);
                    if(i instanceof Linea)
                         lienzo.Linea((Linea)i);
                    if(i instanceof Punto)
                         lienzo.Punto((Punto)i);
                    if(i instanceof Triangulo)
                         lienzo.Triangulo((Triangulo)i);
                    if(i instanceof Texto)
                        lienzo.Texto((Texto)i);
                    if(i instanceof color)
                         lienzo.Color((color)i);
                    if(i instanceof Escalar)
                         lienzo.Escalar((Escalar)i);
                    if(i instanceof Rotar )
                         lienzo.Rotar((Rotar)i);
                    if(i instanceof Trasladar)
                         lienzo.Trasladar((Trasladar)i);
                    if(i instanceof Koch)                 
                        lienzo.Koch((Koch)i);
                    if(i instanceof Sierpinski)
                        lienzo.sierpinski((Sierpinski)i);
                    if(i instanceof Desagrupar)
                        lienzo.Desagrupar((Desagrupar)i);                        
                    }                
                    if(Comando.getText().compareToIgnoreCase("Comandos")!=0)
                        Comandos+=Comando.getText()+"\n";
                  } catch (Exception ex) {
                    Comandos+=Comandos.replace(Comando.getText(), "El comando :"+Comando.getText()+" No es Valido")+"\n";                 
                }
        Comando.setText("");
    }
    
    public void guardar(File file){
        try {
            guardarA = new FileWriter(file);
            guardarA.write(lienzo.historial());
            guardarA.close();
        } catch (IOException ex) {}
        catch(NullPointerException en){
            guardarC();
        }
    }
    
    public void guardarC() {                  
            boolean remplazar=false;
            try { 
                do{
                int status=archivo.showSaveDialog(this);
                int seleccion;
                if(JFileChooser.CANCEL_OPTION==status)
                    remplazar=true;
                if(JFileChooser.APPROVE_OPTION==status){
                        file=archivo.getSelectedFile();
                        if(file.exists()){
                            seleccion=JOptionPane.showOptionDialog(rootPane, "Desea Remplazar el Archivo", "Remplazar Rchivo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("interrogacion.png"),null,"nell");
                            if(seleccion!=-1)
                                if(seleccion+1==1){
                                    new FileOutputStream( file );
                                    guardar(file);
                                    remplazar=true;
                                }
                            }
                        else{
                            new FileOutputStream( file );
                            guardar(file);
                            remplazar=true;
                            }
                }
            }while(!remplazar);
            }catch (FileNotFoundException ex) {}
        }
    public void imagen(){
        JFileChooser fileChooser = new JFileChooser();;
        fileChooser.setFileFilter( new FileNameExtensionFilter(".jpg & .gif", "jpg", "gif"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            name= fileChooser.getSelectedFile();
            ancho=new ImageIcon(fileChooser.getSelectedFile().getPath()).getIconWidth();
            largo=new ImageIcon(fileChooser.getSelectedFile().getPath()).getIconWidth();
            imagen=Toolkit.getDefaultToolkit ().getImage(name.getPath());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e){
        if(imagen!=null){
            lienzo.eliminar();
            lienzo.Imagen(new Imagen(e.getX(),e.getY(),ancho,largo,name.getPath(), imagen));
            imagen=null;
        }
    }
    public void mouseReleased(MouseEvent e){}    
    public void mouseEntered(MouseEvent e){}    
    public void mouseExited(MouseEvent e){}
}