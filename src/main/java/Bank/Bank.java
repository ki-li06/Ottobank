package Bank;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;
import Datenbank.LiteSQL.NutzerDB;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public void NeuenKundenEinrichten(String name, String pin, String mail){
        Kunde kundeneu = new Kunde(name, mail, pin);
    }
    public void NeuenAngestelltenEinrichten(String name, String pin, String passwort){
        if (passwort.equals("admin")){
            //bitte überarbeiten
            //Angestellter angestellterneu = new Angestellter(name, pin);
        }
        else {
            System.out.println("Das Passwort stimmt nicht mit dem Adminpasswort überein");
        }
    }
    int SparkontoEinrichten()
    {
        Sparkonto spark=new Sparkonto();
        return spark.KontonummerGeben();
    }
    int GirokontoEinrichten()
    {
        Girokonto girok =new Girokonto();
        return girok.KontonummerGeben();
    }

    boolean KontoLoeschen(Konto kontoLoeschen)
    {
        return true;
    }
    void AngestelltenLoeschen(){};
    /*Datenbankverbindung DatenbankverbindungGeben(){
        return null;
    };*/
    /*
        bitte überarbeiten - macht in unserem Sachzusammenhang keine Sinn,
        Mail ist das Individuelle Attribut
        (man muss sich nicht genau an die Angaben vom Buch halten)
     */
    Kunde KundenSuchen(String name)
    {
        /*
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
        */
        return null;
    };
    /*int [] KontonummernFuerKundenGeben(){
        NutzerDB ndb = new NutzerDB();
        ;
    };*/
    Angestellter AngestellterSuchen(){
        return null;
    };
    List<String> NutzernamenGeben()
    {
    NutzerDB ndb=new NutzerDB();
    List<Nutzer> list=ndb.alleNutzerGeben();
    List<String> ausgabe=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ausgabe.add(list.get(i).getName());
        }
        return ausgabe;
    }
    List<String> AngestelltennamenGeben()
    {
        NutzerDB ndb=new NutzerDB();
        List<Nutzer> list=ndb.alleNutzerGeben();
        List<String> ausgabe=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Angestellter)
            { ausgabe.add(list.get(i).getName());}
        }
        return ausgabe;
    }
    List<String> KundennamenGeben()
    {
        NutzerDB ndb=new NutzerDB();
        List<Nutzer> list=ndb.alleNutzerGeben();
        List<String> ausgabe=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Kunde)
            { ausgabe.add(list.get(i).getName());}
        }
        return ausgabe;
    }


    /*void KundenLoeschen(String name, String pin){
        NutzerDB ndb = new NutzerDB();
        if (ndb.PinRichtig(name, pin)){

        }
    };*/



    Bank BankGeben(){return null;};//???
    Konto KontoSuchen(int kontonummer)
    {
        return null;
    };
    void Beenden(){};


}
