package com.theironyard.invitator;

import spark.ModelAndView;
import spark.Session;
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
    static String SESSION_USERNAME = "username";
    static List<Person> invitedList = new ArrayList<>();
    static List<Person> notInvitedList = new ArrayList<>();
    static Map<String, User> users = new HashMap();

    public static void main(String[] args) {
        Spark.staticFileLocation("/static");
        Spark.init();

        // Receives info from LOGIN form
        Spark.post("/login", ((request, response) -> {
            String username = request.queryParams("username");
            String password = request.queryParams("password");

            if (username.isEmpty() || password.isEmpty()){
                response.redirect("/");
                return "";
            }

            User user = new User(username, password);
            users.put(username, user);

            Session session = request.session();
            session.attribute(SESSION_USERNAME, username);

            response.redirect("/");
            return "";
        }));

    //
        Spark.get("/", ((request, response) -> {
            Map m = new HashMap();
            Session session = request.session();
            String currentUser = session.attribute(SESSION_USERNAME);
            User user = users.get(currentUser);

            if (user==null){
                return new ModelAndView(m, "login.html");
            }
            else {
                m.put("currentUser", user);
                m.put("invitedNum", invitedList.size());
                m.put("notInvitedNum", notInvitedList.size());

                return new ModelAndView(m, "index.html");
            }
        }), new MustacheTemplateEngine());

        Spark.post("/submit", ((request, response) -> {
            Session session = request.session();
            String currentUser = session.attribute(SESSION_USERNAME);
            User user = users.get(currentUser);

            Integer userId = user.getUserId();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String email = request.queryParams("email");
            String photoUrl = request.queryParams("photo");
            boolean invited = Boolean.valueOf(request.queryParams("invited"));

            Person person = new Person(userId, name, phone, email, photoUrl, invited);
            person.savePerson();

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
