package org.example.runtime;

import org.example.lexer.TokenType;

public class LoxBool implements LoxObject {

    private final boolean value;

    public LoxBool(boolean value) {
        this.value = value;
    }

    @Override
    public LoxObject callBinary(TokenType operation, LoxObject right) {
        throw new RuntimeException("Operation "+operation+"not supported on "+this.getClass().getSimpleName());
    }

    @Override
    public LoxObject callUnary(TokenType operation) {
        return switch (operation) {
            case BANG -> new LoxBool(!value);
            default -> throw new RuntimeException("Operation "+operation+"not supported on "+this.getClass().getSimpleName());
        };
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
