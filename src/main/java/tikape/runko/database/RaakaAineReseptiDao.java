package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Resepti;
import tikape.runko.domain.RaakaAineResepti;


public class RaakaAineReseptiDao implements Dao<RaakaAineResepti, Integer>{
    private Database database;

    public RaakaAineReseptiDao(Database database) {
        this.database = database;
    }
    
    public List<RaakaAineResepti> ReseptiId(Integer key) throws SQLException {
        List<RaakaAineResepti> raakaAineReseptit = new ArrayList();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
            "SELECT * FROM RaakaAineResepti"
            + " WHERE reseptiId=?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            raakaAineReseptit.add(new RaakaAineResepti(
                rs.getInt("id"), rs.getInt("reseptiId"), rs.getInt("raakaAineId"), rs.getString("maara")));
        }
        return raakaAineReseptit;
    }

    @Override
    public RaakaAineResepti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = 
                connection.prepareStatement("SELECT * FROM RaakaAineResepti WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }

        Integer id = rs.getInt("id");
        Integer reseptiId = rs.getInt("reseptiId");
        Integer raakaAineId = rs.getInt("raakaAineId");
        String maara = rs.getString("maara");

        RaakaAineResepti r = new RaakaAineResepti(id, reseptiId, raakaAineId, maara);

        rs.close();
        stmt.close();
        connection.close();

        return r;
    }

    @Override
    public List<RaakaAineResepti> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM RaakaAineResepti");

        ResultSet rs = stmt.executeQuery();
        List<RaakaAineResepti> raakaAineReseptit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            Integer reseptiId = rs.getInt("reseptiId");
            Integer raakaAineId = rs.getInt("raakaAineId");
            String maara = rs.getString("maara");

            raakaAineReseptit.add(new RaakaAineResepti(id, reseptiId, raakaAineId, maara));
        }

        rs.close();
        stmt.close();
        connection.close();

        return raakaAineReseptit;
    }
    
    @Override
    public RaakaAineResepti saveOrUpdate(RaakaAineResepti object) throws SQLException {
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
        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM RaakaAineResepti WHERE id = ?");
        stmt.setInt(1, key);
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    private RaakaAineResepti save(RaakaAineResepti raakaAineResepti) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO RaakaAineResepti"
                + " (reseptiId, raakaAineId, maara) VALUES (?, ?, ?)");
        stmt.setInt(1, raakaAineResepti.getResptiId());
        stmt.setInt(2, raakaAineResepti.getRaakaAineId());
        stmt.setString(3, raakaAineResepti.getMaara());
        

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT MAX(id) AS idd FROM RaakaAineResepti"
                + " WHERE reseptiId=?"
                + " AND raakaAineId=?");
        stmt.setInt(1, raakaAineResepti.getResptiId());
        stmt.setInt(2, raakaAineResepti.getRaakaAineId());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        RaakaAineResepti r = new RaakaAineResepti(rs.getInt("idd"), raakaAineResepti.getResptiId(), raakaAineResepti.getRaakaAineId(), raakaAineResepti.getMaara());

        stmt.close();
        rs.close();

        conn.close();

        return r;
    }

    private RaakaAineResepti update(RaakaAineResepti raakaAineResepti) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE RaakaAineResepti SET"
                + " reseptiId=?, raakaAineId=?, maara=?"
                + " WHERE id = ?");
        stmt.setInt(1, raakaAineResepti.getResptiId());
        stmt.setInt(2, raakaAineResepti.getRaakaAineId());
        stmt.setString(3, raakaAineResepti.getMaara());;
        stmt.setInt(4, raakaAineResepti.getId());

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return raakaAineResepti;
    }
}
