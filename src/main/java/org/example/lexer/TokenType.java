package org.example.lexer;

public enum TokenType {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    BANG("!"),
    SINGLE_LINE_COMMENT("//"),
    MULTI_LINE_COMMENT("/*"),
    NUMBER(""),
    IDENTIFIER(""),
    TRUE("true"),
    FALSE("false"),
    NULL("nil"),
    STRING("");

    private final String code;

    TokenType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
