package GUI.Listeners;

import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterComboBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomActionListener implements ActionListener {
    private final JComponent parent;
    public CustomActionListener(BetterButton parent) {
        this.parent = parent;
    }
    public CustomActionListener(BetterComboBox parent) {
        this.parent = parent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.parent.getClass().equals(BetterButton.class)) {
            ((BetterButton) this.parent).getMethod().performMethod();
        }
        if (this.parent.getClass().equals(BetterComboBox.class)) {
            int index = ((BetterComboBox) this.parent).getSelectedIndex();
            ((BetterComboBox) this.parent).getMethod().performMethod(((BetterComboBox) this.parent).getItemAt(index));
        }

    }
}
