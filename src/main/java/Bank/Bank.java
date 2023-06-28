package Bank;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;

public class Bank {
    public void NeuenKundenEinrichten(String name, int pin){
        Kunde kundeneu = new Kunde(name, pin);
    }
    public void NeuenAngestelltenEinrichten(String name, int pin, String passwort){
        if (passwort.equals("admin")){
            Angestellter angestellterneu = new Angestellter(name, pin);
        }
        else {
            System.out.println("Das Passwort stimmt nicht mit dem Adminpasswort überein");
        }
    }
    int SparkontoEinrichten()
    {
        new Sparkonto();
        return 0 ;
    };
    Girokonto GirokontoEinrichten() {
        return new Girokonto();
    }
    boolean KontoLoeschen(Konto kontoLoeschen)
    {
        return true;
    };
    void AngestelltenLoeschen(){};
    /*Datenbankverbindung DatenbankverbindungGeben(){
        return null;
    };*/
    Kunde KundenSuchen(String name)
    {
        return null;
    };
    /*int [] KontonummernFuerKundenGeben(){
        return 0;
    };*/
    Angestellter AngestellterSuchen(){
        return null;
    };
    /*String [] KundennamenGeben(){return null;};
    String [] AngestelltennamenGeben(){return null;};*/
    void NeuenKundenEinrichten(){};
    void KundenLoeschen(){};
    void NeuenAngestelltenEinrichten(String nameNeu, int pinNeu){
        new Angestellter(nameNeu, pinNeu);
    }

    Bank BankGeben(){return null;};//???
    Konto KontoSuchen(int kontonummer)
    {
        return null;
    };
    void Beenden(){};


}
