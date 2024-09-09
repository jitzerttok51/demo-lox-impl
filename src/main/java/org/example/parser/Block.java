package org.example.parser;

import java.util.List;

public record Block(List<Statement> statements) implements Statement {
    @Override
    public <T> void execute(Evaluator<T> evaluator) {
        evaluator.execute(this);
    }
}
