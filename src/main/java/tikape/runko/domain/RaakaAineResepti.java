package tikape.runko.domain;


public class RaakaAineResepti {
    private int resptiId;
    private int raakaAineId;
    private String maara;
    private int jarjestys;
    
    public RaakaAineResepti(int resptiId, int raakaAineId, String maara, int jarjestys) {
        this.resptiId = resptiId;
        this.raakaAineId = raakaAineId;
        this.maara = maara;
        this.jarjestys = jarjestys;
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

    public int getJarjestys() {
        return jarjestys;
    }
}