package Bank.Konten;

import Bank.Nutzer.Kunde;

public class Girokonto extends Konto{
    double nettoeinkommen = 0.0;

    public Girokonto(){
        super();
    }

    public Girokonto(double kontostand, Kunde besitzer, double nettoeinkommen) {
        super(kontostand, besitzer);
        this.nettoeinkommen = nettoeinkommen;
    }

    public Girokonto(int kontonummer, double kontostand, Kunde besitzer, double nettoeinkommen) {
        super(kontonummer, kontostand, besitzer);
        this.nettoeinkommen = nettoeinkommen;
    }


    @Override
    public boolean AbhebenErlaubt(double abhebebetrag){
        return KontostandGeben() - abhebebetrag >= -ÜberziehungsrahmenGeben();
    }
    private double ÜberziehungsrahmenBerechnen(double nettoeinkommen){
        return nettoeinkommen*3;
    }
    public double ÜberziehungsrahmenGeben(){
        return ÜberziehungsrahmenBerechnen(nettoeinkommen);
    }

    @Override
    public double getSpecialDouble() {
        return nettoeinkommen;
    }

    @Override
    public TYPE getType() {
        return TYPE.GIROKONTO;
    }

    @Override
    public String toString() {
        return "Girokonto{" +
                "überziehungsrahmen=" + nettoeinkommen +
                ", kontostand=" + kontostand +
                ", kontonummer=" + kontonummer +
                ", besitzer=" + besitzer.getEMail() +
                ", einkommen=" + nettoeinkommen +
                '}';
    }
}
