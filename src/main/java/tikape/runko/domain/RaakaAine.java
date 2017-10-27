package tikape.runko.domain;

public class RaakaAine {

    private Integer id;
    private String nimi;
    private int kategoriaId;

    public RaakaAine(Integer id, String nimi, int kategoriaId) {
        this.id = id;
        this.nimi = nimi;
        this.kategoriaId = kategoriaId;
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

    public void setKategoriaId(int kategoriaId) {
        this.kategoriaId = kategoriaId;
    }

    public int getKategoriaId() {
        return kategoriaId;
    }
    
    @Override
    public String toString() {
        return nimi;
    }
    
    

}
