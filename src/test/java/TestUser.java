import com.theironyard.invitator.*;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

/**
 * Created by melmo on 12/24/16.
 */
public class TestUser {
    User user;

    @Test
    public void testSaveUser() throws SQLException {
        String username = "something";
        String password = "sneaky";
        user = new User(username, password);

        user.saveUser();

        assertNotSame("User_id was not generated properly", 0, user.getUserId());
    }

    @Test
    public void testLoadUserName() throws SQLException {
        String username = "testuser";
        user = User.loadUser(username);

        assertEquals("user_id is incorrect", Integer.valueOf(1), user.getUserId());
        assertEquals("password is incorrect", "testpass", user.getPassword());

        assertEquals("", 2, user.getEvents().size());
    }

    @Test
    public void testLoadUserID() throws SQLException {
        Integer userId = 1;
        user = User.loadUser(userId);

        assertEquals("username is incorrect", "testuser", user.getUsername());
        assertEquals("password is incorrect", "testpass", user.getPassword());
    }
}
