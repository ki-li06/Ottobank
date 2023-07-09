package Bank.Konten;
import Bank.Nutzer.Kunde;
import Datenbank.LiteSQL.KontenDB;

import java.util.Timer;
import java.util.TimerTask;

import static Bank.Konten.Konto.TYPE.SPARKONTO;

public class Sparkonto extends Konto{
    double zinssatz = 0.03;

    public Sparkonto(){
        super();
    }

    public Sparkonto(double kontostand, Kunde besitzer, double zinssatz) {
        super(kontostand, besitzer);
        this.zinssatz = zinssatz;
        startTimerZinsen();
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
                kontostand *= (1+zinssatz);
                //System.out.println("neuer Kontostand des Sparkonto " +kontonummer + " : " + kontostand);

            }
        }, 0, 5000);
    }

    public double ZinssatzGeben(){return zinssatz;}

    @Override
    public boolean Abheben(double abhebebetrag) {
        boolean result;
        if (KontostandGeben() - abhebebetrag < 0) {
            kontostand = kontostand - abhebebetrag;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public double getSpecialDouble() {
        return zinssatz;
    }

    @Override
    public TYPE getType() {
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
