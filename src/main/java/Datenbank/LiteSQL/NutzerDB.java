package Datenbank.LiteSQL;

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

    private void NutzerHinzufügen(String Name, int ID, String PIN) {
        connect();
        String command =
                "INSERT INTO NutzerDB (Name, ID, PIN) VALUES ("
                        + "'" + Name + "', "
                        + "'" + ID + "', "
                        + "'" + PIN + "');";
        onUpdate(command);
        disconnect();
        System.out.println("Nutzer {'Name'=" + Name + ", 'ID'=" + ID + ", 'PIN'=" + PIN + "} hinzugefügt");
    }
    public void NutzerHinzufügen(String Name, String PIN){
        if(NutzerNameExistiert(Name)){
            System.out.println("Es existiert bereits ein Nutzer mit {'Name'=" + Name + "}");
            return;
        }
        int id;
        do{
            id = RandomInt(1, 99999);
        }
        while (NutzerIdExistiert(id));
        NutzerHinzufügen(Name, id, PIN);

    }

    /**
     * @param Name Name des Nutzers
     * @param PIN Pin des gleichen Nutzers, wird überprüft
     * @return gibt die NutzerID zurück
     */
    public int PinRichtig(String Name, String PIN){
        connect();
        String cmd = "SELECT ID FROM NutzerDB WHERE "
                + "Name = '" + Name + "' AND "
                + "PIN = '" + PIN + "';";
        ResultSet rs = onQuery(cmd);
        try {
            return rs.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Es wird überprüft, ob bereits ein Nutzer mit diesem Namen existiert.
     * Die Datenbank ist nicht darauf ausgelegt, zwei Nutzer mit dem gleichen Namen anzulegen.
     * @return
     */
    public boolean NutzerNameExistiert(String name){
        connect();
        String command = "SELECT Name FROM NutzerDB;";
        ResultSet rs = onQuery(command);
        List<String> list = new ArrayList<>();
        try{
            while (rs.next()){
                String s = rs.getString("Name");
                list.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return list.contains(name);
    }
    private boolean NutzerIdExistiert (int id){
        connect();
        String command = "SELECT ID FROM NutzerDB;";
        ResultSet rs = onQuery(command);
        List<Integer> list = new ArrayList<>();
        try{
            while (rs.next()){
                int i = rs.getInt("ID");
                list.add(i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        return list.contains(id);
    }

    public void TabelleAusgeben(){
        System.out.println("---\nNutzerDatenbank wird ausgegeben:");
        System.out.println(format("Name", 20) + " | " + format("ID", 5) + " | " + format("PIN", 10));
        System.out.println("-".repeat(41));
        connect();
        String command = "SELECT * FROM NutzerDB;";
        ResultSet rs = onQuery(command);
        try {
            while (rs.next()) {
                String name = rs.getString("Name");
                String id = String.valueOf(rs.getInt("ID"));
                String pin = rs.getString("PIN");
                System.out.println(format(name, 20) + " | " + format(id, 5) + " | " + format(pin, 10));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
        System.out.println("---");
    }

}
