package Schnitstelle;

import Bank.Konten.Konto;
import Bank.Nutzer.Kunde;
import GUI.*;

import java.util.*;

import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;
import static Schnitstelle.Main.*;

public class MainPage {
    public static final BetterTextField TMNA = (BetterTextField) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.NAME_LABEL_PARAM);
    public static final BetterTextField TMNU = (BetterTextField) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.KONTONUMMER_PARAM);
    public static final BetterTextField TMS = (BetterTextField) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.KONTOSTAND_PARAM);
    public static final BetterComboBox CMK = (BetterComboBox) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.KONTO_WECHSEL_BOX);
    public static final BetterButton BMS = (BetterButton) mw.getWindow().getFrame(PAGES.MAIN_PAGE).getElement(COMPONENTS.SETTINGS_BUTTON);

    public static int aktuellesKonto = -1;

    public static void InitializeSettingsButton(){
        BMS.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                System.out.println("Main Page User PRESSED BUTTON SETTINGS");
                mw.getWindow().showPlane(StackedWidget.PAGES.SETTINGS_PAGE);
            }
        });
    }

    public static void setName(Kunde k){
        TMNA.setText(k.getName());
    }

    public static void setAktuellesKonto(int i){
        aktuellesKonto = i;
        setKontonummer();
        setKontostand();
    }

    public static void setKontenListe(){
        String[] kontenNamen = getKontenListe().stream().map(
                i->i.getType().toString().charAt(0) + "K - " + i.KontonummerGeben()
                ).toArray(String[]::new);
        CMK.setElements(kontenNamen);

        CMK.addMethod(new UIComboBoxMethod() {
            @Override
            public void performMethod(String data) {
                int i = Arrays.stream(kontenNamen).toList().indexOf(data);
                setAktuellesKonto(i);
            }
        });
    }

    private static List<Konto> getKontenListe(){
        System.out.println(kontenDB.KontenVonUserGeben(kunde.getEMail()));
        List<Konto> konten = new ArrayList<>(kontenDB.KontenVonUserGeben(kunde.getEMail()));
        konten.sort(new Comparator<Konto>() {
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

    private static void setKontonummer() {
        TMNU.setText(String.valueOf(getAktuellesKonto().KontonummerGeben()));
    }

    private static void setKontostand() {
        TMS.setText(String.valueOf(getAktuellesKonto().KontostandAlsStringGeben()));
    }

    public static Konto getAktuellesKonto(){
        List<Konto> konten = new ArrayList<>(getKontenListe());
        if(aktuellesKonto >= konten.size()){
            System.out.println("FEHLER - MainPage - DIESER KUNDE HAT KEINE KONTOS");
        }
        return konten.get(aktuellesKonto);
    }
}
