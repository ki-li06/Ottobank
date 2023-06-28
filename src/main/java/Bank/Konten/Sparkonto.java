package Bank.Konten;
import java.util.Timer;
import java.util.TimerTask;

import static util.Delay.delay;

public class Sparkonto extends Konto{
    double zinssatz = 0.03;

    public Sparkonto(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                kontostand = kontostand*(1+zinssatz);
                //System.out.println(kontostand);
            }
        }, 0, 5000);
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

    public static void main(String[] args) {
        Sparkonto sp = new Sparkonto();
        sp.Einzahlen(10.0);
        boolean b = true;
        while(b){
            System.out.println("kontostand: " + sp.KontostandGeben());
            delay(100);
            if (System.currentTimeMillis()%200000 == 0){
                b = false;
            }
        }
    }
}
