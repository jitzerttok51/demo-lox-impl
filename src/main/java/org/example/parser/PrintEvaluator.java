package org.example.parser;

import java.util.List;

public class PrintEvaluator implements Evaluator<String> {

    @Override
    public void run(List<Statement> statements) {

    }

    @Override
    public String evaluate(Expr expr) {
        return expr.eval(this);
    }

    @Override
    public String evaluate(LiteralNumber number) {
        return Double.toString(number.number());
    }

    @Override
    public String evaluate(LiteralBoolean number) {
        return Boolean.toString(number.bool());
    }

    @Override
    public String evaluate(Unary number) {
        return "("+number.token().text() + " " + number.expr().eval(this)+ ")";
    }

    @Override
    public String evaluate(Binary number) {
        return "("+number.token().text() + " " +number.left().eval(this) + " " + number.right().eval(this)+ ")";
    }

    @Override
    public void execute(PrintStatement statement) {

    }

    @Override
    public void execute(ExpressionStatement statement) {

    }
}
