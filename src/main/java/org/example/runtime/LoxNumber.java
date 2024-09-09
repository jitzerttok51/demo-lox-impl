package org.example.runtime;

import org.example.lexer.Token;
import org.example.lexer.TokenType;

public class LoxNumber implements LoxObject {

    private final double value;

    public LoxNumber(double value) {
        this.value = value;
    }

    @Override
    public LoxObject callBinary(Token operation, LoxObject right) {
        if(right instanceof LoxNumber loxNumber) {
            return switch (operation.type()) {
                case PLUS -> new LoxNumber(value + loxNumber.value);
                case MINUS -> new LoxNumber(value - loxNumber.value);
                case MULTIPLY -> new LoxNumber(value * loxNumber.value);
                case DIVIDE -> new LoxNumber(value / loxNumber.value);
                case GREATER_EQUAL -> new LoxBool(value >= loxNumber.value);
                case LESS_EQUAL -> new LoxBool(value <= loxNumber.value);
                case LESS_THAN -> new LoxBool(value < loxNumber.value);
                case GREATER_THAN -> new LoxBool(value > loxNumber.value);
                case EQUAL_EQUAL -> new LoxBool(value == loxNumber.value);
                case BANG_EQUAL -> new LoxBool(value != loxNumber.value);
                default -> throw new RuntimeException();
            };
        }
        throw new RuntimeException("Unsupported operation for right value of type "+right.getClass().getSimpleName());
    }

    @Override
    public LoxObject callUnary(Token operation) {
        return switch (operation.type()) {
            case PLUS -> new LoxNumber(+value);
            case MINUS -> new LoxNumber(-value);
            default -> throw new RuntimeException();
        };
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
