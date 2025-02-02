import lexical_analyzer.*;
import syntax_analyzer.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        if(args.length > 1){
            throw new RuntimeException("TOO MUCH ARGUMENTS");
        }

        String line = args[0];
        line = line.toLowerCase();

        Lexer lexer = new Lexer(line);
        List<token> list = lexer.GetTokenList();

        if (list.isEmpty()){
            System.out.println("LIST IS EMPTY");
        }
        else{
            Parser parser = new Parser(list);
            Node ast = parser.helpParser();

            Builder builder = new Builder();
            System.out.println(builder.buildExpression(ast));
        }
    }

}

