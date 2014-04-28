import datebase.UserDataSet;
import gameClasses.Stroke;
import gameMechanic.GameMechanicImpl;
import messageSystem.Address;
import messageSystem.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        UserDataSet user1 = new UserDataSet(1,"dmitry",20,10,5);
        UserDataSet user2 = new UserDataSet(2,"alexander",20,10,5);
        UserDataSet user3 = new UserDataSet(3,"pavel",20,10,5);
        UserDataSet user4 = new UserDataSet(4,"alexey",20,10,5);
        UserDataSet user5 = new UserDataSet(5,"andrey",20,10,5);
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
        UserDataSet user1 = new UserDataSet(1,"dmitry",20,10,5);
        users.put("100",user1);
        gm.createGames(users);
        gm.removeUser("100");
        Assert.assertTrue(gm.getWantToPlay().values().size()==0);

    }

    @Test
    public void testIsStrokeCheckingRight(){
        /*MessageSystemImpl ms = new MessageSystemImpl();
        GameMechanicImpl gm = new GameMechanicImpl(ms);
        Stroke stroke = new Stroke();
        gm.checkStroke(1,stroke);*/

    }
}
