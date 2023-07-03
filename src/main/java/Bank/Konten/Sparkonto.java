package Bank.Konten;
import Bank.Nutzer.Kunde;

import java.util.Timer;
import java.util.TimerTask;

public class Sparkonto extends Konto{
    public static final String SPARKONTO = "Sparkonto";
    double zinssatz = 0.03;

    public Sparkonto(){
        super();
    }

    public Sparkonto(double kontostand, Kunde besitzer, double zinssatz) {
        super(kontostand, besitzer);
        this.zinssatz = zinssatz;
    }

    public Sparkonto(int kontonummer, double kontostand, Kunde besitzer, double zinssatz) {
        this(kontostand, besitzer, zinssatz);
        this.kontonummer = kontonummer;
    }

    private void startTimerZinsen(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                kontostand = kontostand*(1+zinssatz);
                System.out.println("neuer Kontostand des Kontos " +kontonummer + " : " + kontostand);
            }
        }, 0, 60000);
    }

    public double ZinssatzGeben(){return zinssatz;}
    public boolean Abheben(double abhebebetrag) {
        if (KontostandGeben() - abhebebetrag < 0) {
            kontostand = kontostand - abhebebetrag;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double getSpecialDouble() {
        return zinssatz;
    }

    @Override
    public String getType() {
        return SPARKONTO;
    }

    @Override
    public String toString() {
        return "Sparkonto{" +
                "kontostand=" + kontostand +
                ", kontonummer=" + kontonummer +
                ", besitzer=" + besitzer +
                ", zinssatz=" + zinssatz +
                '}';
    }
}
