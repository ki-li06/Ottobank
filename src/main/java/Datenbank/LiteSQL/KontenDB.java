package Datenbank.LiteSQL;

import Bank.Konten.Girokonto;
import Bank.Konten.Konto;
import Bank.Konten.Sparkonto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.Zufall.RandomInt;

public class KontenDB extends LiteSQL{
    public final static String SPARKONTO = "Sparkonto";
    public final static String GIROKONTO = "Girokonto";


    public KontenDB(){
        super();
    }

    public void KontoHinzufügen(Konto konto){
        /*int nummer;
        do{
            nummer = RandomInt(100000, 999999);
        }
        while (NummerSchonBelegt(nummer));
        konto.setKontonummer(nummer);

        String type = "---";
        if(konto instanceof Girokonto){
            type = GIROKONTO;
        }
        else if(konto instanceof Sparkonto){
            type = SPARKONTO;
        }

        double specialDouble = -1;
        if(konto instanceof Girokonto gk) {
            specialDouble = gk.ÜberziehungsrahmenGeben();
        }
        else if(konto instanceof Sparkonto sk){
            specialDouble = sk.ZinssatzGeben();
        }

        connect();
        String cmd =
                "INSERT INTO KontoDB (Nummer, Stand, BesitzerID, Type, SpecialDouble) " +
                        "VALUES ('PARAM_Nummer', 'PARAM_Stand', 'PARAM_BesitzerID', 'PARAM_Type', 'PARAM_SpecialDouble');";
        cmd = cmd
                .replace("PARAM_Nummer", String.valueOf(konto.KontonummerGeben()))
                .replace("PARAM_Stand", String.valueOf(konto.KontostandGeben()))
                .replace("PARAM_BesitzerID", String.valueOf(konto.EigentümerGeben().NutzerIDGeben()))
                .replace("PARAM_Type", type)
                .replace("PARAM_SpecialDouble", String.valueOf(specialDouble));

        onUpdate(cmd);


        disconnect();
    }

    public boolean NummerSchonBelegt(int nummer){
        connect();
        String command = "SELECT Nummer FROM KontoDB;";
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
        return list.contains(nummer);*/
    }
    public Konto getKontoVonKontonummer(int nummer){
        return null;
    }


}
