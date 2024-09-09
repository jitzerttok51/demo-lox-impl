package org.example;

import org.example.parser.Parser;
import org.example.interpreter.Runtime;
import org.example.lexer.DefaultLexer;
import org.example.lexer.Lexer;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = DefaultLexer.lexer();
        var tokens = lexer.run("""
            print (2 + 4) * 10;
            print (2 + 4) * 10;
            """);
        Parser parser = new Parser();
        var program = parser.parseProgram(tokens);
//        System.out.println(new PrintEvaluator().evaluate(expr));
        new Runtime().run(program);
    }
}