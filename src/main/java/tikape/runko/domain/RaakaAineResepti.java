package tikape.runko.domain;


public class RaakaAineResepti {
    private Integer id;
    private Integer resptiId;
    private Integer raakaAineId;
    private String maara;

    public RaakaAineResepti(Integer id, Integer resptiId, Integer raakaAineId, String maara) {
        this.id = id;
        this.resptiId = resptiId;
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
        return resptiId;
    }

    public Integer getRaakaAineId() {
        return raakaAineId;
    }
}
