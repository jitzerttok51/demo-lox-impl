package org.example.parser;

import java.util.List;

public interface Evaluator<T> {
    void run(List<Statement> statements);
    T evaluate(Expr expr);
    T evaluate(LiteralNumber number);
    T evaluate(LiteralBoolean number);
    T evaluate(Unary number);
    T evaluate(Binary number);
    void execute(PrintStatement statement);
    void execute(ExpressionStatement statement);
}
