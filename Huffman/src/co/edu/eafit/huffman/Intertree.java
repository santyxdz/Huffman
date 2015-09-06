/**
 *
 * @author Santiago Montoya
 * @author Sergio Atehortua Ceferino
 * 
 */
package co.edu.eafit.huffman;
import java.awt.Color;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
public class Intertree extends JFrame{
    File root = new File(".");
    private JLabel lbltree = null;
    private JScrollPane mixscroll = null;
    /**
     * Constructor de la clase de la ventana del arbol
     */
    public Intertree(){
        super("Árbol");
        String ruta = root.getAbsolutePath().substring(0,
        root.getAbsolutePath().length()-1)+"tree.png";
        ImageIcon tree = new ImageIcon(ruta);
        tree.getImage().flush();
        lbltree = new JLabel(tree);
        lbltree.setOpaque(false);
        lbltree.setBackground(Color.WHITE);
        mixscroll = new JScrollPane(lbltree);
        getContentPane().add(mixscroll);
        lbltree.repaint();
        lbltree.setIcon(tree);
        lbltree.repaint();
        setSize(720,720);
        setLocation(320,5);
        setVisible(true);
    }
}
