package com.theironyard.invitator;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.*;

/**
 * Created by melmo on 12/22/16.
 */
public class Main {
    static String SESSION_USERID = "username";
//    static User user; // ??? Store user or get every time from db ???

    public static void main(String[] args) {
        Spark.staticFileLocation("/static");
        Spark.init();

        Spark.get("/", ((request, response) -> {
            Map m = new HashMap();
            Session session = request.session();
            Integer currentUser = session.attribute(SESSION_USERID);

            if (currentUser==null){
                return new ModelAndView(m, "login.html");
            }
            else {
                User user = User.loadUser(currentUser);
                m.put("user", user);
                m.put("events", user.getEvents().values());
                return new ModelAndView(m, "user_events.html");
            }
        }), new MustacheTemplateEngine());

        Spark.post("/login", ((request, response) -> {
            String username = request.queryParams("username");
            String password = request.queryParams("password");

            if (username.isEmpty() || password.isEmpty()){
                response.redirect("/");
                return "";
            }

            User user = User.loadUser(username);

            if (user==null){
                user = new User(username, password);
                user.saveUser();
            }
            else if (!password.equals(user.getPassword())){
                throw new Exception("Password is incorrect for user: " + username);
            }

            Session session = request.session();
            session.attribute(SESSION_USERID, user.getUserId());

            response.redirect("/");
            return "";
        }));

        Spark.post("/invite", ((request, response) -> {
            Session session = request.session();
            Integer currentUser = session.attribute(SESSION_USERID);
            User user = User.loadUser(currentUser);

            Integer eventId = Integer.valueOf(request.queryParams("eventId"));
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String email = request.queryParams("email");
            String photoUrl = request.queryParams("photoUrl");
            Boolean invited = Boolean.valueOf(request.queryParams("invited"));

            Person person = new Person(name, phone, email, photoUrl, invited);
            person.savePerson();

            Event event = user.getEvent(eventId);
            event.addPerson(person);

            response.redirect("/event?eventId="+eventId);
            return "";
        }));

        Spark.get("/create_event", ((request, response) -> {
            Map m = new HashMap();
            Session session = request.session();
            Integer currentUser = session.attribute(SESSION_USERID);
            User user = User.loadUser(currentUser);
            m.put("user", user);
            return new ModelAndView(m, "create_event.html");
        }), new MustacheTemplateEngine());

        Spark.post("/create_event", ((request, response) -> {
            Map m = new HashMap();
            Session session = request.session();
            Integer currentUser = session.attribute(SESSION_USERID);
            User user = User.loadUser(currentUser);

            String name = request.queryParams("name");
            String date = request.queryParams("date");
            String time = request.queryParams("time");
            String location = request.queryParams("location");
            String description = request.queryParams("description");

            Event event = new Event(name, date, time, location, description, currentUser);
            event.saveEvent();

            response.redirect("/event?eventId="+event.getEventId());
            return "";
        }));

        Spark.get("/event", ((request, response) -> {
            Map m = new HashMap();
            Session session = request.session();
            Integer currentUser = session.attribute(SESSION_USERID);
            User user = User.loadUser(currentUser);

            Integer event_id = Integer.valueOf(request.queryParams("eventId"));
            Event event = user.getEvent(event_id);
            System.out.println(event.getInvitedList().size());

            m.put("user", user);
            m.put("event", event);
            m.put("invitedNum", event.getInvitedList().size());
            m.put("notInvitedNum", event.getNotInvitedList().size());

            return new ModelAndView(m, "event.html");
        }), new MustacheTemplateEngine());
    }
}
