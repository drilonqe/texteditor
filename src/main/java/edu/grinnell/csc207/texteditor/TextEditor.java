package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        screen.clear();
        int size = buf.getSize();
        for (int i = 0; i < size; i++) {
            char character = buf.getChar(i);
            screen.setCharacter(i, 0, new TextCharacter(character));
        }
        screen.setCursorPosition(new TerminalPosition(buf.getCursorPosition(), 0));
        screen.refresh();
    }

    /**
     * The main entry point for the TextEditor application.
     *
     * @param args command-line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        // TODO: fill me in with a text editor TUI!
        GapBuffer buffer = new GapBuffer();
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        String pathArgs = args[0];
        System.out.format("Loading %s...\n", pathArgs);
        java.nio.file.Path path = Paths.get(pathArgs);

        if (Files.exists(path) && Files.isRegularFile(path)) {
            String bufferString = Files.readString(path);
            for (int i = 0; i < bufferString.length(); i++) {
                buffer.insert(bufferString.charAt(i));
            }
        }

        boolean isRunning = true;
        while (isRunning) {
            drawBuffer(buffer, screen);
            KeyStroke stroke = screen.readInput();
            if (stroke.getKeyType().equals(KeyType.Character)) {
                buffer.insert(stroke.getCharacter());
            }
            if (stroke.getKeyType().equals(KeyType.Backspace)) {
                buffer.delete();
            }
            if (stroke.getKeyType().equals(KeyType.ArrowLeft)) {
                buffer.moveLeft();
            }
            if (stroke.getKeyType().equals(KeyType.ArrowRight)) {
                buffer.moveRight();
            }
            if (stroke.getKeyType().equals(KeyType.Escape)) {
                screen.stopScreen();
                isRunning = false;
            }
        }
        Files.writeString(path, buffer.toString());
    }

}
