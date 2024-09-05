package org.example.runtime;

import org.example.lexer.TokenType;

public interface LoxObject {

    LoxObject callBinary(TokenType operation, LoxObject right);
    LoxObject callUnary(TokenType operation);
}
