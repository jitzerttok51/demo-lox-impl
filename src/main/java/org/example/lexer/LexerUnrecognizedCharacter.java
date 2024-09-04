package org.example.lexer;

public class LexerUnrecognizedCharacter extends RuntimeException {

    private char text;
    private int position, line, column;

    public LexerUnrecognizedCharacter(CharacterStream ch) {
        text = ch.get();
        position = ch.position();
        line = ch.line();
        column = ch.column();
    }

    @Override
    public String getMessage() {
        return "Unrecognized character " + text + " at line " + line + " column " + column;
    }

    public char getText() {
        return text;
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
