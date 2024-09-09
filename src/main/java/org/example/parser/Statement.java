package org.example.parser;

public interface Statement {
    <T> void execute(Evaluator<T> evaluator);
}
