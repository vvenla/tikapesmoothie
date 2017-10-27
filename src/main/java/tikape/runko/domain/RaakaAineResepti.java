package tikape.runko.domain;


public class RaakaAineResepti {
    private int resptiId;
    private int raakaAineId;
    private String maara;
    
    public RaakaAineResepti(int resptiId, int raakaAineId, String maara) {
        this.resptiId = resptiId;
        this.raakaAineId = raakaAineId;
        this.maara = maara;
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
