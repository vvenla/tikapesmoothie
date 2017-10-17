package tikape.runko.domain;


public class Resepti {
    Integer id;
    String nimi;

    public Resepti(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return nimi;
    }
    
    
}
