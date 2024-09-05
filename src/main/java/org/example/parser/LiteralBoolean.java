package org.example.parser;

public record LiteralBoolean (boolean bool) implements Expr {
    @Override
    public <T> T eval(Evaluator<T> evaluator) {
        return evaluator.evaluate(this);
    }
}
