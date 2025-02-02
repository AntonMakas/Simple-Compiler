package lexical_analyzer;
import java.util.ArrayList;
import java.util.List;
import java .util.regex.Pattern;

/*
* Class to create List of all token of our input line
*/
public class Lexer {
    public List<token> TokenArray = new ArrayList<>();
    private int pos = 0; //Position of the current char in input line
    private String input; //Input line
    private char curChar; //Current char

    public Lexer(String input) {
        this.input = input;
        this.curChar = input.charAt(pos);
    }

    /*
    * Method to update Position and CurChar
    * Add +1 to position and change current checked char
    */
    private void UpdatePos(){
        pos++;
        if (pos < input.length()){
            curChar = input.charAt(pos);
        }
        else{
            curChar = '\0';
        }
    }

    /*
    * Method that generate List of Tokens
    */
    public List<token> GetTokenList(){

        while(pos < input.length()){
            if(curChar == ' '){
                UpdatePos();
            }
            else if(curChar == '('){
                TokenArray.add(new token(Functions.Lbracket, "("));
                UpdatePos();
            }
            else if(curChar == ')'){
                TokenArray.add(new token(Functions.Rbracket, ")"));
                UpdatePos();
            }
            else if(curChar == ','){
                TokenArray.add(new token(Functions.comma, ","));
                UpdatePos();
            }
            else if(Character.isDigit(curChar) || curChar == '+' || curChar == '-'  ){
                TokenArray.add(new token(Functions.digit, SeparateDigit()));
            }
            else if(Character.isLetter(curChar)){
                SeparateLetter();
            }

            /*Check for comment block*/
            else if(curChar == '/' && input.charAt(pos+1) == '*'){
                UpdatePos();
                UpdatePos();
                while(curChar != '*' && input.charAt(pos+1) != '/'){
                    UpdatePos();
                }
                UpdatePos();
                UpdatePos();
            }
            else{
                throw new RuntimeException("UNEXPECTED TOKEN");
            }
        }
        return TokenArray;
    }


    /*
    * Method to separate digit from input line
    * by using pattern
    */
    private String SeparateDigit(){
        StringBuilder stringBuilder = new StringBuilder();

        String pattern = "^[+-]?\\d+(\\.\\d+)?([eE][+-]?\\d+)?$"; //Pattern that I got from AI
        Pattern compiledpattern = Pattern.compile(pattern);

        while(Character.isDigit(curChar) || curChar == '+' || curChar == '-' || curChar == '.' || curChar == 'e'){
            stringBuilder.append(curChar);
            UpdatePos();
        }

        if(compiledpattern.matcher(stringBuilder.toString()).matches()){
            return stringBuilder.toString();
        }
        else{
            throw new RuntimeException("INVALID NUMBER");
        }
    }

    /*
    * Separate words/functions from the input line
    */
    private void SeparateLetter(){
        StringBuilder stringBuilder = new StringBuilder();
        while(Character.isLetter(curChar)){
            stringBuilder.append(curChar);
            UpdatePos();
        }

        String word = stringBuilder.toString();
        if(word.equals(Functions.add.getStr())){
            TokenArray.add(new token(Functions.add, word));
        }
        else if(word.equals(Functions.pow.getStr())){
            TokenArray.add(new token(Functions.pow, word));
        }
        else if(word.equals(Functions.div.getStr())){
            TokenArray.add(new token(Functions.div, word));
        }
        else if(word.equals(Functions.tern.getStr())){
            TokenArray.add(new token(Functions.tern, word));
        }
        else if(word.equals(Functions.mod.getStr())){
            TokenArray.add(new token(Functions.mod, word));
        }
        else if(word.equals(Functions.mul.getStr())){
            TokenArray.add(new token(Functions.mul, word));
        }
        else if(word.equals(Functions.sub.getStr())){
            TokenArray.add(new token(Functions.sub, word));
        }
        else{
            throw new RuntimeException("UNEXPECTED TOKEN");
        }
    }
}



