package tikape.runko.domain;

public class RaakaAine {

    private Integer id;
    private String nimi;
    private  Kategoria kategoria;

    public RaakaAine(Integer id, String nimi, Kategoria kategoria) {
        this.id = id;
        this.nimi = nimi;
        this.kategoria = kategoria;
    }
    
    public RaakaAine(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public RaakaAine(String nimi) {
        this.nimi = nimi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public String toString() {
        return nimi;
    }
    
    

}
