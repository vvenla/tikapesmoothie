package tikape.runko.domain;


public class RaakaAineResepti {
    private int id;
    private int resptiId;
    private int raakaAineId;
    private String maara;

    public RaakaAineResepti(int id, int resptiId, int raakaAineId, String maara) {
        this.id = id;
        this.resptiId = resptiId;
        this.raakaAineId = raakaAineId;
        this.maara = maara;
    }

    public int getId() {
        return id;
    }

    public String getMaara() {
        return maara;
    }

    public int getResptiId() {
        return resptiId;
    }

    public int getRaakaAineId() {
        return raakaAineId;
    }
}
