package Bank.Konten;
import java.util.Timer;
import java.util.TimerTask;

public class Sparkonto extends Konto{
    double zinssatz = 0.03;

    public Sparkonto(){
        super();
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
    public String toString() {
        return "Sparkonto{" +
                "kontostand=" + kontostand +
                ", kontonummer=" + kontonummer +
                ", besitzer=" + besitzer +
                ", zinssatz=" + zinssatz +
                '}';
    }
}
