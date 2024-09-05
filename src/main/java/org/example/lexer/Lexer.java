package org.example.lexer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Lexer {

    private final List<LexerFunction> lexerFunctions;

    public Lexer(List<LexerFunction> lexerFunctions) {
        this.lexerFunctions = Collections.unmodifiableList(lexerFunctions);
    }

    public List<Token> run(String input) {
        HistoryCharacterStream stream = new CharacterStreamImpl(input);
        List<Token> tokens = new ArrayList<>();
        outer: while (!stream.isEnd()) {
            for(LexerFunction lexerFunction : lexerFunctions) {
                stream.push();
                var result = lexerFunction.process(stream);
                if(result.isPresent()) {
                    tokens.add(result.get());
                    continue outer;
                }
                stream.pop();
            }
            if(Set.of('\n', '\t', ' ').contains(stream.get())) {
                stream.advance();
            } else {
                throw new LexerUnrecognizedCharacter(stream);
            }
        }
        return List.copyOf(tokens);
    }


}
