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

    // maybe when you insert stuff you need to shift everything
    public void insert(char ch) {
        ensureCapacity();
        buffer[startGap] = ch;
        startGap++;
        size++;
    }

    public void delete() {
        if (startGap > 0) {
            startGap--;
            size--;
        }
    }

    public int getCursorPosition() {
        return startGap;
    }

    public void moveLeft() {
        if (startGap > 0) {
            startGap--;
            endGap--;
            buffer[endGap] = buffer[startGap];
        }
    }

    public void moveRight() {
        if (startGap > 0) {
            buffer[startGap] = buffer[endGap];
            startGap++;
            endGap++;
        }
    }

    public int getSize() {
        return size;
    }

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

    @Override
    public String toString() {
        String output = "";

        // add characters before gap
        for (int i = 0; i < startGap; i++) {
            output += buffer[i];
        }

        //add characters after gap
        for (int i = endGap; i < endGap + (size - startGap); i++){
           output += buffer[i]; 
    }

        return output;
    }
}
