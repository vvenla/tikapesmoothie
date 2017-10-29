package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE TABLE IF NOT EXISTS Kategoria"
                + " (id integer PRIMARY KEY,"
                + " nimi varchar(40));");
        lista.add("CREATE TABLE IF NOT EXISTS RaakaAine"
                + " (id integer PRIMARY KEY,"
                + " kategoriaId integer,"
                + " nimi varchar(40),"
                + " FOREIGN KEY (kategoriaId) REFERENCES Kategoria(id));");
        lista.add("CREATE TABLE IF NOT EXISTS Resepti"
                + " (id integer PRIMARY KEY,"
                + " nimi varchar(40));");
        lista.add("CREATE TABLE IF NOT EXISTS RaakaAineResepti"
                + " (id integer PRIMARY KEY,"
                + " maara integer,"
                + " reseptiId integer,"
                + " raakaAineId integer,"
                + " FOREIGN KEY (reseptiId) REFERENCES Resepti(id),"
                + " FOREIGN KEY (raakaAineId) REFERENCES RaakaAine(id))");
        //lista.add("INSERT INTO Resepti (nimi) VALUES ('Testiresepti');");

        return lista;
    }
}
