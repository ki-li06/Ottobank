package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomActionListener implements ActionListener {
    private JComponent parent;
    CustomActionListener(BetterButton parent) {
        this.parent = parent;
    }
    CustomActionListener(BetterComboBox parent) {
        this.parent = parent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.parent.getClass().equals(BetterButton.class)) {
            ((BetterButton) this.parent).getMethod().performMethod();
        }
        if (this.parent.getClass().equals(BetterComboBox.class)) {
            int index = ((BetterComboBox) this.parent).getSelectedIndex();
            ((BetterComboBox) this.parent).getMethod().performMethod((String) ((BetterComboBox) this.parent).getItemAt(index));
        }

    }
}
