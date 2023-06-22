package GUI;
import javax.swing.*;

public class BetterComboBox extends JComboBox<String> {
    private UIComboBoxMethod method;
    public BetterComboBox(String[] stringlist) {
        super(stringlist);
        this.method = new UIComboBoxMethod() {
            @Override
            public void performMethod(String data) {
                return;
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
                    return;
                }
            };
        } else {
            this.method = method;
        }
        this.addActionListener(new CustomActionListener(this));
    }
    public void addMethod(UIComboBoxMethod method) {
        if (method == null) {
            return;
        } else {
            this.method = method;
        }
    }
    public UIComboBoxMethod getMethod() {
        return this.method;
    }
}
