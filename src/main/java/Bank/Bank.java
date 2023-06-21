package Bank;

public class Bank {
    public static void NeuenKundenEinrichten(String name, int pin){
        Kunde kundeneu = new Kunde(name, pin);
    }
    public static void NeuenAngestelltenEinrichten(String name, int pin, String passwort){
        if (passwort.equals("admin")){
            Angestellter angestellterneu = new Angestellter(name, pin);
        }
        else {
            System.out.println("Das Passwort stimmt nicht mit dem adminpasswort überein");
        }
    }
    int SparkontoEinrichten()
    {
        new Sparkonto();
        return 0;
    };
    int GirokontoEinrichten()
    {
        new Girokonto();
        return 0;
    };
    boolean KontoLoeschen()
    {
        return true;
    };
    void AngestelltenLoeschen(){};
    /*Datenbankverbindung DatenbankverbindungGeben(){
        return null;
    };*/
    Kunde KundenSuchen(){
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
    void NeuenAngestelltenEinrichten(){};

    Bank BankGeben(){return null;};//???
    Konto KontoSuchen(int kontonummer)
    {
        return null;
    };
    void Beenden(){};


}
