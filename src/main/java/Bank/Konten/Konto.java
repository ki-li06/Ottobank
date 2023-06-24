package Bank.Konten;

import Bank.Nutzer.Kunde;

public abstract class Konto {
    protected double kontostand;
    protected int kontonummer;
    protected Kunde besitzer;

    //Warum keine abstract Methoden? (kilian)

    public boolean Abheben(double betrag){return false;}
    int KontonummerGeben (){return 0;}
    public double KontostandGeben(){return 0;}
    Kunde EigentümerGeben(){return null;}
    public void Einzahlen(double einzahlbetrag){}

}
