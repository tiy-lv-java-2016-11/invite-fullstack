package com.theironyard.invitator;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by melmo on 12/22/16.
 */
public class Person {
    private Integer personId;
    private String name;
    private String phone;
    private String email;
    private String photoUrl;
    private Boolean invited;

    public Person(Integer personId, String name, String phone, String email, String photoUrl, Boolean invited) {
        this.personId = personId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
        this.invited = invited;
    }

    public Person(String name, String phone, String email, String photoUrl, Boolean invited) {
        this.personId = 0;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
        this.invited = invited;
    }

    /* *
     * Saves a single 'Person' object to 'people' table in DB 'invitation'
     * */
    public void savePerson() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement insert = connection.prepareStatement(
                "INSERT INTO people (name, phone, email, photo_url, invited) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        insert.setString(1, this.name);
        insert.setString(2, this.phone);
        insert.setString(3, this.email);
        insert.setString(4, this.photoUrl);
        insert.setBoolean(5, this.invited);
        insert.executeUpdate();
        ResultSet rs = insert.getGeneratedKeys();
        rs.next();
        this.personId = rs.getInt("person_id");
    }

    /* *
     * Saves a list of 'Person' objects to 'people' table in DB 'invitation'
     * @param people ArrayList of 'Person' objects
     * */
    public static void savePeople(ArrayList<Person> people) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        for (Person p : people){
            PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO people (name, phone, email, photo_url) VALUES (?, ?, ?, ?, ?, ?)");
            insert.setString(2, p.name);
            insert.setString(3, p.phone);
            insert.setString(4, p.email);
            insert.setString(5, p.photoUrl);
            insert.executeUpdate();
        }
    }

    public static void loadPeople(Event event) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement load = connection.prepareStatement(
                "SELECT p.person_id, p.name, p.phone, p.email, p.photo_url, i.invited\n" +
                        "FROM people p JOIN invited i ON p.person_id = i.person_id\n" +
                        "WHERE i.event_id=?");
        load.setInt(1, event.getEventId());
        ResultSet rs = load.executeQuery();
        while (rs.next()){
            Integer personId = rs.getInt("person_id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String photoUrl = rs.getString("photo_url");
            Boolean invited = rs.getBoolean("invited");
            Person person = new Person(personId, name, phone, email, photoUrl, invited);
            event.addPerson(person);
        }
    }


//-----------------------------------------------------------------------------
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean isInvited() {
        return invited;
    }

    public void setInvited(Boolean invited) {
        this.invited = invited;
    }
}
