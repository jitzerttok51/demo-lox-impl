package org.example.runtime;

import org.example.lexer.TokenType;

public class LoxBool implements LoxObject {

    private final boolean value;

    public LoxBool(boolean value) {
        this.value = value;
    }

    @Override
    public LoxObject callBinary(TokenType operation, LoxObject right) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public LoxObject callUnary(TokenType operation) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
