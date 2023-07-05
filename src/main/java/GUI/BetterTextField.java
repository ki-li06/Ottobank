package GUI;

import javax.swing.*;

public class BetterTextField extends JPasswordField {
    private UITextFieldMethod method;
    public BetterTextField() {
        super();
        this.method = new UITextFieldMethod() {
            @Override
            public void performMethod(String data) {
            }
        };
        afterConstruct();
    }
    public BetterTextField(UITextFieldMethod method) {
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
