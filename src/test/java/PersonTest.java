import com.theironyard.invitator.*;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by melmo on 12/22/16.
 */
public class PersonTest {
    Person person;

    @Test
    public void testSavePerson() throws SQLException {
        Integer userId = 1;
        String name = "Tuxedo Cat";
        String phone = "(702)555-5555";
        String email = "tuxedo@cat.com";
        String photoUrl = "www.tuxedo.com/cat.jpg";
        boolean invited = true;

        person = new Person(userId, name, phone, email, photoUrl, invited);

        person.savePerson();
    }
}
