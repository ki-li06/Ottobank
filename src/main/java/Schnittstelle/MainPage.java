package Schnittstelle;

import Bank.Konten.Konto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import GUI.*;
import GUI.BetterComponents.BetterButton;
import GUI.BetterComponents.BetterComboBox;
import GUI.BetterComponents.BetterTextField;
import GUI.UIs.UIButtonMethod;
import GUI.UIs.UIComboBoxMethod;

import java.util.*;

import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;
import static Schnittstelle.Main.*;

public class MainPage {
    public static final BetterTextField TMNA = (BetterTextField) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.NAME_LABEL_PARAM);
    public static final BetterTextField TMNU = (BetterTextField) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.KONTONUMMER_PARAM);
    public static final BetterTextField TMS = (BetterTextField) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.KONTOSTAND_PARAM);
    public static final BetterComboBox CMK = (BetterComboBox) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.KONTO_WECHSEL_BOX);
    public static final BetterButton BMS = (BetterButton) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.SETTINGS_BUTTON);
    public static final BetterButton BME = (BetterButton) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.EINZAHLEN_BUTTON);
    public static final BetterButton BMÜ = (BetterButton) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.ÜBERWEISEN_BUTTON);
    public static final BetterButton BMA = (BetterButton) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.ABHEBEN_BUTTON);
    public static final BetterButton BMO = (BetterButton) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.LOGOUT_BUTTON);

    public static int aktuellesKonto = -1;

    public static void InitializeSettingsButton(){
        BMS.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                //System.out.println("Main Page User PRESSED BUTTON SETTINGS");
                mw.getWindow().showPlane(StackedWidget.PAGES.SETTINGS_PAGE);
            }
        });
    }

    public static void setName(Kunde k){
        TMNA.setText(k.getName());
    }
    public static void setKontenListe(){
        String[] kontenNamen;

        if(getKontenListe().size() > 0) {
            kontenNamen = getKontenListeString();
        }
        else{
            kontenNamen = new String[]{"------"};
        }
        CMK.setElements(kontenNamen);

        if(getKontenListe().size() > 0) {
            CMK.addMethod(new UIComboBoxMethod() {
                @Override
                public void performMethod(String data) {
                    int i = Arrays.stream(kontenNamen).toList().indexOf(data);
                    setAktuellesKonto(i);
                }
            });
        }
        else if(!(nutzer instanceof Angestellter)){
            PopUp.showWarning("Du besitzt keine Kontos. Bitte ein Konto anlegen.");
        }
    }

    public static void setAktuellesKonto(int i){
        aktuellesKonto = i;
        CMK.setSelectedIndex(i);
        if(getKontenListe().size() == 0){
            aktuellesKonto = -1;
        }
        setKontonummer();
        setKontostand();
    }
    private static void setKontonummer() {
        if(getAktuellesKonto() == null){
            TMNU.setText("------");
        }
        else {
            TMNU.setText(String.valueOf(getAktuellesKonto().KontonummerGeben()));
        }
    }
    private static void setKontostand() {
        if(getAktuellesKonto() == null){
            TMS.setText("------");
        }
        else {
            TMS.setText(String.valueOf(getAktuellesKonto().KontostandAlsStringGeben()));
        }
    }

    public static void setButtonsVoid(){
        setEinzahlenButtonVoid();
        setÜberweisenButtonVoid();
        setAbhebenButtonVoid();
        setAbmeldenButtonVoid();
    }

    private static void setEinzahlenButtonVoid(){
        BME.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(getAktuellesKonto() != null){
                    List<String> eingabe = mw.PopUpEinzahlen(mw.getWindow().getFrame(PAGES.MAIN_PAGE));
                    if(eingabe.size() != 0) {
                        String s = eingabe.get(0);
                        double d = Double.parseDouble(s.replace(",", "."));
                        //System.out.println("double: " + d);
                        Konto konto = getAktuellesKonto();
                        konto.Einzahlen(d);
                        kontenDB.KontoÄndern(konto.KontonummerGeben(), konto);
                        setKontostand();
                    }
                }
            }
        });
    }
    private static void setÜberweisenButtonVoid(){
        BMÜ.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(getAktuellesKonto() != null){
                    List<String> eingabe = mw.PopUpÜberweisen(mw.getWindow().getFrame(PAGES.MAIN_PAGE));
                    System.out.println("eingabe: " + eingabe + "(" + eingabe.size() + ")");
                    if(eingabe.size() == 2 && !eingabe.contains("")){
                        int nummer = Integer.parseInt(eingabe.get(0));
                        double betrag = Double.parseDouble(eingabe.get(1).replace(",", "."));
                        if(kontenDB.NummerSchonBelegt(nummer)){
                            if(getAktuellesKonto().KontonummerGeben() != nummer){
                                System.out.println("aktKonto: " + getAktuellesKonto());
                                if(getAktuellesKonto().AbhebenErlaubt(betrag)){
                                    Konto from = getAktuellesKonto();
                                    from.Abheben(betrag);
                                    kontenDB.KontoÄndern(from.KontonummerGeben(), from);

                                    Konto address = kontenDB.getKontoVonKontonummer(nummer);
                                    address.Einzahlen(betrag);
                                    kontenDB.KontoÄndern(address.KontonummerGeben(), address);

                                    MainPage.setKontostand();

                                    PopUp.showInfo("Du hast " + betrag + " € an das Konto " + nummer + " überwiesen.");
                                }
                                else{
                                    PopUp.showError("<html>Du besitzt zu wenig Geld,<br>um diesen Betrag zu überweisen.</html>");
                                }
                            }
                            else{
                                PopUp.showError("<html>Du kannst nichts an dieses Konto überweisen,<br>da du es gerade selbst ausgewählt hast.</html>");
                            }
                        }
                        else{
                            PopUp.showError("Diese Kontonummer ist nicht belegt.");
                        }
                    }
                }
            }
        });
    }
    private static void setAbhebenButtonVoid() {
        BMA.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(getAktuellesKonto() != null){
                    List<String> eingabe = mw.PopUpAbheben(mw.getWindow().getFrame(PAGES.MAIN_PAGE));
                    if(eingabe.size() != 0) {
                        String s = eingabe.get(0);
                        double d = Double.parseDouble(s.replace(",", "."));
                        Konto konto = getAktuellesKonto();
                        boolean erlaubt = konto.AbhebenErlaubt(d);
                        if (!erlaubt) {
                            PopUp.showError("Dieser Betrag darf nicht abgehoben werden.");
                        } else {
                            konto.Abheben(d);
                            kontenDB.KontoÄndern(konto.KontonummerGeben(), konto);
                            setKontostand();
                        }
                    }
                }
            }
        });
    }
    private static void setAbmeldenButtonVoid(){
        BMO.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(nutzer instanceof Angestellter){
                    mw.getWindow().showPlane(PAGES.ADMIN_MAIN_PAGE);
                }
                else{
                    mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
                }
            }
        });
    }


    public static Konto getAktuellesKonto(){
        if(aktuellesKonto == -1){
            return null;
        }
        List<Konto> konten = new ArrayList<>(getKontenListe());
        if(aktuellesKonto >= konten.size()){
            System.out.println("FEHLER - MainPage - DIESER KUNDE HAT KEINE KONTOS");
        }
        try {
            return konten.get(aktuellesKonto);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    public static List<Konto> getKontenListe(){
        //System.out.println(kontenDB.KontenVonUserGeben(kunde.getEMail()));
        List<Konto> konten = new ArrayList<>(kontenDB.KontenVonUserGeben(kunde.getEMail()));
        konten.sort(new Comparator<>() {
            @Override
            public int compare(Konto o1, Konto o2) {
                if(o1.getType() == o2.getType()){
                    return Integer.compare(o1.KontonummerGeben(), o2.KontonummerGeben());
                }
                return o1.getType().compareTo(o2.getType());
            }
        });
        return konten;
    }
    public static String[] getKontenListeString(){
        return getKontenListe().stream().map(
                Konto::getAsStringKurz
        ).toArray(String[]::new);
    }

}
