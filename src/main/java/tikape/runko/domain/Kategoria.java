package tikape.runko.domain;


public class Kategoria {
    Integer id;
    String nimi;

    public Kategoria(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public Kategoria(String nimi) {
        this.nimi = nimi;
    }

    public Integer getId() {
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
