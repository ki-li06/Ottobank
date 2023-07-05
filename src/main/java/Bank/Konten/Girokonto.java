package Bank.Konten;

import Bank.Nutzer.Kunde;

public class Girokonto extends Konto{
    public static final String GIROKONTO = "Girokonto";
    double überziehungsrahmen = 0.0;

    public Girokonto(){
        super();
    }

    public Girokonto(double kontostand, Kunde besitzer, double überziehungsrahmen) {
        super(kontostand, besitzer);
        this.überziehungsrahmen = überziehungsrahmen;
    }

    public Girokonto(int kontonummer, double kontostand, Kunde besitzer, double überziehungsrahmen) {
        super(kontonummer, kontostand, besitzer);
        this.überziehungsrahmen = überziehungsrahmen;
    }

    public boolean Abheben(double abhebebetrag){
        if (KontostandGeben()+ÜberziehungsrahmenGeben() - abhebebetrag < 0)
        {
            kontostand = kontostand - abhebebetrag;
            return true;
        }
        else {
            return false;
        }
    }
    private double ÜberziehungsrahmenBerechnen(double nettoeinkommen){
        überziehungsrahmen = nettoeinkommen*3;
        return überziehungsrahmen;
    }
    public double ÜberziehungsrahmenGeben(){
        double nettoeinkommen = 0.0; //pop up fenster mit eingabe feld für nettoeinkommen
        return ÜberziehungsrahmenBerechnen(nettoeinkommen);
    }

    @Override
    public double getSpecialDouble() {
        return überziehungsrahmen;
    }

    @Override
    public String getType() {
        return GIROKONTO;
    }

    @Override
    public String toString() {
        return "Girokonto{" +
                "überziehungsrahmen=" + überziehungsrahmen +
                ", kontostand=" + kontostand +
                ", kontonummer=" + kontonummer +
                ", besitzer=" + besitzer +
                '}';
    }
}
