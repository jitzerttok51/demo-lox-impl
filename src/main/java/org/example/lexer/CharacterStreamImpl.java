package org.example.lexer;

import java.util.ArrayDeque;

public class CharacterStreamImpl implements HistoryCharacterStream {

    private record Point(int index, int line, int pos){}

    private final char[] buffer;
    private int index = 0;
    private int line = 1;
    private int pos = 1;

    private final ArrayDeque<Point> history = new ArrayDeque<>();

    public CharacterStreamImpl(char[] buffer) {
        this.buffer = buffer;
    }

    public CharacterStreamImpl(String data) {
        this(data.trim().toCharArray());
    }

    @Override
    public char get() {
        return buffer[index];
    }

    @Override
    public void advance() {
        if(isEnd()) return;
        pos++;
        if(get() == '\n') {
            line++;
            pos = 1;
        }
        index++;
    }

    @Override
    public int line() {
        return line;
    }

    @Override
    public int column() {
        return pos;
    }

    @Override
    public int position() {
        return index;
    }

    @Override
    public boolean isEnd() {
        return position() == buffer.length;
    }

    @Override
    public void push() {
        history.push(new Point(index, line, pos));
    }

    @Override
    public void pop() {
        if(history.isEmpty()) return;
        var record = history.pop();
        index = record.index;
        line = record.line;
        pos = record.pos;
    }
}
