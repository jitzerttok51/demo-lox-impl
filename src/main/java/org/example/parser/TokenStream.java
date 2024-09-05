package org.example.parser;

import org.example.lexer.Token;

import java.util.List;

class TokenStream {
    private final List<Token> tokens;

    private int position = 0;

    TokenStream(List<Token> tokens) {
        this.tokens = tokens;
    }

    public int position() {
        return position;
    }

    public void advance() {
        position++;
    }

    public boolean isEnd() {
        return position == tokens.size();
    }

    public Token get() {
        return tokens.get(position);
    }
}
