import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.NutzerDB;
import GUI.*;

import static Bank.Nutzer.Nutzer.*;
import static Bank.Konten.Konto.*;
import static util.Delay.delay;
import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;

public class Main {
    public static void main(String[] args) {
        MainWindow mw = WindowManager.getWindow();
        //System.out.println(mw.getWindow());
        mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
        NutzerDB nutzerDB = new NutzerDB();
        // LOGIN PAGE
        mw.getWindow().showPlane(PAGES.LOGIN_PAGE);
        BetterTextField TLE = (BetterTextField) mw.getWindow().getFrame(PAGES.LOGIN_PAGE).getElement(COMPONENTS.EMAIL_INPUT);
        BetterTextField TLP = (BetterTextField) mw.getWindow().getFrame(PAGES.LOGIN_PAGE).getElement(COMPONENTS.PIN_INPUT);

        BetterButton BLL = (BetterButton) mw.getWindow().getFrame(PAGES.LOGIN_PAGE).getElement(COMPONENTS.LOGIN_BUTTON);
        BLL.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                //System.out.println("email: '" + TLE.getText() + "'");
                //System.out.println("text : '" + TLP.getText() + "'");
                if (nutzerDB.PasswortRichtig(TLE.getText(),TLP.getText())) {
                    if(nutzerDB.NutzerZuMail(TLE.getText()).getType().equals(Nutzer.TYPE.KUNDE)) {
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
        BetterTextField TRV = (BetterTextField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.VORNAME_INPUT);
        BetterTextField TRN = (BetterTextField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.NACHNAME_INPUT);
        BetterTextField TRE = (BetterTextField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.EMAIL_INPUT);
        BetterTextField TRP = (BetterTextField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.PIN_INPUT);
        BetterTextField TRC = (BetterTextField) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.CONFIRM_PIN_INPUT);
        BetterButton BRR = (BetterButton) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.REGISTER_BUTTON);
        BRR.addMethod(new UIButtonMethod() {
            @Override
            public void performMethod() {
                if(!TRV.getText().equals("") && !TRN.getText().equals("") && !TRE.getText().equals("") && !TRP.getText().equals("")){
                    if (!TRP.getText().equals(TRC.getText())){
                        PopUp.showError("PIN stimmt nicht überein!");
                        return;
                    }
                    String mail = TRE.getText();
                    Kunde k = new Kunde(TRV.getText() +" "+ TRN.getText(),mail,TRP.getText());
                    nutzerDB.NutzerHinzufügen(k);
                    PopUp.showInfo("Neuer Account wurde erstellt.");
                    mw.getWindow().showPlane(0);
                }else{
                    PopUp.showError("Alle Eingaben müssen ausgefüllt sein!");
                }
            }
        });
        //SETTINGS PAGE
        BetterTextField TSV = (BetterTextField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.VORNAME_INPUT);
        BetterTextField TSN = (BetterTextField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.NACHNAME_INPUT);
        BetterTextField TSE = (BetterTextField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.EMAIL_INPUT);
        BetterTextField TSP = (BetterTextField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.PIN_INPUT);
        BetterTextField TSC = (BetterTextField) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.CONFIRM_PIN_INPUT);
        BetterButton BSS = (BetterButton) mw.getWindow().getFrame(PAGES.SETTINGS_PAGE).getElement(COMPONENTS.SAVE_BUTTON);


        //MAIN PAGE USER
        BetterButton BMS = (BetterButton) mw.getWindow().getFrame(PAGES.REGISTER_PAGE).getElement(COMPONENTS.SETTINGS_BUTTON);


        //MAIN PAGE ADMIN
        BetterTextField TMAN = (BetterTextField) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.USER_NAME);
        BetterButton BMAL = (BetterButton) mw.getWindow().getFrame(PAGES.ADMIN_MAIN_PAGE).getElement(COMPONENTS.LOGIN_BUTTON);
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
