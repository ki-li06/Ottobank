package Datenbank.LiteSQL;

import Bank.Nutzer.Nutzer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.StringFormat.format;
import static util.Zufall.RandomInt;

public class NutzerDB extends LiteSQL{

    public NutzerDB(){
        super();
    }


    public static final String ANGESTELLTER = "ANGESTELLTER";
    public static final String KUNDE = "KUNDE";

    /**
     * fügt einen neuen Nutzer in die Datenbank ein
     * @param name der Nutzername
     * @param mail die MailAdresse des Nutzers (individuell pro Nutzer --> EMailExistiert)
     * @param pin die PIN/ das Passwort
     * @param type der Typ des Nutzers (entweder NutzerDB.ANGESTELLTER oder NutzerDB.KUNDE)
     */
    public void NutzerHinzufügen(String name, String mail, String pin, String type){
        connect();
        String cmd = "INSERT INTO NutzerDB (Name, Mail, PIN, Type) VALUES ('PARAM_NAME', 'PARAM_ID', 'PARAM_PIN');";
        cmd = cmd
                .replace("PARAM_NAME", name)
                .replace("PARAM_MAIL", String.valueOf(mail))
                .replace("PARAM_PIN", pin)
                .replace("PARAM_TYPE", type);
        onUpdate(cmd);
        disconnect();
        System.out.println("Nutzer {'name'=" + name + ", 'mail'=" + mail + ", 'pin'=" + pin + ", 'type'=" + type + "}f hinzugefügt");
    }

    /**
     * überprüft, ob diese EMail Adresse schon in der Datenbank vorhanden ist
     * @param mail zu überprüfende Mail-Adresse
     * @return
     */
    public boolean EMailExistiert(String mail){
        return false;
    }

    /**
     * gibt einen Nutzer zur passenden Mail Adresse zurück
     * @param mail die Mail-Adresse des Nutzers
     * @return gleich null, wenn die Adresse nicht vorhanden ist
     */
    public Nutzer NutzerZuMail(String mail){
        return null;
    }

    /**
     * überprüft, ob das Passwort 'richtig' zu diesem Nutzer ist
     * @param Mail die Mail Adresse des Nutzers
     * @param PIN die Pin des Nutzers
     */
    public boolean PasswortRichtig(String Mail, String PIN){
        return false;
    }

    /**
     * gibt zurück, ob dieser NutzerName schon in der Datenbank existiert
     * @param name der name
     */
    public boolean NutzerNameExistiert(String name){
        return false;
    }

    /**
     * gibt zurück, ob diese MailAdresse schon in der Datenbank existiert
     * @param mail der E-Mail Adresse
     */
    public boolean MailAdresseExistiert(String mail){
        return false;
    }

    /**
     * gibt eine List an allen Nutzer zurück, die in der Datenbank gespeichert sind (mit instanceof Angestellter/Kunde checken)
     */
    public List<Nutzer> alleNutzerGeben(){
        return null;
    }

    /**
     * ändert den NutzerName eines Kontos
     * @param Mail die Mail Adresse des Kontos
     * @param PIN der Pin des Kontos
     * @param nameNeu der neue Name des Kontos
     */
    public void NutzerNameÄndern(String Mail, String PIN, String nameNeu){

    }

    /**
     * löscht ein Konto
     * @param Mail die EMail Adresse des Kontos
     */
    public void NutzerLöschen (String Mail){

    }

}
