package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.*;


public class KategoriaDao implements Dao<Kategoria, Integer>{
    private Database database;

    public KategoriaDao(Database database) {
        this.database = database;
    }

    @Override
    public Kategoria findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = 
                connection.prepareStatement("SELECT * FROM Kategoria WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Kategoria r = new Kategoria(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return r;
    }

    @Override
    public List<Kategoria> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kategoria");

        ResultSet rs = stmt.executeQuery();
        List<Kategoria> kategoriat = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            kategoriat.add(new Kategoria(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return kategoriat;
    }
    
    @Override
    public Kategoria saveOrUpdate(Kategoria object) throws SQLException {
//        if (object.id == null) {
            return save(object);
//        } else {
//            return update(object);
//        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Kategoria WHERE id = ?");
        stmt.setInt(1, key);
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    private Kategoria save(Kategoria kategoria) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Kategoria"
                + " (nimi) VALUES (?)");
        stmt.setString(1, kategoria.getNimi());
        

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT MAX(id) AS idd FROM Kategoria"
                + " WHERE nimi = ?");
        stmt.setString(1, kategoria.getNimi());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        Kategoria r = new Kategoria(rs.getInt("idd"), kategoria.getNimi());

        stmt.close();
        rs.close();

        conn.close();

        return r;
    }

    private Kategoria update(Kategoria kategoria) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Kategoria SET"
                + " nimi = ? WHERE id = ?");
        stmt.setString(1, kategoria.getNimi());
        stmt.setInt(2, kategoria.getId());

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return kategoria;
    }
}
