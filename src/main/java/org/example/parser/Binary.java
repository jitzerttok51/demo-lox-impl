package org.example.interpreter;

import org.example.lexer.Token;

public record Binary(Token token, Expr left, Expr right) implements Expr {
    @Override
    public <T> T eval(Evaluator<T> evaluator) {
        return evaluator.evaluate(this);
    }
}
