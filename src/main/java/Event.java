import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by melmo on 12/22/16.
 */
public class Event {
    private String name;
    private Date date;
    private List<Person> invited = new ArrayList<>();
    private List<Person> notInvited = new ArrayList<>();

    public Event(String name, Date date) {
        this.name = name;
        this.date = date;
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
