package Schnitstelle;

import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterInputField;
import GUI.BetterComponents.BetterTextField;
import GUI.UIs.UIButtonMethod;

import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;
import static Schnitstelle.Main.*;

public class AdminMainPage {
    public static final BetterTextField TMaN = (BetterTextField) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.NAME_LABEL_PARAM);
    public static final BetterInputField IMaM = (BetterInputField) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.USER_NAME_INPUT);
    public static final BetterButton BMaI = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGIN_BUTTON);
    public static final BetterButton BMaO = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGOUT_BUTTON);

    //public static final BetterButton BMS = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.SETTINGS_BUTTON);

    public static void InitializeLoginButton(){
        BMaI.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                String text = IMaM.getText();
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

                    PopUp.showInfo("Die PIN dieses Nutzers lautet: '" + kunde.getPin() + "'");

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

    public static void setNameParam(){
        TMaN.setText(nutzer.getName());
    }

    public static void setAbmeldenButtonVoid() {
        BMaO.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                IMaM.setText("");

                mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
            }
        });
    }
}
