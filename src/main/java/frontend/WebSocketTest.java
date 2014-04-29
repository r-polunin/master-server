package frontend;

import database.UserDataSet;
import messageSystem.Address;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.common.WebSocketRemoteEndpoint;
import org.testng.Assert;
import org.testng.annotations.Test;
import testingUtils.TestingUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dmitry on 22.04.14.
 */
public class WebSocketTest {
    @Test
    public void testSendStrokeMsgToGM(){
        WebSocketImpl webSocket = new WebSocketImpl();
        MessageSystem ms = new MessageSystemImpl();
        webSocket.setMS(ms);
        String message="{\"sessionId\": 100," +
                "\"startServerTime\": " + "\""+UserDataImpl.getStartServerTime()+"\""+","+
                "\"from_x\": 1," +
                "\"from_y\": 1," +
                "\"to_x\": 2," +
                "\"to_y\": 2," +
                "\"status\": \"somestatus\"}";
        String serviceName = "GameMechanic";
        UserDataSet user = TestingUtils.getUserDataSet(10, "dmitry", 1, 1, 1);
        UserDataImpl.putLogInUser("100",user);
        ms.addService(new Address(),serviceName);
        webSocket.onWebSocketText(message);
        Assert.assertNotNull(ms.getMsgQueueByName(serviceName).peek());
    }

    @Test
    public void testUpdateUserColor() throws IOException {
        WebSocketImpl webSocket = new WebSocketImpl();
        Map<String, String> usersToColors = new HashMap<String, String>();
        String sesionId1 = "100";
        String sesionId2 = "200";
        String black = "{\"color\":\"black\"}";
        String white = "{\"color\":\"white\"}";
        UserDataSet session = mock(UserDataSet.class);
        WebSocketImpl socket = mock(WebSocketImpl.class);
        UserDataImpl.putSessionIdAndWS(sesionId1,socket);
        UserDataImpl.putSessionIdAndWS(sesionId2,socket);
        Session wsSession = mock (Session.class);
        WebSocketRemoteEndpoint rmt = mock (WebSocketRemoteEndpoint.class);
        when(socket.getSession()).thenReturn(wsSession);
        when(wsSession.getRemote()).thenReturn(rmt);
        doNothing().when(rmt).sendString(white);
        doNothing().when(rmt).sendString(black);
        UserDataImpl.putLogInUser(sesionId1,session);
        UserDataImpl.putLogInUser(sesionId2,session);
        usersToColors.put(sesionId1,black);
        usersToColors.put(sesionId2,white);
        Assert.assertTrue(webSocket.updateUsersColor(usersToColors));

    }
}
