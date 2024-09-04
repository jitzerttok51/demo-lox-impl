package org.example.lexer;

public record Token(TokenType type, String text, int index, int line, int pos) {
}
