package Bank.Nutzer;

import Bank.Bank;
import Bank.Konten.Girokonto;
import Bank.Konten.Sparkonto;

public class Angestellter extends Nutzer{
    public Angestellter(String nameneu, String pinneu) {
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

    String NeuenKundeEinrichten(String nameNeu, String pinNeu)
    {
        //new Kunde(nameNeu, pinNeu);
        return "";
    }
    void KontoSetzten(){}
}
