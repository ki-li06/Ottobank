package Bank.Nutzer;

import Bank.Konten.Konto;

public class Kunde extends Nutzer
{
    public static String KUNDE = "KUNDE";


    public Kunde(String nameNeu, String mail, String pinNeu) {
        super(nameNeu, mail, pinNeu);
    }


    /*Auszugseintrag[] AuszugsdatenErstelen(){};
    * boolean Aktkonto(int)*/

    @Override
    public TYPE getType() {
        return TYPE.KUNDE;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}

