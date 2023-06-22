package Bank;

public class Kunde extends Nutzer
{
    public Kunde(String nameNeu, int pinNeu) {
        name = nameNeu;
        pin = pinNeu;
    }

    public static double KontostandGeben()
    {
        return Konto.KontostandGeben();
    }

    public static void Einzahlen(double einzahlbetrag)
    {
        Konto.Einzahlen(einzahlbetrag);
    }

    public boolean Abheben(double abhebebetrag)
    {
        return Konto.Abheben(abhebebetrag);
    }

    /*Auszugseintrag[] AuszugsdatenErstelen(){};*/
}

