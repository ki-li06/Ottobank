package Bank;

public abstract class Konto {
    protected double kontostand;
    protected int kontonummer;
    protected Kunde besitzer;

    boolean Abheben(double betrag){return false;}
    int KontonummerGeben (){return 0;}
    double KontostandGeben (){return 0;}
    Kunde EigentümerGeben(){return null;}
    void Einzahlen(double einzahlbetrag){}
}
