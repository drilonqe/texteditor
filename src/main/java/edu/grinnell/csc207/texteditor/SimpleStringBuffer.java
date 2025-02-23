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
        return cursor;
    }
    

    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    public void moveRight() {
        if(cursor < buffer.length()){
            cursor++;
        }
    }

    public int getSize() {
        return buffer.length();
    }

    public char getChar(int i) {
        if(i < 0 || i >= buffer.length()){
            throw new IndexOutOfBoundsException();
        }
        return buffer.charAt(i);
    }

    @Override
    public String toString() {
        return buffer;
    }
}
