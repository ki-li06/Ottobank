package GUI;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class StackedPane extends JPanel{
    private ArrayList<JComponent> comps;
    private ArrayList<String> keys;
    private int w;
    private int h;
    private Dimension size;
    private StackedWidget parent;
    private boolean shown;
    public StackedPane(StackedWidget parent) {
        super();
        comps = new ArrayList<JComponent>();
        keys = new ArrayList<String>();
        this.size = parent.getSize();
        this.parent = parent;
        this.setBounds(new Rectangle(new Point(0,0), this.size));
        shown = false;
    }
    public StackedPane(StackedWidget parent, Dimension size) {
        super();
        comps = new ArrayList<JComponent>();
        keys = new ArrayList<String>();
        this.size = size;
        this.parent = parent;
        this.setBounds(new Rectangle(new Point(0,0), this.size));
        shown = false;
    }
    public void registerComponent(JComponent c) {
        registerComponent(c, "ANONYMOUS");
    }
    public JComponent getElement(String key) {
        for (int i=0;i<keys.size();i++) {
            if (keys.get(i) == key) {
                return comps.get(i);
            }
        }
        return null;
    }
    public void registerComponent(JComponent c, String key) {
        add(c);
        comps.add(c);
        c.setVisible(shown);
        keys.add(key);
    }
    private void refresh() {
        for (int i=0;i<comps.size();i++) {
            comps.get(i).setVisible(shown);
        }
    }
    public void show() {
        shown = true;
        refresh();
    }
    public void hide() {
        shown = false;
        refresh();
    }
    public boolean getShown() {
        return shown;
    }
}
