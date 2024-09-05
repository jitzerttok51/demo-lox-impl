package org.example.parser;

public interface Expr {
    <T> T eval(Evaluator<T> evaluator);
}
