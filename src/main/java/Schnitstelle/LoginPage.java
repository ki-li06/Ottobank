package Schnitstelle;

import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterInputField;
import GUI.UIs.UIButtonMethod;

import static Schnitstelle.Main.*;
import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;

public class LoginPage {
    public static BetterInputField ILE = (BetterInputField) mw.getWindow().getFrame(PAGES.LOGIN_PAGE).getElement(COMPONENTS.EMAIL_INPUT);
    public static BetterInputField ILP = (BetterInputField) mw.getWindow().getFrame(PAGES.LOGIN_PAGE).getElement(COMPONENTS.PIN_INPUT);
    public static BetterButton BLL = (BetterButton) mw.getWindow().getFrame(PAGES.LOGIN_PAGE).getElement(COMPONENTS.LOGIN_BUTTON);

    public static void InitializeLoginButton(){
        BLL.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                //System.out.println("email: '" + TLE.getText() + "'");
                //System.out.println("text : '" + TLP.getText() + "'");
                if (nutzerDB.PasswortRichtig(ILE.getText(), ILP.getText())) {
                    nutzer = nutzerDB.NutzerZuMail(ILE.getText());

                    ILE.setText("");
                    ILP.setText("");

                    if (nutzer.getType().equals(Nutzer.TYPE.KUNDE)) {
                        mw.getWindow().showPlane(PAGES.MAIN_PAGE);
                        kunde = (Kunde) nutzer;

                        ILE.setText("");
                        ILP.setText("");

                        MainPage.setName(kunde);
                        MainPage.setKontenListe();
                        MainPage.setAktuellesKonto(0);
                        MainPage.setEinzahlenButtonVoid();
                        MainPage.setAbhebenButtonVoid();
                        MainPage.setAbmeldenButtonVoid();

                        SettingsPage.InitializeButtons();

                    } else {
                        mw.getWindow().showPlane(PAGES.ADMIN_MAIN_PAGE);
                        AdminMainPage.setAbmeldenButtonVoid();
                    }
                } else {
                    PopUp.showError("Passwort falsch!");
                }

            }
        });
    }
}
