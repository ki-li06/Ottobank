package Schnittstelle;

import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.KontenDB;
import Datenbank.LiteSQL.NutzerDB;
import GUI.*;

import static GUI.StackedWidget.PAGES;

public class Main {
    public static MainWindow mw;
    public static NutzerDB nutzerDB;
    public static KontenDB kontenDB;

    public static Nutzer nutzer;
    public static Kunde kunde;

    public static void main(String[] args) {
        mw = WindowManager.getWindow();
        nutzerDB = new NutzerDB();
        kontenDB = new KontenDB();

        mw.getWindow().showPlane(PAGES.LOGIN_PAGE);

        // LOGIN PAGE
        LoginPage.InitializeLoginButton();

        //REGISTER PAGE
        RegisterPage.InitializeRegisterButton();

        //SETTINGS PAGE
        //SettingsPage.InitializeSaveButton();

        //ADMIN MAIN PAGE
        AdminMainPage.InitializeLoginButton();

        //ADMIN SETTINGS PAGE
        //AdminSettingsPage.__init__();
    }
}
