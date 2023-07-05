package Schnittstelle;

import Datenbank.LiteSQL.NutzerDB;
import GUI.*;
import Datenbank.LiteSQL.*;
import Bank.Nutzer.*;
import Bank.Konten.*;
import Bank.*;
import java.util.Objects;

import static Bank.Nutzer.Angestellter.ANGESTELLTER;

public class Schnittstelle {
    public static void main(String[] args) {
        MainWindow mw = WindowManager.getWindow();
        //System.out.println(mw.getWindow());
        mw.getWindow().showPlane("LOGIN_PAGE");
        NutzerDB nutzerDB = new NutzerDB();
        // LOGIN PAGE
        mw.getWindow().showPlane("LOGIN_PAGE");
        BetterTextField TLE = (BetterTextField) mw.getWindow().getFrame("LOGIN_PAGE").getElement("EMAIL_INPUT");
        BetterTextField TLP = (BetterTextField) mw.getWindow().getFrame("LOGIN_PAGE").getElement("PIN_INPUT");

        BetterButton BLL = (BetterButton) mw.getWindow().getFrame("LOGIN_PAGE").getElement("LOGIN_BUTTON");
        BLL.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if (nutzerDB.PasswortRichtig(TLE.getText(),TLP.getText())) {
                    if(nutzerDB.NutzerZuMail(TLE.getText()).getType().equals(ANGESTELLTER)) {
                        mw.getWindow().showPlane(4);
                    }
                    else {
                        mw.getWindow().showPlane(2);
                    }
                } else {
                    PopUp.showError("Passwort falsch!");
                }

            }
        });
        //REGISTER PAGE
        BetterTextField TRV = (BetterTextField) mw.getWindow().getFrame("REGISTER_PAGE").getElement("VORNAME_INPUT");
        BetterTextField TRN = (BetterTextField) mw.getWindow().getFrame("REGISTER_PAGE").getElement("NACHNAME_INPUT");
        BetterTextField TRE = (BetterTextField) mw.getWindow().getFrame("REGISTER_PAGE").getElement("EMAIL_INPUT");
        BetterTextField TRP = (BetterTextField) mw.getWindow().getFrame("REGISTER_PAGE").getElement("PIN_INPUT");
        BetterButton BRR = (BetterButton) mw.getWindow().getFrame("REGISTER_PAGE").getElement("REGISTER_BUTTON");
        BRR.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(TRV.getText().equals("") ||TRN.getText().equals("")|| TRE.getText().equals("") || TRP.getText().equals("")){
                    String mail = TRE.getText();
                    Kunde k = new Kunde(TRV.getText() +"\n"+ TRN.getText(),mail,TRP.getText());
                    nutzerDB.NutzerHinzufügen(k);
                    mw.getWindow().showPlane(1);
                }else{ 
                    PopUp.showError("Alle Eingaben müssen ausgefüllt sein!");
                }
            }
        });
        //SETTINGS PAGE
        BetterTextField TSV = (BetterTextField) mw.getWindow().getFrame("SETTINGS_PAGE").getElement("VORNAME_INPUT");
        BetterTextField TSN = (BetterTextField) mw.getWindow().getFrame("SETTINGS_PAGE").getElement("NACHNAME_INPUT");
        BetterTextField TSE = (BetterTextField) mw.getWindow().getFrame("SETTINGS_PAGE").getElement("EMAIL_INPUT");
        BetterTextField TSP = (BetterTextField) mw.getWindow().getFrame("SETTINGS_PAGE").getElement("PIN_INPUT");
        BetterTextField TSC = (BetterTextField) mw.getWindow().getFrame("SETTINGS_PAGE").getElement("CONFIRM_PIN_INPUT");
        BetterButton BSS = (BetterButton) mw.getWindow().getFrame("SETTINGS_PAGE").getElement("SAVE_BUTTON");


        //MAIN PAGE
        BetterButton BMS = (BetterButton) mw.getWindow().getFrame("REGISTER_PAGE").getElement("SETTINGS_BUTTON");
        

    }

    public static void main(MainWindow mw) {

    }
}
