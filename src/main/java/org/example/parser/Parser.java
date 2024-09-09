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
        return orOperation(stream);
    }

    private Expr orOperation(TokenStream stream) {
        var left = andOperation(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(token.type() != OR) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, orOperation(stream));
    }

    private Expr andOperation(TokenStream stream) {
        var left = equality(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(token.type() != AND) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, andOperation(stream));
    }

    private Expr equality(TokenStream stream) {
        var left = comparison(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(!List.of(EQUAL_EQUAL, BANG_EQUAL).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, equality(stream));
    }

    private Expr comparison(TokenStream stream) {
        var left = addSub(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(!List.of(GREATER_EQUAL, LESS_EQUAL, GREATER_THAN, LESS_THAN).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, comparison(stream));
    }

    private Expr addSub(TokenStream stream) {
        var left = mulDiv(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(!List.of(PLUS, MINUS).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, addSub(stream));
    }

    private Expr mulDiv(TokenStream stream) {
        var left = unary(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(!List.of(MULTIPLY, DIVIDE).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, mulDiv(stream));
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
