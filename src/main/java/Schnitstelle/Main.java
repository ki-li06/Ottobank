package Schnitstelle;

import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.KontenDB;
import Datenbank.LiteSQL.NutzerDB;
import GUI.*;

import static Bank.Nutzer.Nutzer.*;
import static Bank.Konten.Konto.*;
import static util.Delay.delay;
import static GUI.StackedWidget.PAGES;
import static GUI.StackedPane.COMPONENTS;

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

        //MAIN PAGE USER
        MainPage.InitializeSettingsButton();

        //SETTINGS PAGE
        SettingsPage.InitializeSaveButton();

        //ADMIN MAIN PAGE
        AdminMainPage.InitializeLoginButton();
    }
}
