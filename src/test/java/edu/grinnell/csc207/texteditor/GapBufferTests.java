package edu.grinnell.csc207.texteditor;

import net.jqwik.api.Property;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

public class GapBufferTests {

    @Test
    public void insertDeleteTests() {
        GapBuffer buffer = new GapBuffer();
        assertEquals(0, buffer.getSize()); //check if correctly initialized
        buffer.insert('C');
        buffer.insert('S');
        buffer.insert('C');
        buffer.insert('2');
        buffer.insert('0');
        buffer.insert('7');
        assertEquals("CSC207", buffer.toString());
        assertEquals(6, buffer.getCursorPosition());

        buffer.delete();
        buffer.delete();
        buffer.delete();
        assertEquals("CSC", buffer.toString());
        assertEquals(3, buffer.getCursorPosition());
    }

    @Test
    public void testCursor() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('C');
        buffer.insert('S');
        buffer.insert('C');
        buffer.insert('2');
        buffer.insert('0');
        buffer.insert('7');

        buffer.moveLeft();
        buffer.moveLeft();
        buffer.moveLeft();
        assertEquals(3, buffer.getCursorPosition());
        buffer.insert('Z');
        assertEquals("CSCZ207", buffer.toString());

        buffer.moveRight();
        buffer.moveRight();
        buffer.moveRight();
        assertEquals(7, buffer.getCursorPosition());
    }

    @Test
    public void testGetChar() {
        GapBuffer buffer = new GapBuffer();
        buffer.insert('C');
        buffer.insert('S');
        buffer.insert('C');
        buffer.insert('2');
        buffer.insert('0');
        buffer.insert('7');

        assertEquals('2', buffer.getChar(3));
        assertEquals('C', buffer.getChar(0));
        assertEquals('7', buffer.getChar(5));
    }

    @Test
    public void testEdge() {
        GapBuffer buffer = new GapBuffer();
        // moving left on an empty buffer should not change anything.
        buffer.moveLeft();
        assertEquals(0, buffer.getCursorPosition());

        // deleting an empty buffer should not change anything.
        buffer.delete();
        assertEquals("", buffer.toString());

        // moving the cursor to the right when there is no more characters in 
        // buffer should not do anything.
        buffer.insert('x');
        buffer.moveRight();
        assertEquals(1, buffer.getCursorPosition());
    }

    @Test
    public void testArrayExpansion() {
        GapBuffer buffer = new GapBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.insert('a');
        }
        buffer.insert('b');
        assertEquals(11, buffer.getSize());
        assertEquals("aaaaaaaaaab", buffer.toString());
    }

    @Property
    void insertPropertyTest(@ForAll char ch) {
        GapBuffer buffer = new GapBuffer();
        buffer.insert(ch);
        assertEquals(1, buffer.getSize());
        assertEquals(ch, buffer.getChar(0));
        assertEquals(1, buffer.getCursorPosition());

        buffer.insert(ch);
        assertEquals(2, buffer.getSize());
        buffer.moveLeft();
        buffer.insert(ch);
        assertEquals(2, buffer.getCursorPosition());
        assertEquals(ch, buffer.getChar(2));
    }

    @Property
    void deletePropertyTest(@ForAll char ch) {
        GapBuffer buffer = new GapBuffer();
        buffer.insert(ch);
        buffer.insert(ch);
        buffer.delete();
        assertEquals(1, buffer.getSize());
        assertEquals(1, buffer.getCursorPosition());
        /* deleting twice when we have one element left will not give us 
        negative but just 0 */
        buffer.delete();
        buffer.delete();
        assertEquals(0, buffer.getSize());
        assertEquals(0, buffer.getCursorPosition());
    }
}
