package Bank.Nutzer;

import Bank.Bank;
import Bank.Konten.Girokonto;
import Bank.Konten.Sparkonto;

public class Angestellter extends Nutzer{
    public Angestellter(String nameneu, int pinneu) {
        name = nameneu;
        pin = pinneu;
    }
    int SparkontoEinrichten()
    {

        return 0;
    }
    int GirokontoEinrichten()
    {
        new Girokonto();
    }
    boolean KontoLoeschen()
    {
        return false;
    }

    String AngestellterLoeschen(String)
    {
        return "";
    }

    String NeuenKundeEinrichten(String nameNeu, int pinNeu)
    {
        new Kunde(nameNeu, pinNeu);
    }
    void KontoSetzten()
}
