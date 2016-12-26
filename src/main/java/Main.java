import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static final String INVITEE_INFO = "invitee";
    public static ArrayList<String> going = new ArrayList<>();
    public static ArrayList<String> notGoing = new ArrayList<>();
    public static void main(String[] args) {
        Spark.init();

//        Create a route for "/" that will display the invite page with the user counts
        Spark.get("/", ((request, response) -> {
            HashMap model = new HashMap();
            return new ModelAndView(model, "invite.html");
        }), new MustacheTemplateEngine());

//        Create a route for "/going" that will display the going template and pass all those going to the event.
        Spark.get("/going", ((request, response) -> {
            HashMap model = new HashMap();
            ArrayList<Invitee> going;
            return new ModelAndView(model, "going.html");

        }), new MustacheTemplateEngine());

//        Create a route for "/notgoing" that will display notgoing template and pass all those not going to the event.
        Spark.get("/notgoing", ((request, response) ->{
            HashMap model = new HashMap();
            ArrayList<Invitee> notgoing;
            return new ModelAndView(model, "notgoing.html");
        }), new MustacheTemplateEngine());

//        Create a POST route for "/mark-invitee"
        Spark.post("/mark-invitee", ((request, response) -> {
            Session session = request.session();
            String inviteeAns = session.attribute(INVITEE_INFO);
//          Take the information POST about the user and store it in a HashMap<String, Invitee> or ArrayList<Invitee>
            String markInvitee = request.queryParams("INVITEE_INFO");
            response.redirect("/");
            return "";

        }));
    }
}
