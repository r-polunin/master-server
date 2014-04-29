import database.DBServiceImpl;
import database.UserDataSet;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by Ruslan on 27.04.2014.
 */
public class DBServiceTest {

    DBServiceImpl dbService;

    @BeforeClass
    public void setUp() {
        MessageSystem ms = mock(MessageSystemImpl.class);
        dbService = new DBServiceImpl(ms);
    }

    @Test
    public void testAddGetUDS() {
        String login = "testuser";
        String password = "password";
        try {
            dbService.addUDS(login, password);
        } catch (Exception e) {
            UserDataSet testUser = dbService.getUDS(login, password);
            dbService.deleteUser(testUser.getModel());
            dbService.addUDS(login, password);
        }
        UserDataSet testUser = dbService.getUDS(login, password);
        Assert.assertEquals(testUser.getNick(), login);
        dbService.deleteUser(testUser.getModel());
    }
    @Test void testUpdateUsers() {
        List<UserDataSet> users = new ArrayList<UserDataSet>();

        String loginOne = "testuserone";
        String passwordOne = "passwordone";

        String loginTwo = "testusertwo";
        String passwordTwo = "passwordtwo";

        dbService.addUDS(loginOne, passwordOne);
        dbService.addUDS(loginTwo, passwordTwo);

        UserDataSet userOne = dbService.getUDS(loginOne, passwordOne);
        UserDataSet userTwo = dbService.getUDS(loginTwo, passwordTwo);

        int diffOne = 15;
        int diffTwo = 12;

        int userOneRating = diffOne - diffTwo;
        int userTwoRating = diffTwo - diffOne;

        int winQuantity = 1;
        int loseQuantity = 1;

        userOne.win(diffOne);
        userTwo.lose(diffOne);
        userOne.lose(diffTwo);
        userTwo.win(diffTwo);

        users.add(userOne);
        users.add(userTwo);

        dbService.updateUsers(users);

        userOne = dbService.getUDS(loginOne, passwordOne);
        userTwo = dbService.getUDS(loginTwo, passwordTwo);

        Assert.assertEquals(userOne.getWinQuantity(), winQuantity);
        Assert.assertEquals(userTwo.getWinQuantity(), winQuantity);

        Assert.assertEquals(userOne.getLoseQuantity(), loseQuantity);
        Assert.assertEquals(userTwo.getLoseQuantity(), loseQuantity);

        Assert.assertEquals(userOne.getRating(), userOneRating);
        Assert.assertEquals(userTwo.getRating(), userTwoRating);

        dbService.deleteUser(userOne.getModel());
        dbService.deleteUser(userTwo.getModel());

    }
}