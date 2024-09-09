package org.example.parser;

import org.example.lexer.Token;

public record VarStatement(Token identifier, Expr expr) implements Statement {
    public String name() {
        return identifier.text();
    }

    @Override
    public <T> void execute(Evaluator<T> evaluator) {
        evaluator.execute(this);
    }
}
