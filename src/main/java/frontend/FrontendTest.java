package frontend;

import datebase.UserDataSet;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dmitry on 19.04.14.
 */
public class FrontendTest {
    @Test
    public void testSendSomePage() throws IOException {
        PrintWriter writer = new PrintWriter("somewriter");
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(writer);
        UserDataSet userSession = mock(UserDataSet.class);
        when(userSession.getId()).thenReturn(123);
        when(userSession.getNick()).thenReturn("dmitry");
        when(userSession.getRating()).thenReturn(100);
        MessageSystem ms = new MessageSystemImpl();
        FrontendImpl frontendImpl = new FrontendImpl(ms);
        Assert.assertTrue(frontendImpl.sendPage("somePage.html", userSession, response));
    }

    @Test
    public void testCreateNewSession() {
        String sessionId = null;
        String strStartServerTime = null;
        UserDataSet userSession = new UserDataSet();
        MessageSystem ms = new MessageSystemImpl();
        FrontendImpl frontendImpl = new FrontendImpl(ms);
        frontendImpl.createNewSession(sessionId, strStartServerTime, userSession);
        Assert.assertTrue(UserDataImpl.containsSessionId(sessionId));
    }

    @Test
    public void testSendInvalidRequest() throws IOException {
        String target = "/invalidTarget";
        UserDataSet userSession = new UserDataSet();
        PrintWriter writer = new PrintWriter("somewriter");
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(writer);
        MessageSystem ms = new MessageSystemImpl();
        FrontendImpl frontendImpl = new FrontendImpl(ms);
        Assert.assertTrue(frontendImpl.sendPagesDependOnLocation(target, userSession, response));
    }
}
