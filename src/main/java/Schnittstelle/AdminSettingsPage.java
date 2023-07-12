package Schnittstelle;

import Bank.Nutzer.Angestellter;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.UIs.UIButtonMethod;
import GUI.StackedWidget.PAGES;
import GUI.StackedPane.COMPONENTS;

import java.util.List;

import static Schnittstelle.Main.*;

public class AdminSettingsPage {
    public static final BetterButton BSaCA = (BetterButton) mw.getWindow().getElement(PAGES.ADMIN_SETTINGS_PAGE, COMPONENTS.ADMIN_CREATE_BUTTON);
    public static final BetterButton BSaAN = (BetterButton) mw.getWindow().getElement(PAGES.ADMIN_SETTINGS_PAGE, COMPONENTS.ACCOUNT_CHANGE_NAME_BUTTON);
    public static final BetterButton BSaAP = (BetterButton) mw.getWindow().getElement(PAGES.ADMIN_SETTINGS_PAGE, COMPONENTS.ACCOUNT_CHANGE_PIN_BUTTON);
    public static final BetterButton BSaAD = (BetterButton) mw.getWindow().getElement(PAGES.ADMIN_SETTINGS_PAGE, COMPONENTS.ACCOUNT_DELETE_BUTTON);

    public static void InitializeButtons(){
        BSaCA.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpAdminRegisterPage(mw.getWindow().getFrame(PAGES.ADMIN_SETTINGS_PAGE));
                if(eingabe.size() == 4 && !eingabe.contains("")){
                    String username = eingabe.get(0);
                    String mail = eingabe.get(1);
                    String pin = eingabe.get(2);
                    String pinConfirm = eingabe.get(3);
                    if(!pin.equals(pinConfirm)){
                        PopUp.showError("Die PINs stimmen nicht überein.");
                        return;
                    }
                    if(nutzerDB.MailBelegt(mail)){
                        PopUp.showError("Diese Mail ist bereits belegt.");
                        return;
                    }
                    Angestellter angestellter = new Angestellter(username, mail, pin);
                    nutzerDB.NutzerHinzufügen(angestellter);

                    PopUp.showInfo("<html>Du hast einen neuen Angestellten erstellt:<br> E-Mail: '" + mail + "' - PIN: '" + pin + "'</html>");
                }
            }
        });
        BSaAN.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpNameÄndern(mw.getWindow().getFrame(PAGES.ADMIN_SETTINGS_PAGE));
                if(eingabe.size() == 2 && !eingabe.get(0).equals("")){
                    String newName = eingabe.get(0);
                    //System.out.println("newName: " + newName);
                    String passwort = eingabe.get(1);
                    //System.out.println("passwort: " + passwort);
                    if(!nutzer.getPin().equals(passwort)){
                        PopUp.showError("<html><center>Zugriff nicht gewährt.<br>Die PIN ist falsch.</center></html>");
                        return;
                    }
                    nutzer.NameÄndern(newName);

                    nutzerDB.NameÄndern(nutzer.getEMail(), newName);

                    AdminMainPage.setNameParam();

                    PopUp.showInfo("Du hast deinen Namen auf '" + newName + "' geändert.");
                }
            }
        });
        BSaAP.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpPinÄndern(mw.getWindow().getFrame(PAGES.ADMIN_SETTINGS_PAGE));
                if(eingabe.size() == 3 && !eingabe.contains("")){
                    String oldPin = eingabe.get(0);
                    String newPin = eingabe.get(1);
                    String confirmPin = eingabe.get(2);
                    if(!oldPin.equals(nutzer.getPin())){
                        PopUp.showError("Die alte PIN ist falsch.");
                        return;
                    }
                    if(!newPin.equals(confirmPin)){
                        PopUp.showError("Die neue PIN wurde falsch bestätigt.");
                        return;
                    }
                    nutzer.PinÄndern(newPin);
                    nutzerDB.PinÄndern(nutzer.getEMail(), nutzer.getPin());

                    PopUp.showInfo("Du hast deine PIN geändert.");
                }
            }
        });
        BSaAD.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpAccountLöschen(mw.getWindow().getFrame(PAGES.ADMIN_SETTINGS_PAGE));
                if(eingabe.size() == 2 && !eingabe.contains("")){
                    String pin1 = eingabe.get(0);
                    String pin2 = eingabe.get(1);
                    if(!nutzer.getPin().equals(pin1) && pin1.equals(pin2)){
                        PopUp.showError("<html><center>Eine der PINs ist falsch.</center></html>");
                        return;
                    }
                    if(nutzerDB.alleNutzerGeben().stream().mapToInt(i->(i instanceof Angestellter)?1:0).sum() == 1){
                        PopUp.showError("<html><center>Du bist der einzige Angestellte,<br>du kannst dich nicht löschen.");
                        return;
                    }
                    System.out.println("Der Angestellte '" + nutzer.getEMail() + "' wird gelöscht.");
                    nutzerDB.NutzerLöschen(nutzer.getEMail());
                    PopUp.showInfo("Du hast deinen Account (" + nutzer.getEMail() + ") gelöscht.");

                    mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
                }

            }
        });
    }
}