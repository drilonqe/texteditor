package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    public String buffer;
    public int cursor;

    public SimpleStringBuffer() {
        this.buffer = "";
        this.cursor = 0;
    }

    public void insert(char ch) {
        String beforeCursor = buffer.substring(0, cursor);
        String afterCursor = buffer.substring(cursor);
        buffer = beforeCursor + ch + afterCursor;
        cursor++; // move cursor after inserting new character
    }

    public void delete() {
        if (cursor > 0) {
            String beforeCursor = buffer.substring(0, cursor - 1);
            String afterCursor = buffer.substring(cursor);
            
           buffer = beforeCursor + afterCursor;
            cursor--;
        }

    }

    public int getCursorPosition() {
        throw new UnsupportedOperationException("Unimplemented method 'getCursorPosition'");
    }

    public void moveLeft() {
        throw new UnsupportedOperationException("Unimplemented method 'moveLeft'");
    }

    public void moveRight() {
        throw new UnsupportedOperationException("Unimplemented method 'moveRight'");
    }

    public int getSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    public char getChar(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'getChar'");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }
}
