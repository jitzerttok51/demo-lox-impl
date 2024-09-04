package org.example.lexer;

import java.util.Optional;

public interface LexerFunction {
    Optional<Token> process(CharacterStream stream);
}
