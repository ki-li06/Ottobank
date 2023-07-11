package Datenbank;
import Bank.Konten.Konto;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.KontenDB;
import Datenbank.LiteSQL.NutzerDB;
import util.StringFormat;

import java.util.List;


public class DBSchnittstelle {
    public static void main(String[] args) {
        NutzerDB nutzerDB = new NutzerDB();
        System.out.println("Alle Nutzer:");
        nutzerDB.AlleNutzerAusgeben();

        System.out.println();

        KontenDB kontenDB = new KontenDB();
        System.out.println("Alle Konten:");
        kontenDB.AlleKontenAusgeben();

        List<Konto> list = kontenDB.KontenVonUserGeben("mustermann@gmx.de");
        for (Konto k : list) {
            System.out.println(k.getAsStringKurz());
        }




    }
}