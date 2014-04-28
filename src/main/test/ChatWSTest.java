import chat.ChatMessage;
import chat.ChatWSImpl;
import chat.GameChatImpl;
import datebase.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.websocket.api.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by dmitry on 23.04.14.
 */
public class ChatWSTest {
      @Test
      public void testSendMessageToChat(){
        MessageSystemImpl ms = new MessageSystemImpl();
        GameChatImpl gchat = new GameChatImpl(ms);
        String text = "hello";
        String startServerTime = "\""+UserDataImpl.getStartServerTime()+"\"";
        String senderSessionId = "100";
        gchat.createChat(senderSessionId,"200");
        ChatWSImpl chatWS = new ChatWSImpl();
         Session session = chatWS.getSession();
        String message = "{" +
                "\"sessionId\": "+senderSessionId+"," +
                "\"startServerTime\": " + startServerTime+","+
                "\"text\": \""+text+"\"}";
        UserDataSet user = new UserDataSet(10,"dmitry",1,1,1);
        chatWS.onWebSocketConnect(session);
        UserDataImpl.putLogInUser(senderSessionId,user);
        chatWS.onWebSocketText(message);
        List<ChatMessage> q = gchat.getChatMessageQueueBySessionId(senderSessionId);
        ChatMessage cm = q.get(0);
        Assert.assertEquals(cm.getMessage(),text);
    }

    @Test
    public void testAddNewChater(){
        MessageSystemImpl ms = new MessageSystemImpl();
        GameChatImpl gchat = new GameChatImpl(ms);
        String senderSessionId = "100";
        String startServerTime = "\""+UserDataImpl.getStartServerTime()+"\"";
        gchat.createChat(senderSessionId,"200");
        ChatWSImpl chatWS = new ChatWSImpl();
        String message = "{" +
                "\"sessionId\": "+senderSessionId+"," +
                "\"startServerTime\": " + startServerTime+"}";
        chatWS.onWebSocketText(message);
        Assert.assertNotNull(UserDataImpl.containsSessionIdInSITCWS(senderSessionId));
    }
}
