package org.example.parser;

public record ExpressionStatement(Expr expr) implements Statement {

    @Override
    public <T> void execute(Evaluator<T> evaluator) {
        evaluator.execute(this);
    }
}
