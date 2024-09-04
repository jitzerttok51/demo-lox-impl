package org.example.lexer;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CharacterStreamImplTest {

    @Test
    public void testGet() {
        char[] data = "Test123".toCharArray();
        CharacterStreamImpl stream = new CharacterStreamImpl(data);
        assertEquals(data[0], stream.get());
    }

    @Test
    public void testAdvance() {
        char[] data = "Test123".toCharArray();
        CharacterStreamImpl stream = new CharacterStreamImpl(data);
        assertEquals(data[0], stream.get());
        stream.advance();
        assertEquals(data[1], stream.get());
        stream.advance();
        assertEquals(data[2], stream.get());
        stream.advance();
    }

    @Test
    public void testPosition() {
        char[] data = "Test123".toCharArray();
        CharacterStreamImpl stream = new CharacterStreamImpl(data);
        assertEquals(0, stream.position());
        stream.advance();
        assertEquals(1, stream.position());
        stream.advance();
        assertEquals(2, stream.position());
        stream.advance();
    }

    @Test
    public void testLineAndColumn() {
        char[] data = "Test123\nTest123".toCharArray();
        CharacterStreamImpl stream = new CharacterStreamImpl(data);
        assertEquals('T', stream.get());
        assertEquals(0, stream.position());
        assertEquals(1, stream.line());
        IntStream.range(0, 12).forEach(x->stream.advance());
        assertEquals(12, stream.position());
        assertEquals(2, stream.line());
        assertEquals(5, stream.column());
        assertEquals('1', stream.get());
    }

    @Test
    public void testIsEnd() {
        char[] data = "Test123\nTest123".toCharArray();
        CharacterStreamImpl stream = new CharacterStreamImpl(data);
        assertFalse(stream.isEnd());
        IntStream.range(0, 100).forEach(x->stream.advance());
        assertTrue(stream.isEnd());
    }
}