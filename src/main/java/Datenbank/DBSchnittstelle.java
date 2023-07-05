package Datenbank;

import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.NutzerDB;

import static Bank.Nutzer.Angestellter.ANGESTELLTER;
import static Bank.Nutzer.Kunde.KUNDE;


public class DBSchnittstelle {
    public static void main(String[] args) {
        NutzerDB ndb = new NutzerDB();
        String mail = "huber@sepp.com";
        Nutzer nutzer = ndb.NutzerZuMail(mail);
        System.out.println("kunde: " + nutzer);

        ndb.TypeÄndern(mail, KUNDE);





    }
}
