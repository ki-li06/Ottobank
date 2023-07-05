import Datenbank.LiteSQL.NutzerDB;

import static util.Delay.delay;

public class Main {
    public static void main(String[] args) {
        //Kunde k = new Kunde("Herbert Maier", "coole-mail@gmail.com", "notNull");
        NutzerDB ndb = new NutzerDB();
        //ndb.NutzerHinzufügen(k);
        ndb.AlleNutzerAusgeben();
        String mail = "huber@sepp.com";
        String pin = "123456";
        boolean b = ndb.PasswortRichtig(mail, pin);
        System.out.println("b: " + b);
    }
}
