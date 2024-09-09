package org.example.runtime;

import org.example.lexer.Token;
import org.example.lexer.TokenType;

public class LoxBool implements LoxObject {

    private final boolean value;

    public LoxBool(boolean value) {
        this.value = value;
    }

    @Override
    public LoxObject callBinary(Token operation, LoxObject right) {
        if(right instanceof LoxBool loxBool) {
            return switch (operation.type()) {
                case EQUAL_EQUAL -> new LoxBool(value == loxBool.value);
                case BANG_EQUAL -> new LoxBool(value != loxBool.value);
                default -> throw new RuntimeException();
            };
        }
        throw new RuntimeException("Operation "+operation+"not supported on "+this.getClass().getSimpleName());
    }

    @Override
    public LoxObject callUnary(Token operation) {
        return switch (operation.type()) {
            case BANG -> new LoxBool(!value);
            default -> throw new RuntimeException("Operation "+operation+" not supported on "+this.getClass().getSimpleName());
        };
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
