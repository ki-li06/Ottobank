package Schnitstelle;

import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterInputField;
import GUI.UIs.UIButtonMethod;

import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;
import static Schnitstelle.Main.*;

public class AdminMainPage {
    public static final BetterInputField IMAN = (BetterInputField) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.USER_NAME_INPUT);
    public static final BetterButton BMI = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGIN_BUTTON);
    public static final BetterButton BMO = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGOUT_BUTTON);

    public static void InitializeLoginButton(){
        BMI.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                String text = IMAN.getText();
                if(text.equals("")){
                    PopUp.showError("Alle Eingaben müssen ausgefüllt sein!");
                }
                else if(!nutzerDB.MailBelegt(text)){
                    PopUp.showError("Diese EMail ist nicht belegt!");
                }
                else if(nutzerDB.NutzerZuMail(text) instanceof Angestellter){
                    PopUp.showError("Diese EMail gehört einem Angestelltem.");
                }
                else{
                    kunde = (Kunde) nutzerDB.NutzerZuMail(text);
                    mw.getWindow().showPlane(PAGES.MAIN_PAGE);

                    MainPage.setName(kunde);
                    MainPage.setKontenListe();
                    MainPage.setAktuellesKonto(0);
                    MainPage.setEinzahlenButtonVoid();
                    MainPage.setAbhebenButtonVoid();
                    MainPage.setAbmeldenButtonVoid();
                }
            }
        });
    }

    public static void setAbmeldenButtonVoid() {
        BMO.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                IMAN.setText("");

                mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
            }
        });
    }
}
