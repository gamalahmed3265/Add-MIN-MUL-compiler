package exprprser;

import java.io.IOException;

public class Parser {

    private Token currentToken = null;
    private Lexer lexer = null;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void eat(TokenType type) throws Exception {

        if (currentToken.type == type) {
            try {
                currentToken = lexer.getNextToken();
            } catch (IOException e) {
                error("Unexpected Token " + type);
            }
        } else {
            error("Unexpected Token " + type);
        }
    }

    private void error(String msg) throws Exception {
        throw new Exception(msg);
    }

    public Token getNextToken() throws Exception {
        try {
            if (currentToken == null) {
                currentToken = lexer.getNextToken();
            }
            return currentToken;
        } catch (IOException e) {
            error(e.getMessage());
            return null;
        }
    }

    public AST.Exper S() throws Exception {
        AST.Exper root = null;
        Token token = getNextToken();
        switch (token.type) {
            case NUM:
                root = E();
                eat(TokenType.EOF);
                break;
            default:
                error("Unexpected Token " + token.type);
        }
        
        return root;
    }

    public AST.Exper E() throws Exception {
        AST.Exper root = null, left;
        Token token = getNextToken();
        switch (token.type) {
            case NUM:
                left = T();
                root = E_PRIME(left);
                break;
            default:
                error("Unexpected Token " + token.type);
        }
        return root;
    }

    public AST.Exper E_PRIME(AST.Exper left) throws Exception {
        AST.Exper root = null, right;
        Token token = getNextToken();
        switch (token.type) {
            case ADD:
                eat(TokenType.ADD);
                right = E();
                root = new AST.AddExper(left, right);
                break;
            case EOF:
                root = left;
                break;
            default:
                error("Unexpected Token " + token.type);
        }
        
        return root;
    }

    public AST.Exper T() throws Exception {
        AST.Exper root = null, left;
        Token token = getNextToken();
        switch (token.type) {
            case NUM:
                left = F();
                root = T_PRIME(left);
                break;
            default:
                error("Unexpected Token " + token.type);
        }
        return root;
    }

    public AST.Exper T_PRIME(AST.Exper left) throws Exception {
        AST.Exper root = null, right;
        Token token = getNextToken();
        switch (token.type) {
            case MUL:
                eat(TokenType.MUL);
                right = T();
                root = new AST.MulExper(left, right);
                break;
            case ADD:
            case EOF:
                root = left;
                break;
            default:
                error("Unexpected Token " + token.type);
        }
        
        return root;
    }

    public AST.Exper F() throws Exception {
        AST.Exper root = null;
        Token token = getNextToken();
        switch (token.type) {
            case NUM:
                eat(TokenType.NUM);
                root = new AST.NumExper(Double.parseDouble(token.text));
                break;
            default:
                error("Unexpected Token " + token.type);
        }

        return root;
    }
}
