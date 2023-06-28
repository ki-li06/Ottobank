package Bank.Konten;

public class Girokonto extends Konto{
    double überziehungsrahmen = 0.0;

    public boolean Abheben(double abhebebetrag){
        if (KontostandGeben()+ÜberziehungsrahmenGeben() - abhebebetrag < 0) {
        kontostand = kontostand - abhebebetrag;
        return true;
    } else {
        return false;
    }}
    private double ÜberziehungsrahmenBerechnen(double nettoeinkommen){
        überziehungsrahmen = nettoeinkommen*3;
        return überziehungsrahmen;
    }
    public double ÜberziehungsrahmenGeben(){
        double nettoeinkommen = 0.0; //pop up fenster mit eingabe feld für nettoeinkommen
        return ÜberziehungsrahmenBerechnen(nettoeinkommen);
    }
}
