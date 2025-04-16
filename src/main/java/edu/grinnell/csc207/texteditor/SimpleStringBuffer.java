package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    public String buffer;
    public int cursor;

    
     /**
     * Constructs a new, empty SimpleStringBuffer with the cursor at 0.
     */
    public SimpleStringBuffer() {
        this.buffer = "";
        this.cursor = 0;
    }

    /**
     * Inserts a character at the position of the cursor.
     *
     * @param ch character to be inserted.
     */
    public void insert(char ch) {
        String beforeCursor = buffer.substring(0, cursor);
        String afterCursor = buffer.substring(cursor);
        buffer = beforeCursor + ch + afterCursor;
        cursor++; // move cursor after inserting new character
    }

    /**
     * deletes the character in front of cursor.
     */
    public void delete() {
        if (cursor > 0) {
            String beforeCursor = buffer.substring(0, cursor - 1);
            String afterCursor = buffer.substring(cursor);

            buffer = beforeCursor + afterCursor;
            cursor--;
        }

    }

    /**
     * Gets the position of the cursor in the buffer.
     *
     * @return the index where cursor is.
     */
    public int getCursorPosition() {
        return cursor;
    }

    /**
     * moves the cursor one space to the left
     */
    public void moveLeft() {
        if (cursor > 0) {
            cursor--;
        }
    }

    /**
     * moves the cursor one space to the right
     */
    public void moveRight() {
        if (cursor < buffer.length()) {
            cursor++;
        }
    }

    /**
     * Returns the total number of character in buffer
     *
     * @return size of buffer
     */
    public int getSize() {
        return buffer.length();
    }

    /**
     * Gets the character at specified index
     *
     * @param i index of char we want
     * @return The character at specified index.
     */
    public char getChar(int i) {
        if (i < 0 || i >= buffer.length()) {
            throw new IndexOutOfBoundsException();
        }
        return buffer.charAt(i);
    }

    /**
     * Returns the buffer as a string
     *
     * @return the text as a buffer.
     */
    @Override
    public String toString() {
        return buffer;
    }
}
