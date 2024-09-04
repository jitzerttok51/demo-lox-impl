package org.example;

import org.example.lexer.Lexer;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        lexer.run("""
            // Hello World
            3.14/2
            var test123
            "Tet
            "
            """);
    }
}