/**
 *
 * @author Santiago Montoya
 * @author Sergio Atehortua Ceferino
 * 
 */
package co.edu.eafit.huffman;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
public class Tree {
    /**
     * Obtener el arbol a partir de la lista de nodos
     * @param list lista de nodos
     * @return Nodo raiz del arbol
     */
    //Genera el arbol a partir de la lista de Nodos
    public static Node genTree(ArrayList<Node> list){
        if(list.size()>1){
            list.add(new Node(
                    (char)0,
                    list.get(0).getHowMany()+list.get(1).getHowMany(),
                    list.get(0),
                    list.get(1)));
            //Elimino los dos primeros
            list.remove(0);
            list.remove(0);
            //Ordeno
            Collections.sort(list);
            return genTree(list);
        }
        return list.get(0);
    }
    /**
     * Pone el Codigo Huffman Correspondiente a cada nodo
     * segun el codigo que llevava el padre
     * @param nodo nodo a evaluar
     * @param huffcode codigo huffman del padre ó "" si es el raiz
     * @return 
     */
    public static Node evaluateNode(Node nodo, String huffcode){
        nodo.setHuffcode(huffcode);
        if(nodo.getLeft()!=null){
            Node left = evaluateNode(nodo.getLeft(), nodo.getHuffcode()+"0");
            nodo.setLeft(left);
        }
        if(nodo.getRight()!=null){
            Node right = evaluateNode(nodo.getRight(), nodo.getHuffcode()+"1");
            nodo.setRight(right);
        }
        return nodo;
    }
    /**
     * Generar la lista con los nodos y su codigo Huffman correspondiente
     * @param nodo obtener lista con caracteres y su codigo huffman
     * @return lista de caracteres
     */
    public static ArrayList<Node> getTable(Node nodo){
        ArrayList<Node> list = new ArrayList<>();
        genDot(nodo);
        getNodeHuffcode(list, nodo);
        //Eliminar sin caracter
        Iterator<Node> iterator = list.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getLetter()==(char)0){
                iterator.remove();
            }
        }
        return list;
    }
    /**
     * Procedimiente que añade los nodos validos (con caracteres) a la lista
     * @param list lista de nodos
     * @param nodo nodo a evaluar
     */
    public static void getNodeHuffcode(ArrayList<Node> list, Node nodo){
        if(nodo.getLeft()!=null){
            list.add(nodo.getLeft());
            getNodeHuffcode(list, nodo.getLeft());
        }
        if(nodo.getRight()!=null){
            list.add(nodo.getRight());
            getNodeHuffcode(list, nodo.getRight());
        }
    }
    /**
     * caracter vacio para lo nodos con caracter nulo, se usa
     * para poder dibujar el arbol
     * @param at caracter a evaluar
     * @return caracter unicode epsilon su es nulo, el mismo en otro caso
     */
    public static char epsilon(char at){
        if(at==(char)0){
            return (char)949;
        }
        return at;
    }
    /**
     * Cadena con caracter vacio para los que no tiene codigo huffman
     * @param huffcode codigo a evaluar
     * @return  cadena con caracter vacio si no tiene codigo ó el mismo codigo
     */
    public static String empty(String huffcode){
        if(huffcode.equals("")){
            return "Ø";
        }
        return huffcode;
    }
    /**
     * generar codigo dot para el nodo, se usa para generar el arbol
     * @param nodo nodo al que se le genera el codigo
     * @return cadena con el codigo dot
     */
    public static String toDot(Node nodo){
        String left = "";
        String right = "";
        String ret = "";
        String cod = "";
        if (nodo.getHuffcode().equals("")) {
            cod = "Raíz";
        }
        else{
            cod = nodo.getHuffcode();
        }
        if(nodo.getLeft()!=null){
            left = cod+" -> "+nodo.getLeft().getHuffcode()
                    +"\n";
        }
        if(nodo.getRight()!=null){
            right = cod+" -> "+nodo.getRight().getHuffcode()
                    +"[label=1]"+"\n";
        }
        ret = cod+" [shape=record, label=\"{{"+
                epsilon(nodo.getLetter())+"|"+nodo.getHowMany()+"}|"+
                empty(cod)+"}\"];\n"+left+right;
        if(nodo.getLeft()!=null){
            ret += toDot(nodo.getLeft());
        }
        if(nodo.getRight()!=null){
            ret += toDot(nodo.getRight());
        }
        return ret;
    }
    /**
     * generar codigo dot a todo el arbol
     * @param nodo nodo raiz
     */
    public static void genDot(Node nodo){
        String diagram = "digraph G {\n" +
        "    edge [label=0];\n" +
        "    graph [ranksep=0];\n" +
        toDot(nodo)+"}";
        File tree = new File("tree.dot");
	try{
            FileWriter arbol = new FileWriter(tree);
            BufferedWriter buffer = new BufferedWriter(arbol);
            PrintWriter print = new PrintWriter(buffer);  
            print.write(diagram);
            //print.append(" concatenar ");
            print.close();
            buffer.close();
	}catch(IOException e){}
        try {
        String command;
        command = tree.getAbsolutePath().substring(0,tree
                .getAbsolutePath().length()-16)+"\\bin\\"
                + "dot.exe tree.dot -Tpng -o tree.png";
        
        Process child = Runtime.getRuntime().exec(command);
        } catch (IOException e) {}
        System.out.println("Entro!");
    }
    /**
     * Se usa para crear la tabla en la interfaz graficar con la
     * informacion de los nodos de la lista de codificacion
     * @param list lista con los nodos
     * @return objeto con la tabla
     */
    public static Object[][] Matrix(ArrayList<Node> list){
        Object[][]ret = new Object[list.size()][3];
        for(int i = 0; i < list.size(); i++) {
            ret[i][0]= (char)list.get(i).getLetter();
            ret[i][1]= Integer.toString((int)list.get(i).getHowMany());
            ret[i][2]= list.get(i).getHuffcode();
        }
        return ret;
    }  
}
