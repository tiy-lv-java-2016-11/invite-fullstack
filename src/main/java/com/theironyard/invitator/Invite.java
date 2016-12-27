package com.theironyard.invitator;

/**
 * Created by melmo on 12/26/16.
 */
public class Invite {
    private Integer invite_id;
    private Integer event_id;
    private Integer person_id;
    private Boolean invited;



// DEFAULTS --------------------------------------------------
    public Invite(Integer invite_id, Integer event_id, Integer person_id, Boolean invited) {
        this.invite_id = invite_id;
        this.event_id = event_id;
        this.person_id = person_id;
        this.invited = invited;
    }

    public Integer getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(Integer invite_id) {
        this.invite_id = invite_id;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Boolean getInvited() {
        return invited;
    }

    public void setInvited(Boolean invited) {
        this.invited = invited;
    }
}
