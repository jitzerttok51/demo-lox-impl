package org.example;

import org.example.interpreter.Binary;
import org.example.interpreter.Expr;
import org.example.interpreter.LiteralNumber;
import org.example.interpreter.Unary;
import org.example.lexer.Token;
import org.example.lexer.TokenType;

import java.util.List;

public class Parser {

    class TokenStream {
        private final List<Token> tokens;
        private int position = 0;

        TokenStream(List<Token> tokens) {
            this.tokens = tokens;
        }

        public int position() {
            return position;
        }

        public int advance() {
            return position++;
        }

        public boolean isEnd() {
            return position == tokens.size();
        }

        public Token get() {
            return tokens.get(position);
        }
    }

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
        if(!List.of(TokenType.PLUS, TokenType.MINUS).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, expr(stream));
    }

    private Expr mulDiv(TokenStream stream) {
        var left = unary(stream);
        if(stream.isEnd()) return left;
        var token = stream.get();
        if(!List.of(TokenType.MULTIPLY, TokenType.DIVIDE).contains(token.type())) {
            return left;
        }
        stream.advance();
        return new Binary(token, left, unary(stream));
    }

    private Expr unary(TokenStream stream) {
        var token = stream.get();
        if(!List.of(TokenType.PLUS, TokenType.MINUS).contains(token.type())) {
            return literal(stream);
        }
        stream.advance();
        return new Unary(token, literal(stream));
    }

    private Expr literal(TokenStream stream) {
        var token = stream.get();
        if(token.type() == TokenType.NUMBER) {
            stream.advance();
            return new LiteralNumber(Double.parseDouble(token.text()));
        }

        throw new RuntimeException("Unrecognized token type: " + token.type());
    }
}
