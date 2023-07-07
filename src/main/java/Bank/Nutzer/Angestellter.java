package Bank.Nutzer;

import Bank.Bank;
import Bank.Konten.Girokonto;
import Bank.Konten.Sparkonto;

public class Angestellter extends Nutzer{
    public Angestellter(String nameNeu, String mailNeu, String pinNeu)
    {
        super(nameNeu, mailNeu, pinNeu);
    }

    boolean KontoLoeschen()
    {
        return false;
    }
    public void ZinssatzÄndern(){

    }

    String AngestellterLoeschen(String eingabe)
    {
        return "";
    }

    String NeuenKundeEinrichten(String nameNeu, String pinNeu, String mailNeu)
    {
        new Kunde(nameNeu, mailNeu, pinNeu);
        return "";
    }
    void KontoSetzen(){}

    @Override
    public TYPE getType() {
        return TYPE.ANGESTELLTER;
    }

    @Override
    public String toString() {
        return "Angestellter{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}
