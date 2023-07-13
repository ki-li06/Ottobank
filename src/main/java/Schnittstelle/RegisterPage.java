package Schnittstelle;

import Bank.Nutzer.Kunde;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterInputField;
import GUI.UIs.UIButtonMethod;

import static Schnittstelle.Main.mw;
import static Schnittstelle.Main.nutzerDB;
import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;

public class RegisterPage {
    public static final BetterInputField IRV = (BetterInputField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.VORNAME_INPUT);
    public static final BetterInputField IRN = (BetterInputField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.NACHNAME_INPUT);
    public static final BetterInputField IRE = (BetterInputField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.EMAIL_INPUT);
    public static final BetterInputField IRP = (BetterInputField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.PIN_INPUT);
    public static final BetterInputField IRC = (BetterInputField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.CONFIRM_PIN_INPUT);
    public static final BetterButton BRR = (BetterButton) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.REGISTER_BUTTON);


    public static void InitializeRegisterButton() {
        BRR.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod () {
                //Nachname darf leer sein, Vorname wird sozusagen mehr als Nutzername verstanden
                if (IRP.getToolTipText().contains("'") || IRE.getText().contains("'")  || IRV.getText().contains("'")) {
                    PopUp.showError("Das Zeichen \"'\" darf nicht verwendet werden.");
                    return;
                }
                if(IRV.getText().equals("") || IRE.getText().equals("") || IRP.getText().equals("")){
                    PopUp.showError("Alle Eingaben müssen ausgefüllt sein!");
                }
                if (!IRP.getText().equals(IRC.getText())) {
                    PopUp.showError("PIN stimmt nicht überein!");
                    return;
                }
                String name = IRV.getText() + " " + IRN.getText();
                String mail = IRE.getText();
                if(nutzerDB.MailBelegt(mail)){
                    PopUp.showError("Diese Mail ist bereits belegt.");
                }
                Kunde k = new Kunde(name, mail, IRP.getText());
                nutzerDB.NutzerHinzufügen(k);
                PopUp.showInfo("Neuer Account wurde erstellt.");
                mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
            }
        });
    }
}
