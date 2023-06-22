package GUI;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.util.Objects;

public class StackedPane extends JPanel{
    private final ArrayList<JComponent> comps;
    private final ArrayList<String> keys;
    private final Dimension size;
    private final StackedWidget parent;
    private boolean shown;
    public StackedPane(StackedWidget parent) {
        super();
        comps = new ArrayList<JComponent>();
        keys = new ArrayList<String>();
        this.size = parent.getSize();
        this.parent = parent;
        this.setBounds(new Rectangle(new Point(0,0), this.size));
        shown = false;
        setLayout(null);
    }
    public StackedPane(StackedWidget parent, Dimension size) {
        super();
        comps = new ArrayList<JComponent>();
        keys = new ArrayList<String>();
        this.size = size;
        this.parent = parent;
        this.setBounds(new Rectangle(new Point(0,0), this.size));
        shown = false;
        setLayout(null);
    }
    @Deprecated
    public void registerComponent(JComponent c) {
        registerComponent(c, "ANONYMOUS");
    }
    public JComponent getElement(String key) {
        if (Objects.equals(key, "PRIVATE")) {
            return null;
        }
        for (int i=0;i<keys.size();i++) {
            if (Objects.equals(keys.get(i), key)) {
                return comps.get(i);
            }
        }
        return null;
    }
    void registerComponent(JComponent c, String key) {
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
