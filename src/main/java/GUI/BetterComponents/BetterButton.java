package GUI.BetterComponents;

import GUI.Listeners.CustomActionListener;
import GUI.UIs.UIButtonMethod;

import javax.swing.JButton;

/**
 * Extended Button class. Easier support for method binding.\n
 * Usage:\n
 * .addMethod(new UIButtonMethod() {\n
 *      \@Override\n
 *      public void performMethod() {\n
 *          DO_STUFF_HERE\n
 *      }\n
 * });\n
 * UIButtonMethod is an interface that can be implemented into a class\n
 * performMethod is called every time, the button is pressed.
 */
public class BetterButton extends JButton {
    private UIButtonMethod method;
    public BetterButton() {
        super();
        this.method = new UIButtonMethod() {
            @Override
            public void performMethod() {
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
                }
            };
        } else {
            this.method = method;
        }
        this.addActionListener(new CustomActionListener(this));
    }
    public void addMethod(UIButtonMethod method) {
        if (method != null) {
            this.method = method;
        }
    }
    public UIButtonMethod getMethod() {
        return this.method;
    }
}
