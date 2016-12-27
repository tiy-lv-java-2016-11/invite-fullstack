import java.util.ArrayList;
import java.util.HashMap;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Created by sparatan117 on 12/27/16.
 */
public class Main {
    static ArrayList<Invitee> invited = new ArrayList<>();
    static ArrayList<Invitee> notInvited = new ArrayList<>();
    public static void main(String[] args) {
        Spark.staticFileLocation("/public");
        Spark.init();

        Spark.get("/", ((request, response) -> {
         HashMap a = new HashMap();

         a.put("invited", invited.size());
         a.put("notInvited", notInvited.size());
         return new ModelAndView(a, "index.html");
        }), new MustacheTemplateEngine());

        Spark.post("/index", ((request, response) -> {
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String email = request.queryParams("email");
            String picture = request.queryParams("picture");
            boolean isInvited = Boolean.valueOf(request.queryParams("invited"));

            Invitee invitee = new Invitee(name, phone, email, picture);
            if(isInvited){
                invited.add(invitee);
            }
            else {
                notInvited.add(invitee);
            }
            response.redirect("/");
            return "";
        }));

        Spark.get("/going", ((request, response) -> {
            HashMap a = new HashMap();

            a.put("invited", invited);

            return new ModelAndView(a, "going.html");
        }), new MustacheTemplateEngine());

        Spark.get("/notgoing", ((request, response) -> {
            HashMap a = new HashMap();

            a.put("notInvited", notInvited);

            return new ModelAndView(a, "notgoing.html");
        }), new MustacheTemplateEngine());

    }
}
