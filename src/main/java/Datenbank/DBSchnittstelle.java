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
        Kunde kunde = (Kunde) ndb.NutzerZuMail("huber@sepp.com");
        System.out.println("kunde: " + kunde);
        ndb.AlleNutzerAusgeben();

        Sparkonto sk = new Sparkonto(11200300.41, kunde, 0.025);
        KontenDB kdb = new KontenDB();
        kdb.KontoHinzufügen(sk);

        System.out.println("konto: " + sk);
        kdb.AlleKontenAusgeben();



    }
}
