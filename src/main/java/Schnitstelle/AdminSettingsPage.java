package Schnitstelle;

import Bank.Konten.Konto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterInputField;
import GUI.UIs.UIButtonMethod;
import GUI.StackedWidget.PAGES;
import GUI.StackedPane.COMPONENTS;

import java.util.List;

import static Schnitstelle.Main.*;
import static Schnitstelle.Main.kunde;

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
                if(eingabe.size() == 4 && !eingabe.get(0).equals("") && !eingabe.get(1).equals("") && !eingabe.get(2).equals("") && !eingabe.get(3).equals("")){
                    String username = eingabe.get(0);
                    String mail = eingabe.get(1);
                    String pin = eingabe.get(2);
                    String pinConfirm = eingabe.get(3);
                    if(pin.equals(pinConfirm)){
                        if(!nutzerDB.MailBelegt(mail)){
                            Angestellter angestellter = new Angestellter(username, mail, pin);
                            nutzerDB.NutzerHinzufügen(angestellter);

                            PopUp.showInfo("<html>Du hast einen neuen Angestellten<br> EMail: '" + mail + "' - Pin: '" + pin + "'<br>erstellt</html>");
                        }
                        else{
                            PopUp.showError("Diese Mail ist bereits belegt.");
                        }
                    }
                    else{
                        PopUp.showError("Die PINs stimmen nicht überein.");
                    }
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
                    if (nutzer.getPin().equals(passwort)) {
                        nutzer.NameÄndern(newName);

                        nutzerDB.NameÄndern(nutzer.getEMail(), newName);

                        AdminMainPage.setNameParam();

                        PopUp.showInfo("Du hast deinen Namen auf '" + newName + "' geändert.");
                    } else {
                        PopUp.showError("<html><center>Zugriff nicht gewährt.<br>Die PIN ist falsch.</center></html>");
                    }
                }
            }
        });
        BSaAP.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpPinÄndern(mw.getWindow().getFrame(PAGES.ADMIN_SETTINGS_PAGE));
                if(eingabe.size() == 3 && !eingabe.get(0).equals("") && !eingabe.get(1).equals("") && !eingabe.get(2).equals("")){
                    String oldPin = eingabe.get(0);
                    String newPin = eingabe.get(1);
                    String confirmPin = eingabe.get(2);
                    if(oldPin.equals(nutzer.getPin())){
                        if(newPin.equals(confirmPin)){
                            nutzer.PinÄndern(newPin);
                            nutzerDB.PinÄndern(nutzer.getEMail(), nutzer.getPin());

                            PopUp.showInfo("Du hast deine PIN geändert.");
                        }
                        else{
                            PopUp.showError("Die neue PIN wurde falsch bestätigt.");
                        }
                    }
                    else{
                        PopUp.showError("Die alte PIN ist falsch.");
                    }
                }
            }
        });
        BSaAD.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpAccountLöschen(mw.getWindow().getFrame(PAGES.ADMIN_SETTINGS_PAGE));
                if(eingabe.size() == 2 && !eingabe.get(0).equals("") && !eingabe.get(1).equals("")){
                    String pin1 = eingabe.get(0);
                    String pin2 = eingabe.get(1);
                    if(nutzer.getPin().equals(pin1) && pin1.equals(pin2)){
                        System.out.println("Der Angestellte '" + nutzer.getEMail() + "' wird gelöscht.");
                        nutzerDB.NutzerLöschen(nutzer.getEMail());
                        PopUp.showInfo("Du hast deinen Account (" + nutzer.getEMail() + ") gelöscht.");

                        mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
                    }
                    else{
                        PopUp.showError("<html><center>Eine der PINs ist falsch.</center></html>");
                    }
                }

            }
        });
    }
}