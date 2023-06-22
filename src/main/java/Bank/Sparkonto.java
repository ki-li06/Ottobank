package Bank;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Sparkonto extends Konto{
    double zinssatz = 0.03;

    Sparkonto(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                kontostand = kontostand*(1+zinssatz);
                System.out.println(kontostand);
            }
        }, 0, 60000);
    }
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
