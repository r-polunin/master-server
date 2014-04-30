import database.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.Address;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import resource.Rating;
import testingUtils.TestingUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dmitry on 20.04.14.
 */
public class UserDataTest {
    @Test
    public void testGetOldSessionId(){
        int id = 123;
        String sessionId = "1234";
        UserDataSet userSession = mock(UserDataSet.class);
        when(userSession.getId()).thenReturn(id);
        MessageSystem ms = new MessageSystemImpl();
        UserDataImpl userDataImpl = new UserDataImpl(ms);
        UserDataImpl.putLogInUser(sessionId,userSession);
        Assert.assertEquals(userDataImpl.getOldUserSessionId(id),sessionId);
    }

    @Test
    public void testIsGameCreated() {
        MessageSystem ms = new MessageSystemImpl();
        UserDataImpl userDataImpl = new UserDataImpl(ms);
        String sessionId = "100";
        String serviceName = "gameMechanic";
        UserDataSet userSession = mock(UserDataSet.class);
        ms.addService(new Address(),serviceName);
        UserDataImpl.playerWantToPlay(sessionId,userSession);
        Assert.assertTrue(userDataImpl.createGames());
    }

    @Test
    public void testIsRatingReconsidered() {
        MessageSystem ms = new MessageSystemImpl();
        UserDataImpl userDataImpl = new UserDataImpl(ms);
        int winId = 10;
        int loseId = 20;
        String winSessionId1 = "101";
        String loseSessionId1 = "202";
        int winRating = 435;
        int loseRating = 267;
        Rating.setRatingFields(173,24,145,10);
        String serviceName = "DBService";
        ms.addService(new Address(),serviceName);
        String winSessionId2 = "100";
        String loseSessionId2 = "200";
        UserDataImpl.putLogInUser(winSessionId2, TestingUtils.getUserDataSet(winId, "winner", winRating, 41, 4));
        UserDataImpl.putLogInUser(loseSessionId2,TestingUtils.getUserDataSet(loseId, "loser", loseRating,13,25));
        boolean flag1 = !userDataImpl.partyEnd(winId, loseId);
        UserDataSet winUserSession = TestingUtils.getUserDataSet(11, "winner", winRating, 41, 4);
        UserDataSet loseUserSession = TestingUtils.getUserDataSet(12,"loser",loseRating,13,25);
        UserDataImpl.putSessionIdAndUserSession(winSessionId1,winUserSession);
        UserDataImpl.putSessionIdAndUserSession(loseSessionId1, loseUserSession);
        UserDataImpl.putLogInUser(winSessionId1, winUserSession);
        UserDataImpl.putLogInUser(loseSessionId1,loseUserSession);
        boolean flag2 = userDataImpl.partyEnd(11, 12);
        Assert.assertTrue(flag1 && flag2);
    }
}
