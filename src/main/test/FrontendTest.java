import database.UserDataSet;
import database.UserModel;
import frontend.FrontendImpl;
import frontend.UserDataImpl;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Request;
import org.testng.Assert;
import org.testng.annotations.Test;
import testingUtils.TestingUtils;
import utils.SHA2;
import utils.SysInfo;
import utils.TimeHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
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

    @Test
    public void testIsRequestsHandled() throws IOException {
        MessageSystem ms = new MessageSystemImpl();
        FrontendImpl frontend = new FrontendImpl(ms);
        Request baseRequest = mock(Request.class);
        HttpServletRequest request1 = mock(HttpServletRequest.class);
        HttpServletRequest request2 = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = new PrintWriter("somewriter");
        when(response.getWriter()).thenReturn(writer);
        Cookie co_1 = new Cookie("sessionId",null);
        Cookie co_2 = new Cookie("startServerTime",null);
        Cookie co_3 = new Cookie("sessionId","100");
        Cookie co_4 = new Cookie("startServerTime", UserDataImpl.getStartServerTime());
        Cookie[] co1 = new Cookie[2];
        co1[0] = co_1;
        co1[1] = co_2;
        Cookie[] co2 = new Cookie[2];
        co2[0] = co_3;
        co2[1] = co_4;
        when(request1.getCookies()).thenReturn(co1);
        when(request2.getCookies()).thenReturn(co2);
        frontend.handle("er",baseRequest,request1,response);
        frontend.handle("/",baseRequest,request1,response);
        UserDataSet userSession = TestingUtils.getUserDataSet(10,"dmitry",40,10,5);
        UserDataImpl.putSessionIdAndUserSession("100",userSession);
        when(request2.getMethod()).thenReturn("GET");
        SysInfo sy = new SysInfo();
        frontend.handle("/game",baseRequest,request2,response);
        frontend.handle("/img/",baseRequest,request2,response);
        frontend.handle("/admin",baseRequest,request2,response);
        frontend.handle("/rules",baseRequest,request2,response);
        when(request2.getMethod()).thenReturn("POST");
        frontend.handle("/wait",baseRequest,request2,response);
        frontend.handle("/js/",baseRequest,request2,response);
    }
}
