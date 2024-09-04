package org.example.lexer;

public interface CharacterStream {

    char get();
    void advance();
    int line();
    int column();
    int position();
    boolean isEnd();
}
