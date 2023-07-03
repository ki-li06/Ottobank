package Bank.Nutzer;

import Bank.Konten.Konto;
import Datenbank.LiteSQL.NutzerDB;

public abstract class Nutzer {

    String name;
    String email;
    String pin;
    Konto konto;   //(kilian) warum hat ein Nutzer ein Konto?

    public Nutzer(String name, String email, String pin) {
        this.name = name;
        this.email = email;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public String getEMail() {
        return email;
    }
    /*
    public String NutzerIDGeben(){
        NutzerDB ndb = new NutzerDB();
        return ndb.NutzerIDGeben(name);
    }*/

    public abstract String getType();

    @Override
    public String toString() {
        return null;
    }
}
