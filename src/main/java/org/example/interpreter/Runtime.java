package org.example.interpreter;

import org.example.runtime.LoxNumber;
import org.example.runtime.LoxObject;

public class Interpreter implements Evaluator<LoxObject> {

    @Override
    public LoxObject evaluate(Expr expr) {
        return expr.eval(this);
    }

    @Override
    public LoxObject evaluate(LiteralNumber number) {
        return new LoxNumber(number.number());
    }

    @Override
    public LoxObject evaluate(Unary number) {
        var obj = number.expr().eval(this);
        return obj.callUnary(number.token().type());
    }

    @Override
    public LoxObject evaluate(Binary number) {
        var left = number.left().eval(this);
        var right = number.right().eval(this);
        return left.callBinary(number.token().type(), right);
    }
}
