package Bank.Nutzer;

import Bank.Konten.Konto;
import Datenbank.LiteSQL.NutzerDB;

public abstract class Nutzer {
    String name;
    String pin;
    Konto konto;   //(kilian) warum hat ein Nutzer ein Konto?

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public int NutzerIDGeben(){
        NutzerDB ndb = new NutzerDB();
        return ndb.NutzerIDGeben(name);
    }
}
