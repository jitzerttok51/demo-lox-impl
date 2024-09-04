package org.example.lexer;

public class LexerInvalidStringEndingException extends RuntimeException {

    private int position, line, column;

    public LexerInvalidStringEndingException(CharacterStream ch) {
        position = ch.position();
        line = ch.line();
        column = ch.column();
    }

    @Override
    public String getMessage() {
        return "String ended unexpectedly at line " + line + " column " + column;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }
}
