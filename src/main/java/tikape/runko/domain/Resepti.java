package tikape.runko.domain;


public class Resepti {
    int id;
    String nimi;

    public Resepti(int id, String nimi) {
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
