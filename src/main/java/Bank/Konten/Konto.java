package Bank.Konten;

import Bank.Nutzer.Kunde;

public abstract class Konto {
    protected double kontostand;
    protected int kontonummer;
    protected Kunde besitzer;

    public Konto(){
        kontostand = 0;
        kontonummer = -1;
    }

    public void setKontonummer(int kontonummer) {
        this.kontonummer = kontonummer;
    }

    public void setBesitzer(Kunde besitzer) {
        this.besitzer = besitzer;
    }

    public boolean Abheben(double betrag){return false;}
    public int KontonummerGeben (){
        return kontonummer;
    }
    public double KontostandGeben(){
        return kontostand;
    }
    public Kunde EigentümerGeben(){
        return besitzer;
    }
    public void Einzahlen(double einzahlbetrag)
    {
        kontostand=kontostand+einzahlbetrag;
    }

    @Override
    public String toString() {
        return null;
    }
}
