package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.KategoriaDao;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.database.RaakaAineReseptiDao;
import tikape.runko.database.ReseptiDao;
import tikape.runko.domain.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/raakaAineet.db");
        database.init();

        RaakaAineDao raakaAineet = new RaakaAineDao(database);
        RaakaAineReseptiDao raakaAineReseptit = new RaakaAineReseptiDao(database);
        ReseptiDao reseptit = new ReseptiDao(database);
        KategoriaDao kategoriat = new KategoriaDao(database);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("reseptit", reseptit.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/raaka-aineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaAineet", raakaAineet.findAll());
            map.put("kategoriat", kategoriat.findAll());

            return new ModelAndView(map, "raaka-aineet");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/raaka-aineet", (req, res) -> {
            RaakaAine raakaAine = new RaakaAine(null, req.queryParams("raakaAine"));
            if (raakaAine.getNimi().isEmpty()) {
                res.redirect("/raaka-aineet");
                return "";
            }
            raakaAineet.saveOrUpdate(raakaAine);
            res.redirect("/raaka-aineet");
            return raakaAine;
        });
        
        Spark.get("/raaka-aine/poista/:id", (req, res) -> {
           int raId = Integer.parseInt(req.params("id"));
           raakaAineet.delete(raId);
           
           res.redirect("/raaka-aineet");
           return null;
        });
        
        Spark.get("/smoothiet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("reseptit", reseptit.findAll());
            map.put("raakaAineet", raakaAineet.findAll());
            
            return new ModelAndView(map, "smoothiet");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/smoothiet", (req, res) -> {
            Resepti resepti = new Resepti(null, req.queryParams("smoothie"));
            if (resepti.getNimi().isEmpty()) {
                res.redirect("/smoothiet");
                return "";
            }
//            System.out.println("Vastaanotettiin resepti: " + resepti.getNimi());
            reseptit.saveOrUpdate(resepti);
            res.redirect("/smoothiet");
            return resepti;
        });
        
        Spark.get("/smoothiet/:id", (req, res) -> {
            int sId = Integer.parseInt(req.params("id"));
            HashMap map = new HashMap<>();
            Resepti resepti = reseptit.findOne(sId);
            map.put("resepti", resepti);
            map.put("maarat", raakaAineReseptit.ReseptiId(resepti.getId()));
            map.put("raakaAineet", raakaAineet.reseptiId(resepti.getId()));
            
            return new ModelAndView(map, "smoothie");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/smoothiet/poista/:id", (req, res) -> {
           int smoothieId = Integer.parseInt(req.params("id"));
           reseptit.delete(smoothieId);
           
           res.redirect("/smoothiet");
           return null;
        });
        
        Spark.get("/kategoriat", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kategoriat", kategoriat.findAll());

            return new ModelAndView(map, "kategoriat");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/kategoriat", (req, res) -> {
            Kategoria kategoria = new Kategoria(req.queryParams("kategoria"));
            if (kategoria.getNimi().isEmpty()) {
                res.redirect("/raaka-aineet");
                return "";
            }
            kategoriat.saveOrUpdate(kategoria);
            res.redirect("/kategoriat");
            return kategoria;
        });
        
        Spark.get("/kategoria/poista/:id", (req, res) -> {
           int kategoriaId = Integer.parseInt(req.params("id"));
           kategoriat.delete(kategoriaId);
           
           res.redirect("/kategoriat");
           return null;
        });
        
        Spark.post("/raakaAineResepti", (req, res) -> {
            int smoothieId = Integer.parseInt(req.queryParams("smoothieId"));
            int raakaAineId = Integer.parseInt(req.queryParams("raakaAineId"));
            int jarjestys;
            try {
            jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
            } catch (NumberFormatException e) {
                jarjestys = 1;
            }
            RaakaAineResepti raakaAineResepti = 
                new RaakaAineResepti(smoothieId, raakaAineId, req.queryParams("maara"), jarjestys);
            if (raakaAineResepti.getMaara().isEmpty()) {
                res.redirect("/smoothiet");
                return "";
            }
            raakaAineReseptit.saveOrUpdate(raakaAineResepti);
            res.redirect("/smoothiet");
            return raakaAineResepti;
        });
//
//        get("/raakaAineet/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("raakaAine", raakaAineet.findOne(Integer.parseInt(req.params("id"))));
//
//            return new ModelAndView(map, "raakaAine");
//        }, new ThymeleafTemplateEngine());
//         get("/resepti", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("resepti", );
//
//            return new ModelAndView(map, "raakaAineet");
//        }, new ThymeleafTemplateEngine());
//        
    }
}
