package Datenbank;
import Bank.Konten.Konto;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.NutzerDB;
import util.StringFormat;


public class DBSchnittstelle {
    public static void main(String[] args) {
        double d = 12.356;
        String f = StringFormat.formatDouble(d, 2);
        System.out.println("d: " + d);
        System.out.println("f: " + f);






    }
}