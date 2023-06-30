package Datenbank;

import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.NutzerDB;

import java.util.List;

public class DBSchnittstelle {
    public static void main(String[] args) {
        Angestellter kunde = new Angestellter("ThomasMüller", "tm@gmail.com", "passwort");
        NutzerDB ndb = new NutzerDB();
        ndb.NutzerHinzufügen(kunde);
        System.out.println();
        Nutzer nd = ndb.NutzerZuMail("tm@gmail.com");
        System.out.println("nutzer in der datenbank " + nd.toString());
        System.out.println();
        boolean b = ndb.PasswortRichtig("tm@gmail.com", "passwort");
        System.out.println("passwort richtig ? " + b);
        System.out.println();
        System.out.println("Alle NutzerGeben");
        List<Nutzer> list = ndb.alleNutzerGeben();
        for (Nutzer n : list) {
            System.out.println(" - " + n);
        }
        System.out.println();
        ndb.PinÄndern("tm@gmail.com", "nEuEsPaSsWoRt");
        System.out.println();
        ndb.alleNutzerAusgeben();
        System.out.println();
        ndb.NutzerLöschen("tm@gmail.com");

        /*
        Konto konto = new Sparkonto();
        konto.setBesitzer(kunde);

        KontenDB kdb = new KontenDB();
        kdb.KontoHinzufügen(konto);

        System.out.println("id: " + konto.KontonummerGeben());
        */


    }
}
