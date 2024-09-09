package org.example.interpreter;

import org.example.parser.Binary;
import org.example.parser.Block;
import org.example.parser.Evaluator;
import org.example.parser.Expr;
import org.example.parser.ExpressionStatement;
import org.example.parser.Identifier;
import org.example.parser.LiteralBoolean;
import org.example.parser.LiteralNumber;
import org.example.parser.PrintStatement;
import org.example.parser.Statement;
import org.example.parser.Unary;
import org.example.parser.VarStatement;
import org.example.runtime.LoxBool;
import org.example.runtime.LoxNumber;
import org.example.runtime.LoxObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runtime implements Evaluator<LoxObject> {

    private final Map<String, LoxObject> scope = new HashMap<>();
    private final Runtime parent;

    public Runtime(Runtime parent) {
        this.parent = parent;
    }

    public Runtime() {
        this(null);
    }

    @Override
    public void run(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.execute(this);
        }
    }

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
    public LoxObject evaluate(Identifier expr) {
        return get(expr.token().text());
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

    @Override
    public void execute(PrintStatement statement) {
        var result = statement.expr().eval(this);
        System.out.println(result);
    }

    @Override
    public void execute(ExpressionStatement statement) {
        statement.expr().eval(this);
    }

    @Override
    public void execute(VarStatement statement) {
        if(scope.containsKey(statement.name())) {
            throw new RuntimeException("Cannot re-declare variable");
        }
        scope.put(statement.name(), statement.expr().eval(this));
    }

    @Override
    public void execute(Block block) {
        var runtime = new Runtime(this);
        runtime.run(block.statements());
    }

    private LoxObject get(String name) {
        var result = scope.get(name);
        if (result != null) {
            return result;
        }
        if (parent == null) {
            throw new RuntimeException();
        }
        return parent.get(name);
    }
}
