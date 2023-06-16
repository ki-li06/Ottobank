package GUI;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class StackedPane extends JPanel{
    private ArrayList<JComponent> comps;
    private int w;
    private int h;
    private boolean shown;
    public StackedPane(JFrame parent) {
        super();
        int h = parent.getHeight();
        int w = parent.getWidth();
        comps = new ArrayList<JComponent>();
        this.setBounds(0,0,w,h);
        shown = false;
    }
    public void registerComponent(JComponent c) {
        add(c);
        comps.add(c);
        c.setVisible(shown);
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
