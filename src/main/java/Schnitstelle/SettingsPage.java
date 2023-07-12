package Schnitstelle;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import GUI.BetterComponents.BetterButton;
import GUI.PopUp;
import GUI.UIs.UIButtonMethod;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;
import static Schnitstelle.Main.*;

public class SettingsPage {
    public static final BetterButton BSKC = (BetterButton) mw.getWindow().getElement(PAGES.SETTINGS_PAGE, COMPONENTS.KONTO_CREATE_BUTTON);
    public static final BetterButton BSKD = (BetterButton) mw.getWindow().getElement(PAGES.SETTINGS_PAGE, COMPONENTS.KONTO_DELETE_BUTTON);
    public static final BetterButton BSAN = (BetterButton) mw.getWindow().getElement(PAGES.SETTINGS_PAGE, COMPONENTS.ACCOUNT_CHANGE_NAME_BUTTON);
    public static final BetterButton BSAP = (BetterButton) mw.getWindow().getElement(PAGES.SETTINGS_PAGE, COMPONENTS.ACCOUNT_CHANGE_PIN_BUTTON);
    public static final BetterButton BSAD = (BetterButton) mw.getWindow().getElement(PAGES.SETTINGS_PAGE, COMPONENTS.ACCOUNT_DELETE_BUTTON);

    public static void InitializeButtons(){
        BSKC.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                System.out.println("pressed BSKC");
                String[] types = Konto.getAllTypes().toArray(String[]::new);
                List<String> eingabe = mw.PopUpKontoType(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE), types);
                System.out.println("eingabe: " + eingabe + "(" + eingabe.size() + ")");
                if(eingabe.size() == 1){
                    String type = eingabe.get(0).toUpperCase();
                    if(type.equals(String.valueOf(Konto.TYPE.SPARKONTO))){
                        Sparkonto sk = new Sparkonto(0, kunde);
                        kontenDB.KontoHinzufügen(sk);

                        MainPage.setKontenListe();
                        MainPage.setAktuellesKonto(MainPage.getKontenListe().stream().map(Konto::KontonummerGeben).toList().indexOf(sk.KontonummerGeben()));

                        PopUp.showInfo("<html>Du hast ein neues Sparkonto eingerichtet.</html>");
                    }
                    else if(type.equals(String.valueOf(Konto.TYPE.GIROKONTO))){
                        List<String> nettoeinkommen  = mw.PopUpNettoeinkommen(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE));
                        //System.out.println("list einkommen: " + nettoeinkommen + "(" + nettoeinkommen.size() + ")");
                        if(nettoeinkommen.size() == 1 && !nettoeinkommen.get(0).equals("")){
                            double einkommen = Double.parseDouble(nettoeinkommen.get(0).replace(",", "."));
                            //System.out.println("einkommen: " + einkommen);
                            Girokonto gk = new Girokonto(0, kunde, einkommen);
                            kontenDB.KontoHinzufügen(gk);
                            PopUp.showInfo("<html>Du hast ein neues Girokonto eingerichtet.</html>");

                            MainPage.setKontenListe();
                            MainPage.setAktuellesKonto(MainPage.getKontenListe().stream().map(Konto::KontonummerGeben).toList().indexOf(gk.KontonummerGeben()));

                            PopUp.showInfo("<html>Du hast ein neues Sparkonto eingerichtet.</html>");
                        }
                        else{
                            PopUp.showError("<html>Du musst ein Einkommen angeben.<br>Konto bitte neu erstellen.</html>");
                        }
                    }
                }
            }
        });
        BSKD.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                String[] kontos = MainPage.getKontenListeString();
                if(kontos.length != 0) {
                    List<String> eingabe = mw.PopUpKontoAuswahl(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE), kontos);
                    if (eingabe.size() == 2) {
                        int nummer = Integer.parseInt(eingabe.get(0).split(" ")[2]);
                        Konto k = kontenDB.getKontoVonKontonummer(nummer);
                        System.out.println("k: " + k);
                        String passwort = eingabe.get(1);
                        System.out.println("passwort: " + passwort);
                        if (kunde.getPin().equals(passwort)) {
                            kontenDB.KontoLöschen(nummer);

                            PopUp.showInfo("Du hast dein Konto " + k.getAsStringKurz() + " gelöscht.");

                            MainPage.setKontenListe();
                            MainPage.setAktuellesKonto(0);
                        } else {
                            PopUp.showError("<html><center>Zugriff nicht gewährt.<br>Die PIN ist falsch.</center></html>");
                        }
                    }
                }
                else{
                    PopUp.showError("<html><center>Du besitzt kein Konto.");
                }
            }
        });
        BSAN.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpNameÄndern(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE));
                if(eingabe.size() == 2 && !eingabe.get(0).equals("")){
                    String newName = eingabe.get(0);
                    //System.out.println("newName: " + newName);
                    String passwort = eingabe.get(1);
                    //System.out.println("passwort: " + passwort);
                    if (kunde.getPin().equals(passwort)) {
                        kunde.NameÄndern(newName);

                        nutzerDB.NameÄndern(kunde.getEMail(), newName);

                        MainPage.setName(kunde);

                        PopUp.showInfo("Du hast deinen Namen auf '" + newName + "' geändert.");
                    } else {
                        PopUp.showError("<html><center>Zugriff nicht gewährt.<br>Die PIN ist falsch.</center></html>");
                    }
                }
            }
        });
        BSAP.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpPinÄndern(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE));
                if(eingabe.size() == 3 && !eingabe.contains("")){
                    String oldPin = eingabe.get(0);
                    String newPin = eingabe.get(1);
                    String confirmPin = eingabe.get(2);
                    if(!oldPin.equals(kunde.getPin())){
                        PopUp.showError("Die alte PIN ist falsch.");
                        return;
                    }
                    if(!newPin.equals(confirmPin)){
                        PopUp.showError("Die neue PIN wurde falsch bestätigt.");
                        return;
                    }
                    kunde.PinÄndern(newPin);
                    nutzerDB.PinÄndern(kunde.getEMail(), kunde.getPin());

                    PopUp.showInfo("Du hast deine PIN geändert.");
                }
            }
        });
        BSAD.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                List<String> eingabe = mw.PopUpAccountLöschen(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE));
                if(eingabe.size() == 2 && !eingabe.contains("")){
                    String pin1 = eingabe.get(0);
                    String pin2 = eingabe.get(1);
                    if(kunde.getPin().equals(pin1) && pin1.equals(pin2)){
                        System.out.println("Der Kunde '" + kunde.getEMail() + "' wird gelöscht.");
                        List<Konto> kontos = kontenDB.KontenVonUserGeben(kunde.getEMail());
                        for (Konto k : kontos) {
                            kontenDB.KontoLöschen(k.KontonummerGeben());
                        }
                        nutzerDB.NutzerLöschen(kunde.getEMail());
                        PopUp.showInfo("Du hast deinen Account (" + kunde.getEMail() + ") gelöscht.");

                        if(nutzer instanceof Kunde){
                            mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
                        }
                        else if(nutzer instanceof Angestellter){
                            mw.getWindow().showPlane(PAGES.ADMIN_MAIN_PAGE);
                        }
                    }
                    else{
                        PopUp.showError("<html><center>Eine der PINs ist falsch.</center></html>");
                    }
                }
            }
        });
    }
}
