package org.example.parser;

public interface Evaluator<T> {
    T evaluate(Expr expr);
    T evaluate(LiteralNumber number);
    T evaluate(LiteralBoolean number);
    T evaluate(Unary number);
    T evaluate(Binary number);
}
