package Bank;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.NutzerDB;

import java.util.List;

public class Bank {
    public void NeuenKundenEinrichten(String name, String pin, String mail){
        Kunde kundeneu = new Kunde(name, mail, pin);
    }
    public void NeuenAngestelltenEinrichten(String name, String pin, String passwort){
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
    }
    Girokonto GirokontoEinrichten() {
        return new Girokonto();
    }
    boolean KontoLoeschen(Konto kontoLoeschen)
    {
        return true;
    }
    void AngestelltenLoeschen(){};
    /*Datenbankverbindung DatenbankverbindungGeben(){
        return null;
    };*/
    Kunde KundenSuchen(String name)
    {
        NutzerDB ndb = new NutzerDB();
        if (ndb.NutzerNameExistiert(name) == true){
            List<Nutzer> list = ndb.alleNutzerGeben();
            for (int i = 0; i < list.size(); i++) {
                if(name.equals(list.get(i).getName())){
                    return (Kunde) list.get(i);
                }
            }
            return null;
        }
        else{
            return null;
        }
    };
    /*int [] KontonummernFuerKundenGeben(){
        NutzerDB ndb = new NutzerDB();
        ;
    };*/
    Angestellter AngestellterSuchen(){
        return null;
    };
    /*String [] KundennamenGeben(){return null;};
    String [] AngestelltennamenGeben(){return null;};*/
    void NeuenKundenEinrichten(){};

    /*void KundenLoeschen(String name, String pin){
        NutzerDB ndb = new NutzerDB();
        if (ndb.PinRichtig(name, pin)){

        }
    };*/

    void NeuenAngestelltenEinrichten(String nameNeu, String pinNeu){
        new Angestellter(nameNeu, pinNeu);
    }

    Bank BankGeben(){return null;};//???
    Konto KontoSuchen(int kontonummer)
    {
        return null;
    };
    void Beenden(){};


}
