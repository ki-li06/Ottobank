package GUI;

import javax.swing.JButton;

public class BetterButton extends JButton {
    private UIButtonMethod method;
    public BetterButton() {
        super();
        this.method = new UIButtonMethod() {
            @Override
            public void performMethod() {
                return;
            }
        };
        this.addActionListener(new CustomActionListener(this));

    }
    public BetterButton(UIButtonMethod method) {
        super();
        if (method == null) {
            this.method = new UIButtonMethod() {
                @Override
                public void performMethod() {
                    return;
                }
            };
        } else {
            this.method = method;
        }
        this.addActionListener(new CustomActionListener(this));
    }
    public void addMethod(UIButtonMethod method) {
        if (method == null) {
            return;
        } else {
            this.method = method;
        }
    }
    public UIButtonMethod getMethod() {
        return this.method;
    }
}
