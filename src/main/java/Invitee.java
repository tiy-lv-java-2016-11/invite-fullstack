//Create a Invitee class that stores all the info for the random user when submitted

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Invitee {
    private  String invitee;
    private int phone;
    private String email;




    public Invitee(String invitee, int phone, String email ) {
        this.invitee = invitee;
        this.phone = phone;
        this.email = email;

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

}

