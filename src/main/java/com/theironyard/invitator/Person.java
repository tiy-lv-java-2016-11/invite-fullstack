package com.theironyard.invitator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by melmo on 12/22/16.
 */
public class Person {
    private Integer personId;
    private Integer userId;
    private String name;
    private String phone;
    private String email;
    private String photoUrl;
    private boolean invited;

    public Person(){}

    public Person(Integer userId, String name, String phone, String email, String photoUrl, boolean invited) {
        this.personId = 0;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
        this.invited = invited;
    }

    public Person(Integer personId, Integer userId, String name, String phone, String email, String photoUrl, boolean invited) {
        this.personId = personId;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
        this.invited = invited;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isInvited() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
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

    /* *
     * Saves a single 'Person' object to DB 'invitation'
     * */
    public void savePerson() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        PreparedStatement insert = connection.prepareStatement(
                "INSERT INTO people (user_id, name, phone, email, photo_url, invited) VALUES (?, ?, ?, ?, ?, ?)");
        insert.setInt(1, this.userId);
        insert.setString(2, this.name);
        insert.setString(3, this.phone);
        insert.setString(4, this.email);
        insert.setString(5, this.photoUrl);
        insert.setBoolean(6, this.invited);
        insert.executeUpdate();
    }

    /* *
     * Saves a list of 'Person' objects to DB 'invitation'
     * @param people ArrayList of 'Person' objects
     * */
    public static void savePeople(ArrayList<Person> people) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql:invitation");
        for (Person p : people){
            PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO people (user_id, name, phone, email, photo_url, invited) VALUES (?, ?, ?, ?, ?, ?)");
            insert.setInt(1, p.userId);
            insert.setString(2, p.name);
            insert.setString(3, p.phone);
            insert.setString(4, p.email);
            insert.setString(5, p.photoUrl);
            insert.setBoolean(6, p.invited);
            insert.executeUpdate();
        }
    }
}
