package GUI;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.util.Objects;

import static GUI.StackedWidget.PAGES;


public class StackedPane extends JPanel{
    public enum COMPONENTS{
        ANONYMOUS, PRIVATE,
        EMAIL_INPUT, PIN_INPUT, LOGIN_BUTTON,
        VORNAME_INPUT, NACHNAME_INPUT, CONFIRM_PIN_INPUT, REGISTER_BUTTON,
        SETTINGS_BUTTON, KONTO_WECHSEL_BOX, NAME_LABEL_FIX, NAME_LABEL_PARAM, KONTONUMMER_FIX,
            KONTONUMMER_PARAM, KONTOSTAND_FIX, KONTOSTAND_PARAM,
            DEPOSIT_BUTTON, TRANSACTION_BUTTON, WITHDRAW_BUTTON,
        ACCOUNT_DELETE_CHOICE, ACCOUNT_DELETE_BUTTON, ACCOUNT_CREATE_BUTTON,
            ACCOUNT_CREATE_INPUT, NEW_PIN_INPUT, SAVE_BUTTON,
        USER_NAME
    }

    private final ArrayList<JComponent> comps;
    private final ArrayList<COMPONENTS> keys;
    private final Dimension size;
    private final StackedWidget parent;
    private boolean shown;
    public StackedPane(StackedWidget parent) {
        super();
        comps = new ArrayList<JComponent>();
        keys = new ArrayList<>();
        this.size = parent.getSize();
        this.parent = parent;
        this.setBounds(new Rectangle(new Point(0,0), this.size));
        shown = false;
        setLayout(null);
    }
    public StackedPane(StackedWidget parent, Dimension size) {
        super();
        comps = new ArrayList<JComponent>();
        keys = new ArrayList<>();
        this.size = size;
        this.parent = parent;
        this.setBounds(new Rectangle(new Point(0,0), this.size));
        shown = false;
        setLayout(null);
    }
    @Deprecated
    public void registerComponent(JComponent c) {
        registerComponent(c, COMPONENTS.ANONYMOUS);
    }
    public JComponent getElement(COMPONENTS key) {
        if (key == COMPONENTS.PRIVATE) {
            return null;
        }
        for (int i=0;i<keys.size();i++) {
            if (Objects.equals(keys.get(i), key)) {
                return comps.get(i);
            }
        }
        return null;
    }
    void registerComponent(JComponent c, COMPONENTS key) {
        add(c);
        comps.add(c);
        c.setVisible(shown);
        keys.add(key);
    }
    private void refresh() {
        for (JComponent comp : comps) {
            comp.setVisible(shown);
        }
    }
    void showPage() {
        shown = true;
        refresh();
    }
    void hidePage() {
        shown = false;
        refresh();
    }
    public boolean getShown() {
        return shown;
    }
}
