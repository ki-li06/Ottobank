package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import com.formdev.flatlaf.FlatDarkLaf;
public class StackedWidget extends JLayeredPane{
    private ArrayList<StackedPane> comps;
    private ArrayList<String> keys;
    private JFrame parent;
    public StackedWidget(JFrame parent) {
        super();
        setLayout(null);
        comps = new ArrayList<StackedPane>();
        keys = new ArrayList<String>();
        this.parent = parent;
    }
    public void registerPane(StackedPane element) {
        registerPane(element, "ANONYMOUS");
    }
    public void registerPane(StackedPane element, String key) {
        int z = comps.size();
        comps.add(element);
        keys.add(key);
        add(element, z);
    }
    public StackedPane getFrame(String key) {
        if (key == "PRIVATE") {
            return null;
        }
        for (int i=0;i<keys.size();i++) {
            if (keys.get(i) == key) {
                return comps.get(i);
            }
        }
        return null;
    }
    public int getPlaneIndex(String key) {
        for (int i=0;i<keys.size();i++) {
            if (keys.get(i) == key) {
                return i;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Requested Plane does not exist.");
    }
    public void showPlane(int index) {
        if (index < 0 || index >= comps.size()) {
            throw new ArrayIndexOutOfBoundsException("Plane does not exist");
        }
        if (comps.get(index).getShown() != false) {
            return;
        }
        for (int i=0;i<comps.size(); i++) {
            if (i != index) {
                if (comps.get(i).getShown() == true) {
                    comps.get(i).hidePage();

                }
            }
        }
        parent.setSize(comps.get(index).getSize());
        setSize(comps.get(index).getSize());
        comps.get(index).showPage();
        setComponentZOrder(comps.get(index),0);
    }
}
