package org.example.interpreter;

import org.example.parser.Binary;
import org.example.parser.Evaluator;
import org.example.parser.Expr;
import org.example.parser.LiteralBoolean;
import org.example.parser.LiteralNumber;
import org.example.parser.Unary;
import org.example.runtime.LoxBool;
import org.example.runtime.LoxNumber;
import org.example.runtime.LoxObject;

public class Runtime implements Evaluator<LoxObject> {

    @Override
    public LoxObject evaluate(Expr expr) {
        return expr.eval(this);
    }

    @Override
    public LoxObject evaluate(LiteralNumber number) {
        return new LoxNumber(number.number());
    }

    @Override
    public LoxObject evaluate(LiteralBoolean number) {
        return new LoxBool(number.bool());
    }

    @Override
    public LoxObject evaluate(Unary number) {
        var obj = number.expr().eval(this);
        return obj.callUnary(number.token());
    }

    @Override
    public LoxObject evaluate(Binary number) {
        var left = number.left().eval(this);
        var right = number.right().eval(this);
        return left.callBinary(number.token(), right);
    }
}
