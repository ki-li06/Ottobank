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
}
