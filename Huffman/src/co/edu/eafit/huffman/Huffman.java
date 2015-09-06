/**
 *
 * Esta clase contiene procesos y funciones que se utilizan para
 * generar la lista ce nodos con su caracter, codigo huffman
 * y veces que aparece
 * @author Santiago Montoya
 * @author Sergio Atehortua Ceferino
 * 
 */
package co.edu.eafit.huffman;
import java.util.ArrayList;
import java.util.Collections;

public class Huffman {
    /**
    * Diche le posicion de una letra en una lista
    * @param list lista donde su basa el caracter
    * @param at letra a buscar
    * @return 0 si no esta, un int si se encuentra
    */
    public static int pos(ArrayList<Node> list,char at){
        for(int i = 0; i < list.size() ; i++){
            if(list.get(i).getLetter()==at){
                return i;
            }
        }
        return 0;
    }
    /**
     * Lista de nodos con su caracter, codigo huffman y veces que aparece
     * @param text texto a codificar
     * @return Lista con nodos
     */
    public static ArrayList<Node> List(String text){
        ArrayList<Node> list = new ArrayList<>();
        list.add(new Node((char)0,0));
        for(int i = 0; i < text.length(); i++){
            char letter = text.charAt(i);
            int num = pos(list, letter);
            if (num!= 0){
                list.get(num).setHowMany(list.get(num).getHowMany()+1);
            }
            else {
                list.add(new Node(letter, 1));
            }
        }
        list.remove(0);
        Collections.sort(list);
        return list;
    }
    
    /**
    * Realizar todo el procedimiento para generar el codigo huffman a
    * partir de un string
    * @param text texto a codificar
    * @return Nodo raiz del arbol
    */
    public static ArrayList<Node> doIt(String text){
        return Tree.getTable(Tree.evaluateNode(Tree.genTree(List(text)), ""));
    }
    /**
     * @param list lista de nodos
     * @param letter letra a buscar
     * @return posicion de la letra en la lista
     */
    public static int posn(ArrayList<Node> list,char letter){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getLetter()==letter){
                return i;
            }
        }
        return 0;
    }
    /**
     * @param text texto a codificar
     * @return texto codificado (unos y ceros)
     */
    public static String encode(String text){
        ArrayList<Node> doIt = doIt(text);
        String ret = "";
        for (int i = 0; i < text.length();i++) {
            ret += doIt.get(posn(doIt, text.charAt(i))).getHuffcode();
        }
        return ret;
    }
    /**
     * @param doIt lista con los nodos y su codigo
     * @param text texto a codificar
     * @return codigo huffman del texto
     */
    public static String encode(ArrayList<Node> doIt, String text){
        doIt(text);
        String ret = "";
        for (int i = 0; i < text.length();i++) {
            ret += doIt.get(posn(doIt, text.charAt(i))).getHuffcode();
        }
        return ret;
    }
}
