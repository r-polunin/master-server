package frontend;

import datebase.UserDataSet;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        Assert.assertTrue(userDataImpl.createGames());
    }

    @Test
    public void testIsRatingReconsidered() {
        MessageSystem ms = new MessageSystemImpl();
        UserDataImpl userDataImpl = new UserDataImpl(ms);
        int winId = 10;
        int loseId = 20;
        String winSessionId = "101";
        String loseSessionId = "202";
        int winRating = 435;
        int loseRating = 267;
        when(UserDataImpl.getSessionIdByUserId(winId)).thenReturn(winSessionId);
        when(UserDataImpl.getSessionIdByUserId(loseId)).thenReturn(loseSessionId);
        UserDataSet winUserSession = new UserDataSet(winId,"winner",winRating,41,4);
        UserDataSet loseUserSession = new UserDataSet(loseId,"loser",loseRating,13,25);
        when(UserDataImpl.getUserSessionBySessionId(winSessionId)).thenReturn(winUserSession);
        when(UserDataImpl.getUserSessionBySessionId(loseSessionId)).thenReturn(loseUserSession);
        Assert.assertTrue(userDataImpl.partyEnd(winId, loseId));
    }
}
