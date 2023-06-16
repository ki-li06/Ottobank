package GUI;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class CustomDocumentListener implements DocumentListener {
    private BetterTextField t;
    CustomDocumentListener(BetterTextField parent) {
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
