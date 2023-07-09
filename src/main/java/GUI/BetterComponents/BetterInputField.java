package GUI.BetterComponents;

import GUI.Listeners.CustomActionListener;
import GUI.Listeners.CustomDocumentListener;
import GUI.UIs.UITextFieldMethod;

import javax.swing.*;

public class BetterInputField extends JPasswordField {
    private UITextFieldMethod method;
    public BetterInputField() {
        super();
        this.method = new UITextFieldMethod() {
            @Override
            public void performMethod(String data) {
            }
        };
        afterConstruct();
    }
    public BetterInputField(UITextFieldMethod method) {
        if (method == null) {
            this.method = new UITextFieldMethod() {
                @Override
                public void performMethod(String data) {
                }
            };
        } else {
            this.method = method;
        }
        afterConstruct();
    }
    private void afterConstruct() {

        this.getDocument().addDocumentListener(new CustomDocumentListener(this));
    }
    public void addMethod(UITextFieldMethod method) {
        this.method = method;
    }
    public UITextFieldMethod getMethod() {
        return this.method;
    }

}
