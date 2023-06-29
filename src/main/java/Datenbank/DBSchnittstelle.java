package Datenbank;

import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.KontenDB;
import Datenbank.LiteSQL.NutzerDB;

public class DBSchnittstelle {
    public static void main(String[] args) {
        Kunde kunde = new Kunde("HuberSepp", "huber@sepp.com", "123456ficken");
        NutzerDB ndb = new NutzerDB();
        ndb.NutzerHinzufügen(kunde.getName(), kunde.getEmail(), kunde.getPin(), NutzerDB.NUTZER);

        Konto konto = new Sparkonto();
        konto.setBesitzer(kunde);

        KontenDB kdb = new KontenDB();
        kdb.KontoHinzufügen(konto);

        System.out.println("id: " + konto.KontonummerGeben());

    }
}
