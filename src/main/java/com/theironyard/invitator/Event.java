package com.theironyard.invitator;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by melmo on 12/22/16.
 */
public class Event {
    private Integer eventId;
    private Integer userId;
    private String name;
    private String date;
    private String time;
    private String location;
    private String description;
    private Map<Integer, Person> invitedList = new HashMap<>();
    private Map<Integer, Person> notInvitedList = new HashMap<>();

    public Event(Integer eventId, Integer userId, String name, String date, String time, String location, String description) {
        this.eventId = eventId;
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public Event(String name, String date, String time, String location, String description, Integer userId){
        this.eventId = 0;
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public void saveEvent() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement insert = connection.prepareStatement(
                "INSERT INTO events (name, date, time, location, description, user_id) VALUES (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        insert.setString(1, this.name);
        insert.setString(2, this.date);
        insert.setString(3, this.time);
        insert.setString(4, this.location);
        insert.setString(5, this.description);
        insert.setInt(6, this.userId);
        insert.executeUpdate();
        ResultSet rs = insert.getGeneratedKeys();
        rs.next();
        this.eventId = rs.getInt("event_id");
    }

    public static void loadEvents(User user) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement load = connection.prepareStatement(
                "SELECT event_id, name, date, time, location, description FROM events WHERE user_id=?");
        load.setInt(1, user.getUserId());
        ResultSet rs = load.executeQuery();
        while (rs.next()){
            Integer eventId = rs.getInt("event_id");
            String name = rs.getString("name");
            String date = rs.getString("date");
            String time = rs.getString("time");
            String location = rs.getString("location");
            String description = rs.getString("description");
            Event event = new Event(eventId, user.getUserId(), name, date, time, location, description);
            Person.loadPeople(event);
            user.addEvent(event);
        }
    }

    public void addPerson(Person person){
        if (person.isInvited()){
            this.invitedList.put(person.getPersonId(), person);
        }
        else {
            this.notInvitedList.put(person.getPersonId(), person);
        }
    }

//-------------------------------------------------------------------------
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Integer, Person> getInvitedList() {
        return invitedList;
    }

    public void setInvitedList(Map<Integer, Person> invitedList) {
        this.invitedList = invitedList;
    }

    public Map<Integer, Person> getNotInvitedList() {
        return notInvitedList;
    }

    public void setNotInvitedList(Map<Integer, Person> notInvitedList) {
        this.notInvitedList = notInvitedList;
    }
}
