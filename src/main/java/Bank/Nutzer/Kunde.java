package Bank.Nutzer;

public class Kunde extends Nutzer
{
    public Kunde(String nameNeu, String mail, String pinNeu) {
        super(nameNeu, mail, pinNeu);
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

    @Override
    public String toString() {
        return "Kunde{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}

