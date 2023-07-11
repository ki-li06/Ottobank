package Datenbank.LiteSQL;

import Bank.Nutzer.Angestellter;
import Bank.Nutzer.Kunde;
import Bank.Nutzer.Nutzer;

import javax.print.DocFlavor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Bank.Nutzer.Nutzer.TYPE;
import static util.StringFormat.format;

/**
 * NutzerDB TABELLE in der SQL-DATENBANK
 * (String) Name
 * (String) Mail
 * (String) PIN
 * (String) Type
 */
public class NutzerDB extends LiteSQL{
    private static final String CONST_ANGESTELLTER = "ANGESTELLTER";
    private static final String CONST_KUNDE = "KUNDE";


    public NutzerDB(){
        super("NutzerDB");
    }


    /**
     * fügt einen neuen Nutzer in die Datenbank ein (Mail bitte davor checken)
     * @param nutzer dieser Nutzer samt Attribute wird hinzugefügt
     */
    public void NutzerHinzufügen(Nutzer nutzer){
        String name = nutzer.getName();
        String mail = nutzer.getEMail();
        String pin = nutzer.getPin();
        String type = getConstOfType(nutzer.getType());

        if(MailBelegt(mail)){
            System.out.println("FEHLER - MailAdresse '" + mail + "' schon belegt");
            return;
        }
        connect();
        String cmd = "INSERT INTO TABLE (Name, Mail, PIN, Type) VALUES ('PARAM_NAME', 'PARAM_MAIL', 'PARAM_PIN', 'PARAM_TYPE');";
        cmd = cmd
                .replace("PARAM_NAME", name)
                .replace("PARAM_MAIL", String.valueOf(mail))
                .replace("PARAM_PIN", pin)
                .replace("PARAM_TYPE", type);
        onUpdate(cmd);
        disconnect();
        System.out.println(nutzer + " hinzugefügt");
    }

    /**
     * überprüft, ob diese EMail Adresse schon in der Datenbank vorhanden ist
     * @param mail zu überprüfende Mail-Adresse
     */
    public boolean MailBelegt(String mail){
        String cmd = "SELECT Mail FROM TABLE";
        connect();
        ResultSet rs = onQuery(cmd);
        List<String> adresssen = new ArrayList<>();
        try{
            while(rs.next()){
                String string = rs.getString("Mail");
                adresssen.add(string);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return adresssen.contains(mail);
    }

    /**
     * gibt einen Nutzer zur passenden Mail Adresse zurück
     * @param mail die Mail-Adresse des Nutzers
     * @return gleich null, wenn die Adresse nicht vorhanden ist
     */
    public Nutzer NutzerZuMail(String mail){
        if(!MailBelegt(mail)){
            System.out.println("FEHLER - NutzerZuMail - die Mail '" + mail + "' ist nicht belegt");
            return null;
        }
        String cmd = "SELECT * FROM TABLE WHERE Mail = 'PARAM_MAIL'";
        cmd = cmd
                .replace("PARAM_MAIL", mail);
        connect();
        ResultSet rs = onQuery(cmd);
        try{
            String name = rs.getString("Name");
            //System.out.println("name: " + name);
            String pin = rs.getString("PIN");
            //System.out.println("pin: " + pin);
            String type = rs.getString("Type");
            //System.out.println("type: " + type);
            disconnect();
            if(type.equals(CONST_ANGESTELLTER)){
                return new Angestellter(name, mail, pin);
            }
            else if(type.equals(CONST_KUNDE)){
                return new Kunde(name, mail, pin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return null;
    }

    /**
     * überprüft, ob das Passwort 'richtig' zu diesem Nutzer ist
     * @param mail die Mail Adresse des Nutzers
     * @param pin die Pin des Nutzers
     */
    public boolean PasswortRichtig(String mail, String pin){
        if(!MailBelegt(mail)){
            return false;
        }
        String cmd = "SELECT PIN FROM TABLE WHERE Mail = 'PARAM_MAIL'";
        cmd = cmd
                .replace("PARAM_MAIL", mail);
        connect();
        ResultSet rs = onQuery(cmd);
        try{
            String string = rs.getString("PIN");
            disconnect();
            return string.equals(pin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return false;
    }


    /**
     * gibt eine List an allen Nutzer zurück, die in der Datenbank gespeichert sind (mit instanceof Angestellter/Kunde checken)
     */
    public List<Nutzer> alleNutzerGeben(){
        String cmd = "SELECT * FROM TABLE";
        connect();
        ResultSet rs = onQuery(cmd);
        List<Nutzer> list = new ArrayList<>();
        try{
            while(rs.next()){
                String name = rs.getString("Name");
                String mail = rs.getString("Mail");
                String pin = rs.getString("PIN");
                String type = rs.getString("Type");
                Nutzer n = null;
                if(type.equals(CONST_KUNDE)){
                    n = new Kunde(name, mail, pin);
                }
                else if(type.equals(CONST_ANGESTELLTER)){
                    n = new Angestellter(name, mail, pin);
                }
                list.add(n);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return list;
    }

    /**
     * ändert die Pin eines Kontos (altes Passwort davor checken)
     * @param mail die Mail Adresse des Nutzers
     * @param pinNeu die neue Pin des Nutzers
     */
    public void PinÄndern(String mail, String pinNeu){
        String cmd = "UPDATE TABLE SET PIN = 'PARAM_PIN' WHERE Mail = 'PARAM_MAIL'";
        cmd = cmd
                .replace("PARAM_PIN", pinNeu)
                .replace("PARAM_MAIL", mail);
        connect();
        onUpdate(cmd);
        disconnect();
        System.out.println("Pin des Nutzers mit der Mail '" + mail + "' auf '" + pinNeu + "' geändert");
    }

    /**
     * ändert den Namen (Nutzer/Angestellter) des Nutzers
     */
    public void NameÄndern(String mail, String nameNeu){
        String cmd = "UPDATE TABLE SET Name = 'PARAM_Name' WHERE Mail = 'PARAM_Mail'";
        cmd = cmd
                .replace("PARAM_Name", nameNeu)
                .replace("PARAM_Mail", mail);
        connect();
        onUpdate(cmd);
        disconnect();
        System.out.println("Name des Nutzers mit der Mail '" + mail + "' auf '" + nameNeu + "' geändert");
    }

    /**
     * löscht einen Nutzer
     * @param mail die EMail Adresse des Nutzers
     */
    public void NutzerLöschen (String mail){
        String cmd = "DELETE FROM TABLE WHERE Mail = 'PARAM_MAIL'";
        cmd = cmd
                .replace("PARAM_MAIL", mail);
        connect();
        onUpdate(cmd);
        disconnect();
        System.out.println("Nutzers mit der Mail '" + mail + "' gelöscht");

    }


    public void AlleNutzerAusgeben(){
        List<Nutzer> list = new ArrayList<>(alleNutzerGeben());
        System.out.println("-".repeat(100));
        System.out.println("AUSGABE NutzerDB");
        System.out.println("Name" + " ".repeat(21) + "Mail" + " ".repeat(21) + "PIN" + " ".repeat(22) + "Type" + " ".repeat(21));
        for (Nutzer n : list) {
            String s = "";
            s += format(n.getName(), 25);
            s += format(n.getEMail(), 25);
            s += format(n.getPin(), 25);
            String type = getConstOfType(n.getType());
            s += type;
            System.out.println(s);

        }
        System.out.println("-".repeat(100));
    }

    public static String getConstOfType(TYPE type){
        if(type == TYPE.ANGESTELLTER){
            return CONST_ANGESTELLTER;
        }
        else if(type == TYPE.KUNDE){
            return CONST_KUNDE;
        }
        return null;

    }
}
