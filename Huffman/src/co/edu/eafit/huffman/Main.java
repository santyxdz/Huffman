/**
 * Clase principal general la interfaz y el resto
 * @author Santiago Montoya
 * @author Sergio Atehortua Ceferino
 * 
 */
package co.edu.eafit.huffman;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Main extends JFrame implements ActionListener{
    private final JLabel lbl,lblequi,lblnombr;
    private final JButton btn, btnlim,btntree;
    private final JTextArea txt_texto,txt_cifrar;
    private JScrollPane l,txt,equiv,huffman,tituequi;
    private String[] Nombrescol = {"Simbolo","Repetido","Cifrado"}; 
    /**
     * Constructor de la ventana
     */
    public Main(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setTitle("Algoritmo de Huffman");
        setLocation(400,150);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("Orange-64.png")
        ).getImage());
        
        txt_texto = new JTextArea();
        l = new JScrollPane(txt_texto);
        l.setBounds(10, 20, 400, 290);
        txt_texto.setToolTipText("Ingrese simbolos");
        txt_texto.setBackground(new Color(250,250,250));
        
        txt_cifrar = new JTextArea();
        txt = new JScrollPane(txt_cifrar);
        txt.setBounds(10,348,249, 35);
        txt_cifrar.setEditable(false);
        txt_cifrar.setBackground(new Color(250,250,250));
        
        btn = new JButton("Cifrar");
        btn.setBounds( 262, 322, 80, 30);
        btn.setActionCommand("Cifrar");
        btn.addActionListener(this);
        
        Icon tree = new ImageIcon(getClass().getResource("tree.png"));
        btntree = new JButton(tree);
        btntree.addActionListener(this);
        btntree.setActionCommand("Graficar");
        btntree.setToolTipText("Graficar Arbol");
        btntree.setBounds( 345, 322, 60, 63);
        btntree.setBackground(Color.white);
        
        Icon limpiar = new ImageIcon(getClass().getResource("limpiar.png"));
        btnlim = new JButton("Limpiar");
        btnlim.setBounds( 262, 355, 80, 30);
        btnlim.setToolTipText("Limpiar");
        //btnlim.setBackground(Color.white);
        btnlim.setActionCommand("Limpiar");
        btnlim.addActionListener(this);
        lblnombr = new JLabel("Huffman",JLabel.CENTER);
        lblnombr.setOpaque(true);
        lblnombr.setFont(new Font("Times Roman",0, 16)); 
        huffman = new JScrollPane(lblnombr);
        huffman.setBounds( 10, 325, 249, 24);
        lblnombr.setBackground(new Color(250,250,250));
        
        Icon imagen = new ImageIcon(getClass().getResource("bg.png"));
        lbl = new JLabel(imagen);
        lbl.setBounds(0, 0, 640,425);
        
        lblequi = new JLabel("Equivalencias",JLabel.CENTER);
        lblequi.setFont(new Font("Times Roman",Font.BOLD,13));
        lblequi.setBackground(new Color(7,79,79));
        lblequi.setForeground(Color.white);
        lblequi.setOpaque(true);
        tituequi = new JScrollPane(lblequi);
        tituequi.setBounds(415, 20, 210, 25);
        
        add(btnlim);
        add(btn);
        add(btntree);
        add(txt);
        add(l);
        add(tituequi);
        //add(equiv);
        add(huffman);
        getContentPane().add(lbl);
    }
    /**
     * Quita el editar de la tablas de nodo
     * @param row fila
     * @param column columna
     * @return si lo quito o no
     */
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    /**
     * Maneja los eventos de los botones y reconoce cual fue pulsado
     * @param e  evento desde el boton
     */
    @Override
    public void actionPerformed(ActionEvent e ){
        String comando = e.getActionCommand();
          if("Cifrar".equals(comando)){ 
              File root = new File(".");
              File tree = new File(root.getAbsolutePath()
                      .substring(0,root.getAbsolutePath().length()-1)
                      +"tree.png");
              if(tree.exists()){
                  tree.delete();
              }
              if(txt_texto.getText().length()>1){
                if((repeated(txt_texto.getText()))){
                String text = txt_texto.getText();
                ArrayList<Node> list = Huffman.doIt(text);
                txt_cifrar.setText(Huffman.encode(list,text));
                Object[][] datos = Tree.Matrix(list);
                DefaultTableModel dtm = new DefaultTableModel(datos,Nombrescol);
                final JTable table = new JTable(dtm);
                table.getTableHeader().setReorderingAllowed(false);
                JTableHeader header = table.getTableHeader();
                header.setBackground(new Color(119,136,153));
                header.setForeground(Color.white);
                header.setFont(new Font("Times Roman",Font.BOLD,12));
                equiv = new JScrollPane(table);
                equiv.setBounds(415, 45, 210, 340);
                TableColumn col = table.getColumnModel().getColumn(0);
                col.setResizable(false);
                TableColumn col1 = table.getColumnModel().getColumn(1);
                col1.setResizable(false);
                TableColumn col2 = table.getColumnModel().getColumn(2);
                col2.setResizable(false);
                this.add(equiv);
                table.setEnabled(false);
                }else{ 
                    txt_cifrar.setText(printRepead(txt_texto.getText()));
                    //Imprimir tabla
                    Object[][] datos = {
                    {txt_texto.getText().charAt(0), 
                    new Integer(cont(txt_texto.getText())), new Integer(0)},
                    };
                    DefaultTableModel dtm = new DefaultTableModel(datos,
                            Nombrescol);
                    final JTable table = new JTable(dtm);
                    JTableHeader header = table.getTableHeader();
                    header.setBackground(new Color(119,136,153));
                    header.setForeground(Color.white);
                    header.setFont(new Font("Times Roman",Font.BOLD,12));
                    equiv = new JScrollPane(table);
                    equiv.setBounds(415, 45, 210, 340);
                    table.setEnabled(false);
                    this.add(equiv);
                    }
            }else if(txt_texto.getText().length()==1){
            
            txt_cifrar.setText("0");
            Object[][] datos = {
                    {txt_texto.getText().charAt(0), 1,0}
                    };
                    DefaultTableModel dtm = new DefaultTableModel(datos,Nombrescol);
                    final JTable table = new JTable(dtm);
                    JTableHeader header = table.getTableHeader();
                    header.setBackground(new Color(119,136,153));
                    header.setForeground(Color.white);
                    //table.setBackground(Color.red);
                    header.setFont(new Font("Times Roman",Font.BOLD,12));
                    equiv = new JScrollPane(table);
                    equiv.setBounds(415, 45, 210, 340);
                    this.add(equiv);        
            }
        else{
            JOptionPane.showMessageDialog(null,"Ingrese un Texto para cifrar");
            }
          }
        if("Limpiar".equals(comando)){
            txt_texto.setText("");
            txt_cifrar.setText("");
            this.remove(this.equiv);
            repaint();
        }
        if("Graficar".equals(comando)){
            Intertree intertree = new Intertree();
        }
    }
    /**
     * Por si es una sola letra
     * @param str cadena de caracteres
     * @return true si hay, false si no
     */
    public boolean repeated(String str){
        boolean res = false;
        for(int i=0;i<str.length()-1;i++){
            if(str.charAt(i) != str.charAt(i+1)){
                res = true;
                break;
            }
        }
        return res;
    }
    /**
     * Cadena por si solo hay caracteres iguales
     * @param str cadena de texto
     * @return codigo huffman de caracteres iguales
     */
    public String printRepead(String str){
        String cod = "";
        for(int i=0;i<cont(str);i++){
            cod += "0";
        }
        return cod;
    }
    /**
     * cuenta el numero deletra
     * @param str cadena
     * @return numero de letras
     */
    public int cont(String str){
        int cont = 0;
        for(int i = 0; i<str.length();i++){
            cont++;
        }
        return cont;
    }
    /**
     * Hace la instancia del objeto
     * @param args No SIRVEN PA' NADAAA!!!,PA' NADAAA!!!
     */
    public static void main(String[] args) {
        Main windows = new Main();
        windows.setSize(640,425);
    } 
    
}