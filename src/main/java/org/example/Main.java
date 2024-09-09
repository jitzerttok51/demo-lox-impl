package org.example;

import org.example.parser.Parser;
import org.example.interpreter.Runtime;
import org.example.parser.PrintEvaluator;
import org.example.lexer.DefaultLexer;
import org.example.lexer.Lexer;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = DefaultLexer.lexer();
        var tokens = lexer.run("""
            1 * 2 * 3 + 4 == 10
            """);
        Parser parser = new Parser();
        var expr = parser.parse(tokens);
        System.out.println(new PrintEvaluator().evaluate(expr));
        System.out.println(new Runtime().evaluate(expr));
    }
}