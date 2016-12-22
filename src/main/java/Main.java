import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melmo on 12/22/16.
 */
public class Main {
    static List<Person> invitedList = new ArrayList<>();
    static List<Person> notInvitedList = new ArrayList<>();

    public static void main(String[] args) {
        Spark.staticFileLocation("/static");
        Spark.init();

        Spark.get("/", ((request, response) -> {
            Map m = new HashMap();

            m.put("invitedNum", invitedList.size());
            m.put("notInvitedNum", notInvitedList.size());

            return new ModelAndView(m, "index.html");

        }), new MustacheTemplateEngine());

        Spark.post("/submit", ((request, response) -> {
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String email = request.queryParams("email");
            String photoUrl = request.queryParams("photo");
            Boolean invited = Boolean.valueOf(request.queryParams("invited"));

            Person person = new Person(name, phone, email, photoUrl);
            System.out.println(name+phone+email+invited);
            if (invited){
                invitedList.add(person);
            }
            else {
                notInvitedList.add(person);
            }

            response.redirect("/");
            return "";
        }));

        Spark.get("/invited", ((request, response) -> {
            Map m = new HashMap();

            m.put("invitedList", invitedList);

            return new ModelAndView(m, "invited.html");
        }), new MustacheTemplateEngine());

        Spark.get("/notinvited", ((request, response) -> {
            Map m = new HashMap();

            m.put("notInvitedList", notInvitedList);

            return new ModelAndView(m, "notinvited.html");
        }), new MustacheTemplateEngine());
    }
}
