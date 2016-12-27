import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static final String INVITEE_INFO = new Invitee();
    public static ArrayList<String> going = new ArrayList<>();
    public static ArrayList<String> notGoing = new ArrayList<>();
    public static void main(String[] args) {
        Spark.staticFileLocation("/public");
        Spark.init();

//        Create a route for "/" that will display the invite page with the user counts
        Spark.get("/", ((request, response) -> {
            HashMap model = new HashMap();
            return new ModelAndView(model, "invite.html");
        }), new MustacheTemplateEngine());

//        Create a route for "/going" that will display the going template and pass all those going to the event.
        Spark.get("/going", ((request, response) -> {
            HashMap model = new HashMap();
            going.add(INVITEE_INFO);
            return new ModelAndView(model, "going.html");

        }), new MustacheTemplateEngine());

//        Create a route for "/notgoing" that will display notgoing template and pass all those not going to the event.
        Spark.get("/notgoing", ((request, response) ->{
            HashMap model = new HashMap();
            notGoing.add(INVITEE_INFO);
            return new ModelAndView(model, "notgoing.html");
        }), new MustacheTemplateEngine());

//        Create a POST route for "/mark-invitee"
        Spark.post("/mark-invitee", ((request, response) -> {
            String yes = request.queryParams("going");
            String no = request.queryParams("notgoing");
            going.add(yes);
            notGoing.add(no);
            response.redirect("/");
            return "";

        }));
    }
}
