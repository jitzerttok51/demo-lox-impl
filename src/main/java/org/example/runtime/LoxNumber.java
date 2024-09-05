package org.example.runtime;

import org.example.lexer.TokenType;

public class LoxNumber implements LoxObject {

    private final double value;

    public LoxNumber(double value) {
        this.value = value;
    }

    @Override
    public LoxObject callBinary(TokenType operation, LoxObject right) {
        if(right instanceof LoxNumber loxNumber) {
            return switch (operation) {
                case PLUS -> new LoxNumber(value + loxNumber.value);
                case MINUS -> new LoxNumber(value - loxNumber.value);
                case MULTIPLY -> new LoxNumber(value * loxNumber.value);
                case DIVIDE -> new LoxNumber(value / loxNumber.value);
                default -> throw new RuntimeException();
            };
        }
        throw new RuntimeException("Unsupported operation for right value of type "+right.getClass().getSimpleName());
    }

    @Override
    public LoxObject callUnary(TokenType operation) {
        return switch (operation) {
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
