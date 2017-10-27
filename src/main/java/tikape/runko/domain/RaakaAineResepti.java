package tikape.runko.domain;

public class RaakaAineResepti {
    private Integer id;
    private Integer reseptiId;
    private Integer raakaAineId;
    private String maara;

    public RaakaAineResepti(Integer id, Integer reseptiId, Integer raakaAineId, String maara) {
        this.id = id;
        this.reseptiId = reseptiId;
        this.raakaAineId = raakaAineId;
        this.maara = maara;
    }

    public Integer getId() {
        return id;
    }

    public String getMaara() {
        return maara;
    }

    public Integer getResptiId() {
        return reseptiId;
    }

    public Integer getRaakaAineId() {
        return raakaAineId;
    }
}
