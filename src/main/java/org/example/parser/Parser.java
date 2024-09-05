package org.example.parser;

import org.example.lexer.Token;
import static org.example.lexer.TokenType.*;

import java.util.List;

public class Parser {

    public Expr parse(List<Token> tokens) {
        TokenStream stream = new TokenStream(tokens);
        return expr(stream);
    }

    private Expr expr(TokenStream stream) {
        return addSub(stream);
    }

    private Expr addSub(TokenStream stream) {
        var left = mulDiv(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(!List.of(PLUS, MINUS).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, expr(stream));
    }

    private Expr mulDiv(TokenStream stream) {
        var left = unary(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(!List.of(MULTIPLY, DIVIDE).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, unary(stream));
    }

    private Expr unary(TokenStream stream) {
        var token = stream.get();
        if(!List.of(PLUS, MINUS, BANG).contains(token.type())) {
            return literal(stream);
        }
        stream.advance();
        return new Unary(token, unary(stream));
    }

    private Expr literal(TokenStream stream) {
        var token = stream.get();

        var literal = switch (token.type()) {
            case NUMBER -> new LiteralNumber(Double.parseDouble(token.text()), token);
            case TRUE -> new LiteralBoolean(true, token);
            case FALSE -> new LiteralBoolean(false, token);
            default -> throw new RuntimeException("Unrecognized token type: " + token.type());
        };
        stream.advance();

        return literal;
    }
}
