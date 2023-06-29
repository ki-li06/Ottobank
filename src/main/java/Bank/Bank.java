package Bank;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.KontenDB;
import Datenbank.LiteSQL.NutzerDB;

import java.util.List;

public class Bank {
    public void NeuenKundenEinrichten(String name, String pin, String mail){
        Kunde kundeneu = new Kunde(name, mail, pin);
    }
    public void NeuenAngestelltenEinrichten(String name, String pin, String mail, String passwort){
        if (passwort.equals("admin")){
            Angestellter angestellterneu = new Angestellter(name, pin, mail);
        }
        else {
            System.out.println("Das Passwort stimmt nicht mit dem Adminpasswort überein");
        }
    }
    Sparkonto SparkontoEinrichten()
    {
        return new Sparkonto();
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
                if(name.equals(list.get(i).getName())&& list.get(i) instanceof Angestellter){
                    return (Kunde) list.get(i);
                }
            }
            return null;
        }
        else{
            return null;
        }
    }
    /*int [] KontonummernFuerKundenGeben(){
        NutzerDB ndb = new NutzerDB();
        ;
    };*/
    Angestellter AngestellterSuchen(String name){
        NutzerDB ndb = new NutzerDB();
        if (ndb.NutzerNameExistiert(name) == true){
            List<Nutzer> list = ndb.alleNutzerGeben();
            for (int i = 0; i < list.size(); i++) {
                if(name.equals(list.get(i).getName())&& list.get(i) instanceof Kunde){
                    return (Angestellter) list.get(i);
                }
            }
            return null;
        }
        else{
            return null;
        }
    }
    /*String [] KundennamenGeben(){return null;};
    String [] AngestelltennamenGeben(){return null;};*/

    void KundenLoeschen(String mail){
        NutzerDB ndb = new NutzerDB();
        ndb.NutzerLöschen(mail);
    }


    Konto KontoSuchen(int kontonummer)
    {
        KontenDB kdb = new KontenDB();
        return kdb.getKontoVonKontonummer(kontonummer);
    }

    void Beenden(){};


}
