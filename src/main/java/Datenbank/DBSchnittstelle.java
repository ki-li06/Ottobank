package Datenbank;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.KontenDB;
import Datenbank.LiteSQL.NutzerDB;

import java.util.List;

public class DBSchnittstelle {
    public static void main(String[] args) {
        NutzerDB ndb = new NutzerDB();
        Kunde kunde = (Kunde) ndb.NutzerZuMail("mustermann@gmx.de");
        System.out.println("kunde: " + kunde);

        //Girokonto gk = new Girokonto(420.00, kunde, 999.99);
        final int NUMMER = 856808;
        KontenDB kdb = new KontenDB();
        Konto k = kdb.getKontoVonKontonummer(NUMMER);

        System.out.println("konto: " + k);



    }
}
