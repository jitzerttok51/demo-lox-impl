package org.example.lexer;

public interface HistoryCharacterStream extends CharacterStream {
    void push();
    void pop();
}
