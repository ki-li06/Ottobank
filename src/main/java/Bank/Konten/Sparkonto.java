package Bank.Konten;
import Bank.Nutzer.Kunde;
import Datenbank.LiteSQL.KontenDB;
import util.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static Bank.Konten.Konto.TYPE.SPARKONTO;
import static util.Round.round;

public class Sparkonto extends Konto{
    private static List<Integer> zinssatzTimer = new ArrayList<>();

    double zinssatz = 0.03;

    public Sparkonto(){
        super();
    }

    public Sparkonto(double kontostand, Kunde besitzer){
        super(kontostand, besitzer);
    }

    public Sparkonto(double kontostand, Kunde besitzer, double zinssatz) {
        super(kontostand, besitzer);
        this.zinssatz = zinssatz;
        startTimerZinsen();
    }

    public Sparkonto(int kontonummer, double kontostand, Kunde besitzer, double zinssatz) {
        this(kontostand, besitzer, zinssatz);
        this.kontonummer = kontonummer;
        startTimerZinsen();
    }

    private void startTimerZinsen(){
        if(!zinssatzTimer.contains(kontonummer) && kontonummer != -1) {
            zinssatzTimer.add(kontonummer);
            //System.out.println("zinssatzTimer: " + zinssatzTimer);
            int period = 60000;
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    kontostand *= (1 + zinssatz);
                    kontostand = round(kontostand, 4);
                    //System.out.println("neuer Kontostand des Sparkonto " + kontonummer + " : " + kontostand + " (aktuelle Zeit: " + System.currentTimeMillis() + ")");
                    KontenDB kdb = new KontenDB();
                    kdb.KontoÄndern(kontonummer, Sparkonto.this);

                }
            }, period, period);
        }
    }

    public double ZinssatzGeben(){return zinssatz;}

    @Override
    public boolean AbhebenErlaubt(double abhebebetrag) {
        return KontostandGeben() - abhebebetrag > 0;
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
                ", besitzer=" + besitzer.getEMail() +
                ", zinssatz=" + zinssatz +
                '}';
    }
}
