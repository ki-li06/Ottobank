package GUI;

import javax.swing.*;

public class PopUp {
    private static void showMessage(String message,int type) {
        String[] errors = new String[] {"ERROR","NULL","WARNING"};
        String title = "???";
        try {
            title = errors[type];
        } catch (ArrayIndexOutOfBoundsException e) {}

        JOptionPane.showMessageDialog(null, message, title, type);
    }
    public static void showError(String message) {
        showMessage(message, JOptionPane.ERROR_MESSAGE);
    }
    public static void showWarning(String message) {
        showMessage(message, JOptionPane.WARNING_MESSAGE);
    }
}
