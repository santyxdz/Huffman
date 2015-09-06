/**
 * Clase que define a los "Node" con los cuales se trabaja a lo
 * largo de toda la App
 * @author Santiago Montoya
 * @author Sergio Atehortua Ceferino
 * 
 */
package co.edu.eafit.huffman;
public class Node implements Comparable<Node>{
    //Atributos
    private Node left = null;
    private Node right = null;
    private char letter = 0;
    private int how_many = 0;
    private String huffcode = "";
    
    //Constructores
    /**
     * Constructor con todos los parametros
     * @param letter letra del nodo
     * @param how_many cuantas letras hay en el texto
     * @param left hijo izquierdo
     * @param right hijo derecho
     */
    public Node(char letter, int how_many, Node left, Node right){
        this.letter = letter;
        this.how_many = how_many;
        this.left = left;
        this.right = right;
    }
    /**
     * Constructor sin hijos
     * @param letter letra del nodo
     * @param how_many  cuantas letras hay
     */
    public Node(char letter, int how_many){
        this.letter = letter;
        this.how_many = how_many;
    }
    /**
     * Constructor solo con hijos
     * @param left hijo izquierdo
     * @param right hijo derecho
     */
    public Node(Node left, Node right){
    	this.left = left;
    	this.right = right;
    }
    //Métodos
    	//Getters
        /**
         * Obtener hijo Izquierdo
         * @return hijo izquierdo
         */
    	public Node getLeft(){
            return this.left;
    	}
        /**
         * Obtener hijo derecho
         * @return hijo derecho
         */
	public Node getRight(){
            return this.right;
	}
        /**
         * Obtener caracter del nodo
         * @return letra del nodo
         */
        public char getLetter(){
            return this.letter;
        }
        /**
         * Obtener cuantos caracteres hay
         * @return numero de caracteres
         */
        public int getHowMany(){
            return this.how_many;
        }
        /**
         * Obtener codigo de Huffman
         * @return codigo de huffman
         */
        public String getHuffcode(){
            return this.huffcode;
        }
    	//Setters
        /**
         * Establecer hijo izquierdo
         * @param node hijo izquierdo
         */
    	public void setLeft(Node node){
            this.left = node;
    	}
        /**
         * Establecer hijo derecho
         * @param node hijo derecho
         */
	public void setRight(Node node){
            this.right = node;
	}
        /**
         * Establecer caracter
         * @param letter caracter del nodo
         */
        public void setLetter(char letter){
            this.letter = letter;
        }
        /**
         * Establecer cuantos hay
         * @param how_many cuantos hay
         */
        public void setHowMany(int how_many){
            this.how_many = how_many;
        }
        /**
         * Establecer codigo huffman
         * @param huffcode codigo huffman
         */
        public void setHuffcode(String huffcode){
            this.huffcode = huffcode;
        }
        /**
         * Metodo de comparacion para el sort en una Lista de nodos
         * @param o nodo a comparar
         * @return 0 si sin iguales, 1 si el actual es mayor, -1 si es menor
         */
    @Override
    public int compareTo(Node o) {
        if(this.how_many == o.getHowMany()){
            return 0;
        }
        else if(this.how_many >= o.getHowMany()){
            return 1; //Ascendente
            //return -1; //Desendente
        }
        else{
            return -1; //Ascendente
            //return 1; //Desendente
        }
    }
}
