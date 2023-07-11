package Schnitstelle;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
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

                        PopUp.showInfo("<html>Der Nutzer \"" + kunde.getEMail() + "\"<br>hat ein neues Sparkonto eingerichtet.</html>");
                    }
                    else if(type.equals(String.valueOf(Konto.TYPE.GIROKONTO))){
                        List<String> nettoeinkommen  = mw.PopUpNettoeinkommen(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE));
                        //System.out.println("list einkommen: " + nettoeinkommen + "(" + nettoeinkommen.size() + ")");
                        if(nettoeinkommen.size() == 1 && !nettoeinkommen.get(0).equals("")){
                            double einkommen = Double.parseDouble(nettoeinkommen.get(0).replace(",", "."));
                            //System.out.println("einkommen: " + einkommen);
                            Girokonto gk = new Girokonto(0, kunde, einkommen);
                            kontenDB.KontoHinzufügen(gk);
                            PopUp.showInfo("<html>Der Nutzer \"" + kunde.getEMail() + "\"<br>hat ein neues Girokonto eingerichtet.</html>");

                            MainPage.setKontenListe();
                            MainPage.setAktuellesKonto(MainPage.getKontenListe().stream().map(Konto::KontonummerGeben).toList().indexOf(gk.KontonummerGeben()));

                            PopUp.showInfo("<html>Der Nutzer \"" + kunde.getEMail() + "\"<br>hat ein neues Sparkonto eingerichtet.</html>");
                        }
                        else{
                            PopUp.showError("<html>Es muss ein Einkommen eingegeben werden.<br>Konto bitte neu erstellen.</html>");
                        }
                    }
                }
            }
        });
        BSKD.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                String[] kontos = MainPage.getKontenListeString();
                List<String> eingabe = mw.PopUpKontoAuswahl(mw.getWindow().getFrame(PAGES.SETTINGS_PAGE), kontos);
                if(eingabe.size() == 1){
                    int nummer = Integer.parseInt(eingabe.get(0).split(" ")[2]);
                    Konto k = kontenDB.getKontoVonKontonummer(nummer);
                    System.out.println("k: " + k);
                }
            }
        });
    }
}
