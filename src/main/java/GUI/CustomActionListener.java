package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomActionListener implements ActionListener {
    private BetterButton parent;
    CustomActionListener(BetterButton parent) {
        this.parent = parent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.parent.getMethod().performMethod();
    }
}
