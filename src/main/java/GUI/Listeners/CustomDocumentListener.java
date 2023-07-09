package GUI.Listeners;

import GUI.BetterComponents.BetterInputField;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CustomDocumentListener implements DocumentListener {
    private final BetterInputField t;
    public CustomDocumentListener(BetterInputField parent) {
        this.t = parent;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateText();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateText();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateText();
    }
    private void updateText()
    {
        this.t.getMethod().performMethod(this.t.getText());
    }
}
