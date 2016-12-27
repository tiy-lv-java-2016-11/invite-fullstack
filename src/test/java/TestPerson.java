import com.theironyard.invitator.*;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

/**
 * Created by melmo on 12/22/16.
 */
public class TestPerson {
    Person person;

    @Test
    public void testSavePerson() throws SQLException {
        String name = "Tuxedo Cat";
        String phone = "(702)555-5555";
        String email = "tuxedo@cat.com";
        String photoUrl = "www.tuxedo.com/cat.jpg";
        Boolean invited = true;

        person = new Person(name, phone, email, photoUrl, invited);
        person.savePerson();

        assertNotSame("Person_id was not generated properly", 0, person.getPersonId());
    }

    @Test
    public void testLoadPeople() throws SQLException {
        Integer eventId = 2;
        String name = "test_event";
        String date = "2012-12-12";
        String time = "13:00:00";
        String location = "test location";
        String description = "test description";
        Integer userId = 1;
        Event event = new Event(eventId, userId, name, date, time, location, description);

        Person.loadPeople(event);

        Person person1 = event.getInvitedList().get(2);
        Person person2 = event.getNotInvitedList().get(5);

        assertEquals("Person1 username is not correct", "Teddy Bear", person1.getName());
        assertEquals("Person2 username is not correct", "Tuxedo Cat", person2.getName());
    }
}
