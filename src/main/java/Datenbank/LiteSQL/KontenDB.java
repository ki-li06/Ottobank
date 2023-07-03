package Datenbank.LiteSQL;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;
import Bank.Nutzer.Kunde;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Bank.Konten.Girokonto.GIROKONTO;
import static Bank.Konten.Sparkonto.SPARKONTO;
import static util.Zufall.RandomInt;

/**
 * KontenDB TABELLE in der SQL-DATENBANK
 * (Integer) Nummer
 * (Numeric/Double) Stand (Kontostand)
 * (String) BesitzerMail
 * (String) Type
 * (Numeric/Double) SpecialDouble (Sparkonto - Zinssatz; Girokonto - Überziehungsrahmen)
 */
public class KontenDB extends LiteSQL{


    public KontenDB(){
        super("KontenDB");
    }

    /**
     * fügt ein neues Konto in die Datenbank ein
     * @param konto Konto das eingefügt werden soll; KontoNummer wird automatisch gesetzt (bitte -1 lassen)
     */
    public void KontoHinzufügen(Konto konto){
        if(konto.KontonummerGeben() != -1){
            System.out.println("Fehler die Kontonummer wurde schon auf '" + konto.KontonummerGeben() + "' gesetzt. (Kontonummer ist einmalig)");
        }
        int nummer;
        do{
            nummer = RandomInt(100000, 999999);
        }
        while (NummerSchonBelegt(nummer));
        konto.setKontonummer(nummer);

        String type = konto.getType();

        double specialDouble = konto.getSpecialDouble();

        connect();
        String cmd =
                "INSERT INTO TABLE (Nummer, Stand, BesitzerMail, Type, SpecialDouble) " +
                        "VALUES ('PARAM_Nummer', 'PARAM_Stand', 'PARAM_BesitzerMail', 'PARAM_Type', 'PARAM_SpecialDouble');";
        cmd = cmd
                .replace("PARAM_Nummer", String.valueOf(konto.KontonummerGeben()))
                .replace("PARAM_Stand", String.valueOf(konto.KontostandGeben()))
                .replace("PARAM_BesitzerMail", konto.EigentümerGeben().getEMail())
                .replace("PARAM_Type", type)
                .replace("PARAM_SpecialDouble", String.valueOf(specialDouble));

        onUpdate(cmd);
        disconnect();

        System.out.println("Konto " + konto + " hinzugefügt");
    }

    /**
     * überprüft, ob die eingegebene Nummer schon in der Datenbank belegt ist
     * @param nummer die Kontonummer
     */
    private boolean NummerSchonBelegt(int nummer){
        connect();
        String command = "SELECT Nummer FROM TABLE;";
        ResultSet rs = onQuery(command);
        List<Integer> list = new ArrayList<>();
        try{
            while (rs.next()){
                int i = rs.getInt("Nummer");
                list.add(i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return list.contains(nummer);
    }

    /**
     * gibt das Konto zur passenden Kontonummer zurück
     * @param nummer die Kontonummer
     */
    public Konto getKontoVonKontonummer(int nummer){
        if(!NummerSchonBelegt(nummer)){
            System.out.println("FEHLER - getKontoVonKontonummer - die Nummer '" + nummer + "' ist nicht belegt");
            return null;
        }
        String cmd = "SELECT * FROM TABLE WHERE Nummer = 'PARAM_Nummer'";
        cmd = cmd
                .replace("PARAM_Nummer", String.valueOf(nummer));
        connect();
        ResultSet rs = onQuery(cmd);
        try{
            double stand = rs.getDouble("Stand");
            String besitzerMail = rs.getString("BesitzerMail");
            Kunde besitzer = (Kunde) (new NutzerDB()).NutzerZuMail(besitzerMail);
            String type = rs.getString("Type");
            double specialDouble = rs.getDouble("SpecialDouble");
            disconnect();
            if(type.equals(SPARKONTO)){
                return new Sparkonto(nummer, stand, besitzer, specialDouble);
            }
            else if(type.equals(GIROKONTO)){
                return new Girokonto(nummer, stand, besitzer, specialDouble);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return null;
    }

    /**
     * überschreibt die Attribute des neuen Kontos auf das in der Datenbank gespeicherte Konto
     * @param nummer die Kontonummer des "alten" Kontos
     * @param neuesKonto die Attribute dieses Objektes werden übernommen
     */
    public void KontoÄndern (int nummer, Konto neuesKonto){
        if(!NummerSchonBelegt(nummer)){
            System.out.println("FEHLER - KontoÄndern - Kontonummer nicht belegt");
            return;
        }
        else if(nummer != neuesKonto.KontonummerGeben()){
            System.out.println("FEHLER - KontoÄndern - die eingegebene Kontonummer stimmt nicht mit dem Konto überein");
            return;
        }

        String type = neuesKonto.getType();

        double specialDouble = neuesKonto.getSpecialDouble();

        String cmd = "UPDATE TABLE SET "
                + "Stand = PARAM_Stand, "
                + "BesitzerMail = 'PARAM_BesitzerMail', "
                + "Type = 'PARAM_Type', "
                + "SpecialDouble = PARAM_SpecialDouble "
                + "WHERE Nummer = PARAM_Nummer";
        cmd = cmd
                .replace("PARAM_Nummer", String.valueOf(nummer))
                .replace("PARAM_Stand", String.valueOf(neuesKonto.KontostandGeben()))
                .replace("PARAM_BesitzerMail", neuesKonto.EigentümerGeben().getEMail())
                .replace("PARAM_Type", type)
                .replace("PARAM_SpecialDouble", String.valueOf(specialDouble));
        System.out.println("cmd: '" + cmd + "'");
        connect();
        onUpdate(cmd);
        disconnect();
        System.out.println("Konto mit der " + nummer + " wurde geändert");
    }

    /**
     * löscht ein Konto
     * @param nummer die Kontonummer des Kontos
     */
    public void KontoLöschen(int nummer){
        String cmd = "DELETE FROM TABLE WHERE Nummer = 'PARAM_Nummer'";
        cmd = cmd
                .replace("PARAM_Nummer", String.valueOf(nummer));
        connect();
        onUpdate(cmd);
        disconnect();
        System.out.println("Konto mit der Kontonummer '" + nummer + "' gelöscht");
    }


    /**
     * gibt eine List<Konto> an allen Konten zurück
     */
    public List<Konto> alleKontenGeben(){
        String cmd = "SELECT * FROM TABLE";
        connect();
        ResultSet rs = onQuery(cmd);
        List<Konto> ausgabe = new ArrayList<>();
        try{
            while(rs.next()) {
                int nummer = rs.getInt("Nummer");
                double stand = rs.getDouble("Stand");
                String besitzerMail = rs.getString("BesitzerMail");
                Kunde besitzer = (Kunde) (new NutzerDB()).NutzerZuMail(besitzerMail);
                String type = rs.getString("Type");
                double specialDouble = rs.getDouble("SpecialDouble");
                Konto k = null;
                if (type.equals(SPARKONTO)) {
                    k = new Sparkonto(nummer, stand, besitzer, specialDouble);
                } else if (type.equals(GIROKONTO)) {
                    k = new Girokonto(nummer, stand, besitzer, specialDouble);
                }
                ausgabe.add(k);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return ausgabe;
    }

}
