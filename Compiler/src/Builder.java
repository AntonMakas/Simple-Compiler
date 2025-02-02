import lexical_analyzer.Functions;
import syntax_analyzer.Node;

public class Builder {
    public StringBuilder stringBuilder = new StringBuilder();
    public Builder(){} //Default constructor

    /*
    * Method that build string output from the Parse Tree
    */
    public String buildExpression(Node node) {
        if (node == null) { return null; }

        if (node.left != null){
            if(GetPrecedence(node.str) > GetPrecedence(node.left.str)){
                stringBuilder.append("(");
                buildExpression(node.left);
                stringBuilder.append(")");
            }
            else if(GetPrecedence(node.str) == GetPrecedence(node.left.str) && GetAssociative(node.left.str)){
                stringBuilder.append("(");
                buildExpression(node.left);
                stringBuilder.append(")");
            }
            else buildExpression(node.left);
        }


        /*Check of Tern */
        if (node.middle != null){
            stringBuilder.append("?");
            buildExpression(node.middle);
            stringBuilder.append(":");
        }
        else{
            stringBuilder.append(GetSign(node.str));
        }

        if(node.right != null){
            if(GetPrecedence(node.str) > GetPrecedence(node.right.str)){ //check for precedence level
                stringBuilder.append("(");
                buildExpression(node.right);
                stringBuilder.append(")");
            }
            else if(GetPrecedence(node.str) == GetPrecedence(node.right.str) && GetAssociative(node.right.str)){
                stringBuilder.append("(");
                buildExpression(node.right);
                stringBuilder.append(")");
            }
            else buildExpression(node.right);
        }

        return stringBuilder.toString();
    }

    /*
    * Return sign that need to use
    */
    private String GetSign(String word){
        if(word.equals(Functions.add.getStr())){
            return "+";
        }
        else if(word.equals(Functions.pow.getStr())){
            return "^";
        }
        else if(word.equals(Functions.div.getStr())){
            return "/";
        }
        else if(word.equals(Functions.mod.getStr())){
            return "%";
        }
        else if(word.equals(Functions.mul.getStr())){
            return "*";
        }
        else if(word.equals(Functions.sub.getStr())){
            return "-";
        }
        else{
            if (word.charAt(0) == '-') return String.format("(" + word + ")"); //return negative digit wit brackets
            return word; //return digit
        }
    }

    /*
    * Precedence table for operators
    */
    private int GetPrecedence(String str){
        if(str.equals(Functions.tern.getStr())) return 1;
        else if(str.equals(Functions.add.getStr()) || str.equals(Functions.sub.getStr())) return 2;
        else if(str.equals(Functions.mul.getStr()) || str.equals(Functions.div.getStr()) || str.equals(Functions.mod.getStr())) return 3;
        else if(str.equals(Functions.pow.getStr())) return 4;
        else return 5;
    }

    /*
    * Method to get Association of operand
    * Return true if right associative
    * Return false if left associative
    */
    private boolean GetAssociative(String str){
        if(str.equals(Functions.tern.getStr()) || str.equals(Functions.pow.getStr())) return true;
        else return false;
    }

}
