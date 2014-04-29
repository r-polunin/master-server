import database.UserDataSet;
import gameClasses.Field;
import gameClasses.Stroke;
import gameMechanic.GameMechanicImpl;
import gameMechanic.GameSession;
import messageSystem.Address;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import testingUtils.TestingUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dmitry on 29.04.14.
 */
public class GameMechanicTest {
    @Test
    public void testIsGameChatCreated(){
        MessageSystemImpl ms = new MessageSystemImpl();
        GameMechanicImpl gm = new GameMechanicImpl(ms);
        ms.addService(new Address(),"GameChat");
        Map<String,UserDataSet> users = new HashMap<String, UserDataSet>();
        UserDataSet user1 = TestingUtils.getUserDataSet(1,"dmitry",20,10,5);
        UserDataSet user2 = TestingUtils.getUserDataSet(2,"alexander",20,10,5);
        UserDataSet user3 = TestingUtils.getUserDataSet(3,"pavel",20,10,5);
        UserDataSet user4 = TestingUtils.getUserDataSet(4,"alexey",20,10,5);
        UserDataSet user5 = TestingUtils.getUserDataSet(5,"andrey",20,10,5);
        users.put("100",user1);
        users.put("101",user2);
        users.put("102",user3);
        users.put("103",user4);
        users.put("104",user5);
        HashMap<String,String> results = (HashMap<String, String>) gm.createGames(users);
        Assert.assertTrue(results.size()==4 &&
                (gm.getWantToPlay().size()==1)&&
                (!results.keySet().containsAll(gm.getWantToPlay().keySet()))
        );
    }

    @Test
    public void testIsUserRemoved(){
        MessageSystemImpl ms = new MessageSystemImpl();
        GameMechanicImpl gm = new GameMechanicImpl(ms);
        ms.addService(new Address(),"GameChat");
        Map<String,UserDataSet> users = new HashMap<String, UserDataSet>();
        UserDataSet user1 = TestingUtils.getUserDataSet(1,"dmitry",20,10,5);
        users.put("100",user1);
        gm.createGames(users);
        gm.removeUser("100");
        Assert.assertTrue(gm.getWantToPlay().values().size()==0);

    }

    @Test
    public void testIsStrokeCheckingRight(){
        MessageSystemImpl ms = new MessageSystemImpl();
        GameMechanicImpl gm1 = new GameMechanicImpl(ms);
        GameMechanicImpl gm2 = new GameMechanicImpl(ms);
        GameMechanicImpl gm3 = new GameMechanicImpl(ms);
        ms.addService(new Address(),"UserData");
        ms.addService(new Address(),"WebSocket");
        Stroke stroke1 = new Stroke(3,2,4,1,"somestatus");
        Map<Integer,Stroke> res1 = gm1.checkStroke(1,stroke1);
        Stroke stroke2 = new Stroke(3,1,4,0,"lose");
        GameSession gs2 = new GameSession(1,2);
        gm2.getUserIdToSession().put(1,gs2);
        Map<Integer,Stroke> res2 = gm2.checkStroke(1,stroke2);
        GameSession gs3 = new GameSession(1,2,10,0);
        gs3.getField(3,5).setType(Field.checker.nothing);
        gs3.getField(4,8).setType(Field.checker.white);
        gm3.getUserIdToSession().put(1,gs3);
        Map<Integer,Stroke> res3 = gm3.checkStroke(1,stroke1);
        Stroke res = res3.get(1);
        Assert.assertTrue((res1==null) &&
                (res2.size() == 2) &&
                (res.getFrom_X()==4 && res.getFrom_Y()==1 && res.getTo_X()==3 && res.getTo_Y()==2));

    }
}
