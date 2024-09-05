package org.example.lexer;

import java.util.List;
import java.util.Optional;

public class DefaultLexer {

    public static Lexer lexer() {
        return new Lexer(List.of(
            singleTokenFunction(TokenType.PLUS),
            singleTokenFunction(TokenType.MINUS),
            singleTokenFunction(TokenType.MULTIPLY),
            DefaultLexer::singleLineComment,
            singleTokenFunction(TokenType.DIVIDE),
            DefaultLexer::numberFunction,
            DefaultLexer::identifierFunction,
            DefaultLexer::stringFunction
        ));
    }

    private static Optional<Token> singleLineComment(CharacterStream stream) {
        var line = stream.line();
        var position = stream.position();
        var column = stream.column();
        if(stream.get() != '/') {
            return Optional.empty();
        }
        stream.advance();
        if(stream.get() != '/') {
            return Optional.empty();
        }
        stream.advance();
        StringBuilder sb = new StringBuilder();
        while (!stream.isEnd() && stream.get() != '\n') {
            sb.append(stream.get());
            stream.advance();
        }
        var result = new Token(TokenType.SINGLE_LINE_COMMENT, sb.toString(), position, line, column);
        stream.advance();
        return Optional.of(result);
    }

    private static LexerFunction singleTokenFunction(TokenType tokenType) {
        return stream -> {
            if(stream.get() != tokenType.getCode().charAt(0)) {
                return Optional.empty();
            }
            var result = new Token(tokenType, tokenType.getCode(), stream.position(), stream.line(), stream.column());
            stream.advance();
            return Optional.of(result);
        };
    }

    private static Optional<Token> numberFunction(CharacterStream stream) {
        var line = stream.line();
        var position = stream.position();
        var column = stream.column();
        if(!Character.isDigit(stream.get())) {
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder();
        boolean expectDot = true;
        while (!stream.isEnd() && (Character.isDigit(stream.get()) || (stream.get() == '.' && expectDot))) {
            sb.append(stream.get());
            if(stream.get() == '.') {
                expectDot = false;
            }
            stream.advance();
        }
        return Optional.of(new Token(TokenType.NUMBER, sb.toString(), position, line, column));
    }

    private static Optional<Token> identifierFunction(CharacterStream stream) {
        var line = stream.line();
        var position = stream.position();
        var column = stream.column();
        if(!Character.isAlphabetic(stream.get()) && stream.get() != '_') {
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder();
        while (!stream.isEnd() && (
            Character.isAlphabetic(stream.get()) ||
                Character.isDigit(stream.get()) ||
                stream.get() == '_')) {
            sb.append(stream.get());
            stream.advance();
        }
        var value = sb.toString();
        var type = switch (value) {
            case "true" -> TokenType.TRUE;
            case "false" -> TokenType.FALSE;
            case "nil" -> TokenType.NULL;
            default -> TokenType.IDENTIFIER;
        };
        return Optional.of(new Token(type, value, position, line, column));
    }

    private static Optional<Token> stringFunction(CharacterStream stream) {
        var line = stream.line();
        var position = stream.position();
        var column = stream.column();
        if(stream.get() != '"') {
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder();
        stream.advance();
        boolean ignoreNext = false;
        while (true) {
            if(stream.isEnd() || stream.get() == '\n' || stream.get() == '\r') {
                throw new LexerInvalidStringEndingException(stream); // Invalid string ending
            }

            if(stream.get() == '\\') {
                ignoreNext = true;
                stream.advance();
                continue;
            }

            if(ignoreNext) {
                ignoreNext = false;
                if(stream.get() == '"') {
                    sb.append(stream.get());
                }
                if(stream.get() == 'n') {
                    sb.append('\n');
                }
                if(stream.get() == 't') {
                    sb.append('\t');
                }
                if(stream.get() == 'r') {
                    sb.append('\r');
                }
                if(stream.get() == '\\') {
                    sb.append('\\');
                }
                stream.advance();
            } else {
                if(stream.get() == '"') {
                    stream.advance();
                    break;
                }
                sb.append(stream.get());
                stream.advance();
            }




        }
        return Optional.of(new Token(TokenType.STRING, sb.toString(), position, line, column));
    }
}
