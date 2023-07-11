package Schnitstelle;

import Bank.Nutzer.Angestellter;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterInputField;
import GUI.UIs.UIButtonMethod;
import GUI.StackedWidget.PAGES;
import GUI.StackedPane.COMPONENTS;
import static Schnitstelle.Main.nutzerDB;
public class AdminSettingsPage {
    private static BetterInputField parseTextField(COMPONENTS c) {
        return (BetterInputField) widget.getElement(PAGES.ADMIN_REGISTER_PAGE,c);
    }
    private static final MainWindow mw = WindowManager.getWindow();
    private static final StackedWidget widget = mw.getWindow();

    private static final BetterInputField vornameInputField = parseTextField(COMPONENTS.VORNAME_INPUT);
    private static final BetterInputField nachNameInputField = parseTextField(COMPONENTS.NACHNAME_INPUT);
    private static final BetterInputField emailInputField = parseTextField(COMPONENTS.EMAIL_INPUT);
    private static final BetterInputField pinInputField = parseTextField(COMPONENTS.PIN_INPUT);
    private static final BetterInputField confirmPinInputField = parseTextField(COMPONENTS.CONFIRM_PIN_INPUT);
    private static final BetterButton confirmButton = (BetterButton) widget.getElement(PAGES.ADMIN_REGISTER_PAGE,COMPONENTS.REGISTER_BUTTON);

    public static void __init__() {
        confirmButton.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                String vorname = vornameInputField.getText();
                String nachname = vornameInputField.getText();
                String email = emailInputField.getText();
                String pin = pinInputField.getText();
                String cpin = confirmPinInputField.getText();
                if (vorname.equals("")||nachname.equals("")||email.equals("")||pin.equals("")||cpin.equals("")) {
                    PopUp.showError("Alle Daten müssen ausgefüllt sein!");
                    return;
                }
                if (!pin.equals(cpin)) {
                    PopUp.showError("Pin stimmt nicht überein");
                    return;
                }
                set s = validatePin(pin);
                if (!s.response) {
                    PopUp.showError(s.message);
                    return;
                }
                Angestellter a = new Angestellter(vorname+" "+nachname,email,pin);
                nutzerDB.NutzerHinzufügen(a);
                PopUp.showInfo("Neuer Account wurde erstellt.");
            }
            private static set validatePin(String pin) {
                boolean valid = true;
                String message = "";
                if (pin.length() < 8) {
                    valid = false;
                    message = "PIN muss mindestens 8 Zeichen lang sein.";
                    return new set(valid, message);
                }
                valid = false;
                message = "PIN muss einen Großbuchstaben enthalten.";
                for (int i=0;i<pin.length();i++) {
                    if ("ABCDEFGHIJKLMNOPQRSTUVWXYZÄÜÖ".contains(""+pin.charAt(i))) {
                        valid = true;
                    }
                }
                if (!valid) {
                    return new set(valid, message);
                }
                valid = false;
                message = "PIN muss eine Zahl enthalten";
                for (int i=0;i<pin.length();i++) {
                    if ("0123456789".contains(""+pin.charAt(i))) {
                        valid = true;
                    }
                }
                if (!valid) {
                    return new set(valid, message);
                }
                valid = false;
                message = "PIN muss eine Sonderzeichen ( !§$%&/()=,.-;:_#*+~@€ ) enthalten";
                for (int i=0;i<pin.length();i++) {
                    if ("!§$%&/()=,.-;:_#*+~@€".contains(""+pin.charAt(i))) {
                        valid = true;
                    }
                }
                return new set(valid, message);
            }
        });
    }
}
class set {
    public boolean response;
    public String message;
    public set(boolean response, String message) {
        this.response = response;
        this.message = message;
    }
}