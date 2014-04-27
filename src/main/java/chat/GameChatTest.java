package chat;

import datebase.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dmitry on 23.04.14.
 */
public class GameChatTest {
    @Test
    public void testSendMessageToChat(){
        MessageSystem ms = new MessageSystemImpl();
        GameChatImpl gameChat = new GameChatImpl(ms);
        String sessionId1 = "100";
        String sessionId2 = "200";
        String nick = "dmitry";
        UserDataSet userSession = mock(UserDataSet.class);
        when(userSession.getNick()).thenReturn(nick);
        UserDataImpl.putLogInUser(sessionId1,userSession);
        String text = "Brave New World";
        gameChat.createChat(sessionId1,sessionId2);
        GameChatImpl.sendMessage(sessionId1,text);
        Assert.assertEquals(gameChat.getChatMessageQueueBySessionId(sessionId1).get(0).text,text);
    }
}
