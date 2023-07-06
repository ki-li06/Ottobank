import Bank.Nutzer.Kunde;
import Datenbank.LiteSQL.NutzerDB;
import GUI.*;

import static Bank.Nutzer.Angestellter.ANGESTELLTER;
import static util.Delay.delay;

public class Main {
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
                //System.out.println("email: '" + TLE.getText() + "'");
                //System.out.println("text : '" + TLP.getText() + "'");
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
                if(!TRV.getText().equals("") && !TRN.getText().equals("") && !TRE.getText().equals("") && !TRP.getText().equals("")){
                    String mail = TRE.getText();
                    Kunde k = new Kunde(TRV.getText() +" "+ TRN.getText(),mail,TRP.getText());
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


        //MAIN PAGE USER
        BetterButton BMS = (BetterButton) mw.getWindow().getFrame("REGISTER_PAGE").getElement("SETTINGS_BUTTON");


        //MAIN PAGE ADMIN
        BetterTextField TMAN = (BetterTextField) mw.getWindow().getFrame("ADMIN_MAIN_PAGE").getElement("USER_NAME");
        BetterButton BMAL = (BetterButton) mw.getWindow().getFrame("ADMIN_MAIN_PAGE").getElement("LOGIN_BUTTON");
        BMAL.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(TMAN.getText().equals("")){
                    PopUp.showError("Alle Eingaben müssen ausgefüllt sein!");
                }else{
                    mw.getWindow().showPlane(2);
                }
            }
        });
    }

    public static void main(MainWindow mw) {

    }
}
