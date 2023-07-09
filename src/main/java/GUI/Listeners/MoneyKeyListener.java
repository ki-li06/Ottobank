package GUI.Listeners;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * ein KeyListener (sozusagen eine Tastatureingabe)
 * nur für Geldbeträge (bzw. Zahlen mit max. 2 Nachkommastellen)
 */
public class MoneyKeyListener implements KeyListener {

    private static String allowed = "1234567890,";


    private String text = "";
    private JTextField textField;

    public MoneyKeyListener(JTextField field) {
        this.textField = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char letter = e.getKeyChar();
        if(!allowedLetter(letter)){
            e.consume();
        }
        else{
            //System.out.println("text: " + text);
        }
    }

    private boolean allowedLetter(char letter){
        if(allowed.indexOf(letter) == -1){
            return false;
        }
        if(text.contains(",") && letter == ','){
            return false;
        }
        String text = textField.getText();
        if(text.contains(",")
                && text.indexOf(",") != (text.length() -1)
                && textField.getCaretPosition() > text.indexOf(",")
                && text.split(",")[1].length() == 2){
            return false;
        }
        return true;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
