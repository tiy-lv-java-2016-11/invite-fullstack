package com.theironyard.invitator;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by melmo on 12/22/16.
 */
public class User {
    private Integer userId;
    private String username;
    private String password;
    private Map<Integer, Event> events = new HashMap<>();

    public User(String username, String password) {
        this.userId = 0;
        this.username = username;
        this.password = password;
    }

    public void addEvent(Event event){
        events.put(event.getEventId(), event);
    }

    public Event getEvent(Integer id){
        return events.get(id);
    }

    /* *
     * Saves a single 'User' object to 'user' table in DB 'invitation'
     * */
    public void saveUser() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement insert = connection.prepareStatement(
                "INSERT INTO users (username, password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
        insert.setString(1,this.username);
        insert.setString(2,this.password);
        insert.executeUpdate();

        ResultSet rs = insert.getGeneratedKeys();
        rs.next();
        this.userId = rs.getInt("id");
    }

    /* *
     * Loads single 'user' from db with 'username'
     * */
    public static User loadUser(String username) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement load = connection.prepareStatement("SELECT user_id, password FROM users WHERE username=?");
        load.setString(1, username);
        ResultSet rs = load.executeQuery();
        rs.next();
        try {
            Integer userId = rs.getInt("user_id");
            String password = rs.getString("password");
            User user = new User(userId, username, password);
            Event.loadEvents(user);
            return user;
        }
        catch (SQLException e){
            return null;
        }
    }

    /* *
     * Loads single 'user' from db with 'userId'
     * */
    public static User loadUser(Integer userId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement loadUser = connection.prepareStatement("SELECT username, password FROM users WHERE user_id=?");
        loadUser.setInt(1, userId);
        ResultSet rs = loadUser.executeQuery();
        rs.next();
        try {
            String username = rs.getString("username");
            String password = rs.getString("password");
            User user = new User(userId, username, password);
            Event.loadEvents(user);
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

// DEFAULTS ----------------------------------
    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public void setEvents(Map<Integer, Event> events) {
        this.events = events;
    }
}
