package org.example.lexer;

public enum TokenType {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    SINGLE_LINE_COMMENT("//"),
    MULTI_LINE_COMMENT("/*"),
    NUMBER(""),
    IDENTIFIER(""),
    STRING("");

    private final String code;

    TokenType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
