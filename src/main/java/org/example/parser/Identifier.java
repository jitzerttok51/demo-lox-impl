package org.example.parser;

import org.example.lexer.Token;

public record Identifier(String name, Token token) implements Expr {

    public Identifier(Token token) {
        this(token.text(), token);
    }

    @Override
    public <T> T eval(Evaluator<T> evaluator) {
        return evaluator.evaluate(this);
    }
}
