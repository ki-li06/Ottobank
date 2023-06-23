package Bank;

public class Kunde extends Nutzer
{
    public Kunde(String nameNeu, int pinNeu) {
        name = nameNeu;
        pin = pinNeu;
    }

    public double KontostandGeben()
    {
        return konto.KontostandGeben();
    }

    public void Einzahlen(double einzahlbetrag)
    {
        konto.Einzahlen(einzahlbetrag);
    }

    public boolean Abheben(double abhebebetrag)
    {
        return konto.Abheben(abhebebetrag);
    }

    /*Auszugseintrag[] AuszugsdatenErstelen(){};
    * boolean Aktkonto(int)*/


}

