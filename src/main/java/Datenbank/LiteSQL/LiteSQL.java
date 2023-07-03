package Datenbank.LiteSQL;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public abstract class LiteSQL {

    private Connection connection;
    private Statement stmt;

    private final String filename = "datenbank.db";
    private String table_name;

    protected LiteSQL(String table) {
        this.table_name = table;
    }

    protected void connect(){
        connection = null;

        try {
            File file = new File(filename);
            if (!file.exists()){
                file.createNewFile();
            }

            String url = "jdbc:sqlite:" + file.getPath();
            connection = DriverManager.getConnection(url);

            //System.out.println("Verbindung zur Datenbank hergestellt.");


            stmt = connection.createStatement();
            stmt.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
    protected void disconnect(){
        try {
            if (connection != null){
                connection.close();
                //System.out.println("Verbindung zur Datenbank getrennt.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void onUpdate(String cmd){
        String sql = cmd.replace("TABLE", table_name);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected ResultSet onQuery(String cmd){
        String sql = cmd.replace("TABLE", table_name);
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
