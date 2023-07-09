package GUI.Listeners;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoneyKeyListener implements KeyListener {

    private static String allowed = "1234567890,";

    private boolean commaPressed = false;

    private String text = "";
    private JTextField textField;

    public MoneyKeyListener(JTextField field) {
        this.textField = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char letter = e.getKeyChar();
        boolean notAllowedChar = allowed.indexOf(letter) == -1;
        boolean alreadyContainingComma = textField.getText().contains(",") && letter == ',';
        boolean tooManyDecimals =
                textField.getText().contains(",") && textField.getCaretPosition() >= textField.getText().indexOf(",")+3;
        if(notAllowedChar || alreadyContainingComma || tooManyDecimals){
            e.consume();
        }
        else{
            //System.out.println("text: " + textField.getText());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
