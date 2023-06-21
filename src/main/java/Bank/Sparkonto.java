package Bank;

public class Sparkonto extends Konto{
    double zinssatz = 0.03;

    void Verzinsen(){}
    double ZinssatzGeben(){return zinssatz;}
    boolean Abheben (double abhebebetrag) {
        if (KontostandGeben() - abhebebetrag < 0) {
            kontostand = kontostand - abhebebetrag;
            return true;
        } else {
            return false;
        }
    }
}
