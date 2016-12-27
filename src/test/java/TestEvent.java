import com.theironyard.invitator.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static java.util.Calendar.PM;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
/**
 * Created by melmo on 12/26/16.
 */
public class TestEvent {
    Event event;

    @Test
    public void testSaveEvent() throws SQLException {
        String name = "test save event";
        String date = "2012-12-12";
        String time = "01:00 PM";
        String location = "test save event location";
        String description = "test save event description";
        Integer userId = 1;

        event = new Event(name, date, time, location, description, userId);
        event.saveEvent();

        assertNotSame("Event_id was not generated properly", 0, event.getEventId());
    }

    @Test
    public void testLoadEvents() throws SQLException {
        User user = new User(1, "testuser", "testpass");
        Event.loadEvents(user);

        Event event = user.getEvent(2);

        assertEquals("Event name is not correct", "test_event", event.getName());
    }

    @Test
    public void testAddPerson() {
        Integer personId = 5;
        String name = "Tuxedo Cat";
        String phone = "(702)555-5555";
        String email = "tuxedo@cat.com";
        String photoUrl = "www.tuxedo.com/cat.jpg";
        Boolean invited = true;
        Person person = new Person(personId, name, phone, email, photoUrl, invited);

        String ename = "test save event";
        String date = "2012-12-12";
        String time = "01:00 PM";
        String location = "test save event location";
        String description = "test save event description";
        Integer userId = 1;
        event = new Event(ename, date, time, location, description, userId);

        event.addPerson(person);

        assertEquals(1, event.getInvitedList().size());
        assertEquals(0, event.getNotInvitedList().size());
    }
}
