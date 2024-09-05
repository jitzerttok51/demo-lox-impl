package org.example.parser;

public record Literal(Object object) implements Expr {
    @Override
    public <T> T eval(Evaluator<T> evaluator) {
        return null;
    }
}
