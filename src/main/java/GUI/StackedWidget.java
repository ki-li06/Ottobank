package GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

import static GUI.StackedPane.COMPONENTS;

public class StackedWidget extends JLayeredPane{
    public enum PAGES{
        ANONYMOUS,
        LOGIN_PAGE,
        REGISTER_PAGE,
        SETTINGS_PAGE,
        MAIN_PAGE,
        ADMIN_REGISTER_PAGE,
        ADMIN_MAIN_PAGE,
        PRIVATE
    }

    private final ArrayList<StackedPane> comps;
    private final ArrayList<PAGES> keys;

    private final JFrame parent;
    public StackedWidget(JFrame parent) {
        super();
        setLayout(null);
        comps = new ArrayList<>();
        keys = new ArrayList<>();
        this.parent = parent;
    }

    public void registerPane(StackedPane element) {
        registerPane(element, PAGES.ANONYMOUS);
    }
    public void registerPane(StackedPane element, PAGES key) {
        int z = comps.size();
        comps.add(element);
        keys.add(key);
        add(element, z);
    }

    public StackedPane getFrame(PAGES key) {
        if (key == PAGES.PRIVATE) {
            return null;
        }
        for (int i=0;i < keys.size();i++) {
            if (keys.get(i) == key) {
                return comps.get(i);
            }
        }
        return null;
    }
    public JComponent getElement(PAGES page, COMPONENTS component) {
        /*if (keys.length != 2) {
            throw new ArrayIndexOutOfBoundsException("INVALID keypair");
        }*/
        try {
            return getFrame(page).getElement(component);
        } catch (NullPointerException e) {
            return null;
        }
    }
    public int getPlaneIndex(PAGES key) {

        for (int i=0;i<keys.size();i++) {
            if (keys.get(i) == key) {
                return i;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Requested Plane does not exist.");
    }

    public void showPlane(PAGES key) {
        showPlane(getPlaneIndex(key));
    }

    public void showPlane(int index) {
        if (index < 0 || index >= comps.size()) {
            throw new ArrayIndexOutOfBoundsException("Plane does not exist");
        }
        if (comps.get(index).getShown()) {
            return;
        }
        for (int i=0;i< comps.size(); i++) {
            if (i != index && comps.get(i).getShown()) {
                comps.get(i).hidePage();
            }
        }
        parent.setSize(comps.get(index).getSize());
        setSize(comps.get(index).getSize());
        comps.get(index).showPage();
        setComponentZOrder(comps.get(index),0);
    }
}
