package exprprser;

import java.io.StringReader;
public class ExprPrser {

    public static void main(String[] args) throws Exception {

        String statement = "120 * 250 + 20";
        StringReader r = new StringReader(statement);
        Lexer l = new Lexer(r);
        /*
        Token token;
        while ((token = l.getNextToken()).type != TokenType.EOF) {
            System.out.println(token);
        }*/

        Parser p = new Parser(l);

        
//        p.S();
//        
        AST.Exper root = p.S();
        System.out.println("Equation = " + root.toString());
        System.out.println("result = " + root.evaluate());
//
        System.out.println("build Successful");

    }

}
