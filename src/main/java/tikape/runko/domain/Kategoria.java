package tikape.runko.domain;


public class Kategoria {
    int id;
    String nimi;

    public Kategoria(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public Kategoria(String nimi) {
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
