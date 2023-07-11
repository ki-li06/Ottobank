package Bank.Nutzer;

import Bank.Konten.Konto;
import Datenbank.LiteSQL.NutzerDB;

public abstract class Nutzer {
    public enum TYPE{
        ANGESTELLTER,
        KUNDE
    }

    protected String name;
    protected String email;
    protected String pin;


    protected Nutzer(String name, String email, String pin) {
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

    public abstract TYPE getType();

    public void NameÄndern(String newName){
        name = newName;
    }
    public void PinÄndern(String newPin){
        pin = newPin;
    }

    @Override
    public String toString() {
        return null;
    }
}
