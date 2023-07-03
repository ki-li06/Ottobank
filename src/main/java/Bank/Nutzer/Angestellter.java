package Bank.Nutzer;

import Bank.Bank;
import Bank.Konten.Girokonto;
import Bank.Konten.Sparkonto;

public class Angestellter extends Nutzer{
    public static final String ANGESTELLTER = "ANGESTELLTER";

    public Angestellter(String nameNeu, String mailNeu, String pinNeu) {
        super(nameNeu, mailNeu, pinNeu);
    }
    int SparkontoEinrichten()
    {

        return 0;
    }
    int GirokontoEinrichten()
    {
        new Girokonto();
        return -1;
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
    void KontoSetzten(){}

    @Override
    public String getType() {
        return ANGESTELLTER;
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
