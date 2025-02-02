package syntax_analyzer;
import lexical_analyzer.*;
import java.util.List;

public class Parser{

    private List<token> tokens; //List of all tokens
    private int pos = 0; //Position of current token to be checked
    private token currentToken; //Current token

    /*Constructor*/
    public Parser(List<token> tokens){
        this.tokens = tokens;
        currentToken = tokens.get(pos);
    }

    /*
    * Methods to compare operands with current Token
    * True - if current token is operand
    * False otherwise
    */
    private boolean CompareFunctions(token token){

        if(token.func == Functions.add || token.func == Functions.div ||
                token.func == Functions.mod || token.func == Functions.mul ||
                token.func == Functions.pow || token.func == Functions.sub ||
                token.func == Functions.tern) return true;

        return false;
    }

    /*
     * Check if current Token is left bracket
    */
    private void CheckBracketsLeft(){
        if (currentToken == null || currentToken.func != Functions.Lbracket)
            throw new RuntimeException("UNEXPECTED FAIL WITH LEFT BRACKET");
        else{
            UpdatePos();
        }
    }

    /*
     * Check if current Token is right bracket
    */
    private void CheckBracketsRight(){
        if (currentToken == null || currentToken.func != Functions.Rbracket)
            throw new RuntimeException("UNEXPECTED FAIL WITH RIGHT BRACKET");
        else{
            UpdatePos();
        }
    }

    /*
    * Check if current token is comma
    */
    private void CheckComma(){
        if(currentToken.func == Functions.comma) UpdatePos();
        else throw new RuntimeException("NO COMMA IN FUNCTION");
    }

    /*
    * Update position in the list of tokens
    * Update current token
    */
    private void UpdatePos(){
        pos++;
        if (pos < tokens.size()) {
            currentToken = tokens.get(pos);
        } else {
            currentToken = null;
        }
    }

    /*
    * Count and check brackets for matching
    */
    private void CheckBrackets(){
        int left_counter = 0;
        int right_counter = 0;
        for(token token : tokens){
            if(token.func == Functions.Lbracket){
                left_counter++;
            }
            if(token.func == Functions.Rbracket){
                right_counter++;
            }
        }

        if(left_counter != right_counter){
            throw new RuntimeException("DIFFERENT AMOUNT OF BRACKETS");
        }
    }


    /*
    *Start parsing and check for end of token list
    */
    public Node helpParser(){
        Node ast = parser();

        if(currentToken != null){
            throw new RuntimeException("UNEXPECTED TOKEN: " + currentToken.value);
        }
        else return ast;
    }

    /*
    * Parse a tokens with using different methods
    * And by using Recursive with parseExpression() method create Node Tree
    */
    private Node parser(){
        CheckBrackets(); //Check for equal number of brackets
        if (currentToken == null) {
            throw new RuntimeException("UNEXPECTED FAIL");
        }

        /*Check which operand is that*/
        if(CompareFunctions(currentToken)){
            return parseExpression();
        }

        if (currentToken.func == Functions.digit){
            Node node1 = new Node(currentToken.value);
            UpdatePos();
            return node1;
        }

        else throw new RuntimeException("UNEXPECTED TOKEN: " + currentToken.value);
    }

    /*
    * Help method to return children of the main Node
    */
    private Node parseExpression(){
        if (currentToken == null) {
            throw new RuntimeException("UNEXPECTED FAIL");
        }

        Node func = new Node(currentToken.value);
        UpdatePos();

        CheckBracketsLeft();
        Node left = parser();
        CheckComma();
        if (func.str.equals("tern")){
            Node middle = parser();
            CheckComma();
            Node right = parser();
            CheckBracketsRight();
            return new Node(func.str, left, middle, right);
        }
        else {
            Node right = parser();
            CheckBracketsRight();
            return new Node(func.str, left, right);
        }

    }


}