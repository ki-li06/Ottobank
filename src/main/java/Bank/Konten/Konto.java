package Bank.Konten;

import Bank.Nutzer.Kunde;

public abstract class Konto {
    protected int kontonummer;
    protected double kontostand;
    protected Kunde besitzer;

    protected Konto(){
        kontostand = 0;
        kontonummer = -1;
    }

    public Konto(double kontostand, Kunde besitzer) {
        kontonummer = -1;
        this.kontostand = kontostand;
        this.besitzer = besitzer;
    }

    protected Konto(int kontonummer, double kontostand, Kunde besitzer) {
        this(kontostand, besitzer);
        this.kontonummer = kontonummer;
    }

    public void setKontonummer(int kontonummer) {
        this.kontonummer = kontonummer;
    }

    public void setBesitzer(Kunde besitzer) {
        this.besitzer = besitzer;
    }

    public boolean Abheben(double betrag)
    {return false;} //wird in den Kontos überschrieben

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
        System.out.println("Neuer Kontostand:"+kontostand);
    }



    /**
     * für die Datenbank wichtig (da sowohl GiroKonto als auch das Sparkonto nur ein Double
     * szg. extra als Attribut haben, wird dies in der Datenbank als eines gespeichert)
     */
    public abstract double getSpecialDouble();
    public abstract String getType();

    @Override
    public String toString() {
        return null;
    }
}
