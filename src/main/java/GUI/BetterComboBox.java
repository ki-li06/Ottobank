package GUI;
import javax.swing.*;

public class BetterComboBox extends JComboBox {
    private UIComboBoxMethod method;
    public BetterComboBox() {
        super();
        this.method = new UIComboBoxMethod() {
            @Override
            public void performMethod(String data) {
                return;
            }
        };
        this.addActionListener(new CustomActionListener(this));

    }
    public BetterComboBox(UIComboBoxMethod method) {
        super();
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
