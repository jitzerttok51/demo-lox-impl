package org.example.interpreter;

public interface Evaluator<T> {
    T evaluate(Expr expr);
    T evaluate(LiteralNumber number);
    T evaluate(Unary number);
    T evaluate(Binary number);
}
