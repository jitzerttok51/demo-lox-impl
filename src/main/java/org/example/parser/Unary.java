package org.example.parser;

import org.example.lexer.Token;

public record Unary(Token token, Expr expr) implements Expr {
    @Override
    public <T> T eval(Evaluator<T> evaluator) {
        return evaluator.evaluate(this);
    }
}
