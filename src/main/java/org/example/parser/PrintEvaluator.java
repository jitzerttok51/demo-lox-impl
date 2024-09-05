package org.example.parser;

public class PrintEvaluator implements Evaluator<String> {

    @Override
    public String evaluate(Expr expr) {
        return expr.eval(this);
    }

    @Override
    public String evaluate(LiteralNumber number) {
        return Double.toString(number.number());
    }

    @Override
    public String evaluate(Unary number) {
        return "("+number.token().text() + " " + number.expr().eval(this)+ ")";
    }

    @Override
    public String evaluate(Binary number) {
        return "("+number.token().text() + " " +number.left().eval(this) + " " + number.right().eval(this)+ ")";
    }
}
