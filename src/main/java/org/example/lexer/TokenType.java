package org.example.lexer;

public enum TokenType {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    EQUAL_EQUAL("=="),
    BANG_EQUAL("!="),
    GREATER_EQUAL(">="),
    LESS_EQUAL("<="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    BANG("!"),
    AND("&&"),
    OR("||"),
    OPEN_BRACE("("),
    CLOSE_BRACE(")"),
    SINGLE_LINE_COMMENT("//"),
    MULTI_LINE_COMMENT("/*"),
    NUMBER(""),
    IDENTIFIER(""),
    TRUE("true"),
    FALSE("false"),
    NULL("nil"),
    PRINT("print"),
    END(";"),
    STRING("");

    private final String code;

    TokenType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
