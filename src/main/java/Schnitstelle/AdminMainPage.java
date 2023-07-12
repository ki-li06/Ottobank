package Schnitstelle;

import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterComboBox;
import GUI.BetterComponents.BetterInputField;
import GUI.BetterComponents.BetterTextField;
import GUI.UIs.UIButtonMethod;

import java.util.ArrayList;
import java.util.List;

import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;
import static Schnitstelle.Main.*;

public class AdminMainPage {
    public static final BetterTextField TMaN = (BetterTextField) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.NAME_LABEL_PARAM);
    public static final BetterComboBox IMaM = (BetterComboBox) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.USER_NAME_INPUT);
    public static final BetterButton BMaI = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGIN_BUTTON);
    public static final BetterButton BMaO = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGOUT_BUTTON);

    //public static final BetterButton BMS = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.SETTINGS_BUTTON);

    public static void InitializeLoginButton(){
        BMaI.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                String text = IMaM.getSelectedValue();
                if(text.equals("-----")){
                    PopUp.showError("<html>Es existieren keine Kunden,<br>bitte erst ein Konto registrieren.</html>");
                    return;
                }
                kunde = (Kunde) nutzerDB.NutzerZuMail(text);
                mw.getWindow().showPlane(PAGES.MAIN_PAGE);

                MainPage.setName(kunde);
                MainPage.setKontenListe();
                MainPage.setAktuellesKonto(0);
                MainPage.setButtonsVoid();

                PopUp.showInfo("<html>Die PIN des Nutzers '" + kunde.getEMail() + "'<br>ist '" + kunde.getPin() + "'</html>");
            }
        });
    }

    public static void setNameParam(){
        TMaN.setText(nutzer.getName());
    }
    public static void setKundenList(){
        List<Nutzer> user = new ArrayList<>(nutzerDB.alleNutzerGeben());
        user.removeIf(i->i instanceof Angestellter);
        System.out.println("user: " + user);
        if(user.size() > 0) {
            IMaM.setElements(user.stream().map(Nutzer::getEMail).toArray(String[]::new));
        }
        else {
            IMaM.setElements(new String[]{"-----"});
            PopUp.showError("<html>Es existieren keine Kunden,<br>bitte erst ein Konto registrieren.</html>");
        }
    }

    public static void setAbmeldenButtonVoid() {
        BMaO.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
            }
        });
    }
}
