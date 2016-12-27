/**
 * Created by sparatan117 on 12/27/16.
 */
public class Invitee {
    private String name;
    private String phoneNumber;
    private String email;
    private String picture;

    public Invitee(String name, String phoneNumber, String email, String picture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
