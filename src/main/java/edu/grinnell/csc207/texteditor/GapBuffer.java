package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private char[] buffer;
    private int startGap;
    private int size;
    private static final int INITIAL_SIZE = 10;
    private int endGap;

    public GapBuffer() {
        this.buffer = new char[INITIAL_SIZE];
        this.startGap = 0;
        this.endGap = INITIAL_SIZE;
        this.size = 0;
    }

    /**
     * Makes sure the buffer has enough capacity to insert more characters
     */
    private void ensureCapacity() {
        if (startGap == endGap) {
            int newSize = buffer.length * 2;
            char[] newBuffer = new char[newSize];
            // suggested from netbeans after doing this with a for loop
            System.arraycopy(buffer, 0, newBuffer, 0, startGap);
            int newEndGap = newSize - (size - startGap);
            System.arraycopy(buffer, endGap, newBuffer,
                    newEndGap, size - startGap);

            buffer = newBuffer;
            endGap = newEndGap;
        }
    }

    /**
     * Inserts a character at the position of the cursor.
     *
     * @param ch character to be inserted.
     */
    public void insert(char ch) {
        ensureCapacity();
        buffer[startGap] = ch;
        startGap++;
        size++;
    }

    /**
     * deletes the character in front of cursor.
     */
    public void delete() {
        if (startGap > 0) {
            startGap--;
            size--;
        }
    }

    /**
     * Gets the position of the cursor in the buffer.
     *
     * @return the index where cursor is.
     */
    public int getCursorPosition() {
        return startGap;
    }

    /**
     * moves the cursor one space to the left
     */
    public void moveLeft() {
        if (startGap > 0) {
            startGap--;
            endGap--;
            buffer[endGap] = buffer[startGap];
        }
    }

    /**
     * moves the cursor one space to the right
     */
    public void moveRight() {
        if (endGap < buffer.length) {
            buffer[startGap] = buffer[endGap];
            startGap++;
            endGap++;
        }
    }

    /**
     * Returns the total number of character in buffer
     *
     * @return size of buffer
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the character at specified index
     *
     * @param i index of char we want
     * @return The character at specified index.
     */
    public char getChar(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (i < startGap) {
            return buffer[i];
        } else {
            return buffer[endGap + i - startGap];
        }
    }

    /**
     * Returns the buffer as a string
     *
     * @return the text as a buffer.
     */
    @Override
    public String toString() {
        String output = "";

        // add characters before gap
        for (int i = 0; i < startGap; i++) {
            output += buffer[i];
        }

        //add characters after gap
        for (int i = endGap; i < endGap + (size - startGap); i++) {
            output += buffer[i];
        }

        return output;
    }
}
