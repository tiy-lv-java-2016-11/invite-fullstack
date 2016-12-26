//Create a Invitee class that stores all the info for the random user when submitted

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Invitee {
    private  String invitee;
    private int phone;
    private String email;
    private String notGoing;
    private String going;
    private String inviteeAnswer;


    public Invitee(String invitee, int phone, String email, String inviteeAnswer) {
        this.invitee = invitee;
        this.phone = phone;
        this.email = email;
        this.inviteeAnswer = inviteeAnswer;
    }



    public String getInvitee() {
        return invitee;
    }

    public void setInvitee(String invitee) {
        this.invitee = invitee;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getNotGoing() {
        return notGoing;
    }

    public void setNotGoing(String notGoing) {
        this.notGoing = notGoing;
    }

    public String getGoing() {
        return going;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public String getInviteeAnswer() {
        return inviteeAnswer;
    }

    public void setInviteeAnswer(String inviteeAnswer) {
        this.inviteeAnswer = inviteeAnswer;
    }
}

