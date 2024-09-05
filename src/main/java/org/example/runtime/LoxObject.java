package org.example.runtime;

import org.example.lexer.Token;
import org.example.lexer.TokenType;

public interface LoxObject {

    LoxObject callBinary(Token operation, LoxObject right);
    LoxObject callUnary(Token operation);
}
