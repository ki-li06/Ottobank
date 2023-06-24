package Datenbank;

import Datenbank.LiteSQL.NutzerDB;

public class DBSchnittstelle {
    public static void main(String[] args) {
        NutzerDB nutzerDB = new NutzerDB();
        int id = nutzerDB.PinRichtig("Anton Huber", "123456");
        System.out.println("id: " + id);


    }
}
