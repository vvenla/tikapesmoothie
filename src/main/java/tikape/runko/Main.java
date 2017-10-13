package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:raakaAineet.db");
        database.init();

        RaakaAineDao raakaAineet = new RaakaAineDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/raakaAineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaAineet", raakaAineet.findAll());

            return new ModelAndView(map, "raakaAineet");
        }, new ThymeleafTemplateEngine());
        
        post("/raakaAinetet", (req, res) -> {
            String nimi = req.queryParams("nimi");
            raakaAineet.saveOrUpdate(new RaakaAine(nimi));
            res.redirect("/");
            return "";
        });

        get("/raakaAineet/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("raakaAine", raakaAineet.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "raakaAine");
        }, new ThymeleafTemplateEngine());
    }
}
