package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import com.formdev.flatlaf.FlatDarkLaf;
public class StackedWidget extends JLayeredPane{
    private ArrayList<StackedPane> comps;
    public StackedWidget() {
        super();
        setLayout(null);
        comps = new ArrayList<StackedPane>();
    }
    public void registerPane(StackedPane element) {
        int z = comps.size();
        comps.add(element);
        add(element, z);
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
                    comps.get(i).hide();
                }
            }
        }
        comps.get(index).show();
    }
}
