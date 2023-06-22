package Bank;

public abstract class Konto {
    protected double kontostand;
    protected int kontonummer;
    protected Kunde besitzer;

    static boolean Abheben(double abhebebetrag)
    {return false;}
    int KontonummerGeben (){return 0;}
    static double KontostandGeben(){return 0;}
    Kunde EigentümerGeben(){return null;}
    static void Einzahlen(double einzahlbetrag){}
}
