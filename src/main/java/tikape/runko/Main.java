package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.KategoriaDao;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.database.ReseptiDao;
import tikape.runko.domain.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/raakaAineet.db");
        database.init();

        RaakaAineDao raakaAineet = new RaakaAineDao(database);
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
        
        Spark.get("/smoothiet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("reseptit", reseptit.findAll());
            
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
            Integer id = new Integer(req.params("id"));
            Resepti resepti = reseptit.findOne(id);
            HashMap map = new HashMap<>();
            map.put("resepti", resepti);
            return new ModelAndView(map, "resepti");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/kategoriat", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kategoriat", kategoriat.findAll());
            return new ModelAndView(map, "kategoriat");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/kategoriat", (req, res) -> {
            Kategoria kategoria = new Kategoria(null, req.queryParams("kategoria"));
            System.out.println(kategoria.getNimi());
            if (kategoria.getNimi().isEmpty()) {
                res.redirect("/kategoriat");
                return "";
            }
            kategoriat.saveOrUpdate(kategoria);
            res.redirect("/kategoriat");
            return "";
        });
        
        Spark.post("/kategoriat/poista/:id", (req, res) -> {
            Integer id = new Integer(req.params(":id"));
            System.out.println(id);
            kategoriat.delete(id);
            res.redirect("/kategoriat");
            return "";
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
