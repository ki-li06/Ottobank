package Bank;
import java.util.Random;

public abstract class Konto {
    protected double kontostand = 100.00;
    protected long kontonummer;
    protected Kunde besitzer;
    Konto(){
        Random random = new Random();
        long number = random.nextLong();
        number = Math.abs(number);
        number = number % 10000000000L;
        kontonummer = number;


    }

    boolean Abheben(double betrag){return false;}
    int KontonummerGeben (){return 0;}
    double KontostandGeben (){return kontostand;}
    String EigentümerGeben(){return besitzer.name;}
    void Einzahlen(double einzahlbetrag){}
}
