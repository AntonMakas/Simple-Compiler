package syntax_analyzer;

/*
* Class to store one Node
*/
public class Node {
    public String str; //Value of node
    public Node left; //Left child of Node
    public Node right; //Right child of Node
    public Node middle; //Middle child only for tern operand

    public Node(String str, Node left, Node right){
        this.str = str;
        this.left = left;
        this.right = right;
    }

    public Node(String str, Node left, Node middle, Node right){
        this.str = str;
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public Node(String str){
        this.str = str;
    }


}
