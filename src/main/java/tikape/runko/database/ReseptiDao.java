package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.*;


public class ReseptiDao implements Dao<Resepti, Integer>{
    private Database database;

    public ReseptiDao(Database database) {
        this.database = database;
    }

    @Override
    public Resepti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = 
                connection.prepareStatement("SELECT * FROM Resepti WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Resepti r = new Resepti(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return r;
    }

    @Override
    public List<Resepti> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Resepti");

        ResultSet rs = stmt.executeQuery();
        List<Resepti> reseptit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            reseptit.add(new Resepti(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return reseptit;
    }
    
    @Override
    public Resepti saveOrUpdate(Resepti object) throws SQLException {
//        if (object.id == null) {
            return save(object);
//        } else {
//            return update(object);
//        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Resepti WHERE id = ?");
        stmt.setInt(1, key);
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    private Resepti save(Resepti resepti) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Resepti"
                + " (nimi) VALUES (?)");
        stmt.setString(1, resepti.getNimi());
        

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT MAX(id) AS idd FROM Resepti"
                + " WHERE nimi = ?");
        stmt.setString(1, resepti.getNimi());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        Resepti r = new Resepti(rs.getInt("idd"), resepti.getNimi());

        stmt.close();
        rs.close();

        conn.close();

        return r;
    }

    private Resepti update(Resepti resepti) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Resepti SET"
                + " nimi = ? WHERE id = ?");
        stmt.setString(1, resepti.getNimi());
        stmt.setInt(2, resepti.getId());

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return resepti;
    }
}
