package org.example.parser;

import org.example.lexer.Token;

public record LiteralBoolean (boolean bool, Token original) implements Expr {
    @Override
    public <T> T eval(Evaluator<T> evaluator) {
        return evaluator.evaluate(this);
    }
}
