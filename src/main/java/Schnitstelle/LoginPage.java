package Schnitstelle;

import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import GUI.*;

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
                    if (nutzer.getType().equals(Nutzer.TYPE.KUNDE)) {
                        mw.getWindow().showPlane(PAGES.MAIN_PAGE);
                        kunde = (Kunde) nutzer;

                        MainPage.setName(kunde);
                        MainPage.setAktuellesKonto(0);
                        MainPage.setKontenListe();



                    } else {
                        mw.getWindow().showPlane(PAGES.ADMIN_MAIN_PAGE);
                    }
                } else {
                    PopUp.showError("Passwort falsch!");
                }

            }
        });
    }
}
