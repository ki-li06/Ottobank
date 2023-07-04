package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class IntegerInputKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        char inputChar = e.getKeyChar();
        if (!Character.isDigit(inputChar)) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}