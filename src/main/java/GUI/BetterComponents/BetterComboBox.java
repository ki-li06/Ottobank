package GUI.BetterComponents;
import GUI.Listeners.CustomActionListener;
import GUI.UIs.UIComboBoxMethod;

import javax.swing.*;

public class BetterComboBox extends JComboBox<String> {
    private UIComboBoxMethod method;
    public BetterComboBox(String[] stringlist) {
        super(stringlist);
        this.method = new UIComboBoxMethod() {
            @Override
            public void performMethod(String data) {
            }
        };
        this.addActionListener(new CustomActionListener(this));

    }
    public BetterComboBox(String[] stringlist, UIComboBoxMethod method) {
        super(stringlist);
        if (method == null) {
            this.method = new UIComboBoxMethod() {
                @Override
                public void performMethod(String data) {
                }
            };
        } else {
            this.method = method;
        }
        this.addActionListener(new CustomActionListener(this));
    }
    public void addMethod(UIComboBoxMethod method) {
        if (method != null) {
            this.method = method;
        }
    }
    public void setElements(String[] elements) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(elements);
        this.setModel(model);
    }
    public UIComboBoxMethod getMethod() {
        return this.method;
    }
    public String getSelectedValue(){
        return getItemAt(getSelectedIndex());
    }
}
