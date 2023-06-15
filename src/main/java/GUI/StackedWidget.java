package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import com.formdev.flatlaf.FlatDarkLaf;
public class StackedWidget extends JComponent{
    private JLayeredPane pane;
    private  Dimension size;
    public StackedWidget(int width, int height) {
        super();
        this.pane = new JLayeredPane();
        this.size = new Dimension(width,height);
        this.pane.setPreferredSize(this.size);
    }
    public void addComponent(JComponent c) {
        this.addComponent(c,0);
    }
    public void addComponent(JComponent c, int zidx) {

    }

}
