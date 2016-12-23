package com.theironyard.invitator;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by melmo on 12/22/16.
 */
public class Event {
    private Integer eventId;
    private Integer userId;
    private String name;
    private Date date;
    private Time time;
    private String location;
    private String description;
    private List<Person> invited = new ArrayList<>();
    private List<Person> notInvited = new ArrayList<>();

    public Event(Integer eventId, Integer userId, String name, Date date, String location, String description) {
        this.eventId = eventId;
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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

    public List<Person> getInvited() {
        return invited;
    }

    public void setInvited(List<Person> invited) {
        this.invited = invited;
    }

    public List<Person> getNotInvited() {
        return notInvited;
    }

    public void setNotInvited(List<Person> notInvited) {
        this.notInvited = notInvited;
    }
}
