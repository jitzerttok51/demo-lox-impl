package org.example.interpreter;

public interface Expr {
    <T> T eval(Evaluator<T> evaluator);
}
