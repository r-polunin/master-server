import gameClasses.Snapshot;
import gameMechanic.GameSession;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dmitry on 29.04.14.
 */
public class SnapshotTest {
    @Test
    public void testIsSnapshotRight(){
        GameSession gameSession = new GameSession(1,2,4,1);
        Snapshot snap = new Snapshot(gameSession.getDesc(),'w',4,'b');
        String str = snap.toString();
        String
                snapString
                = "{\"status\":\"snapshot\",\"next\":\"b\",\"color\":\"w\"," +
                "\"field\":[[\"white\", \"nothing\", \"white\", \"nothing\"], " +
                "[\"nothing\", \"nothing\", \"nothing\", \"nothing\"], " +
                "[\"nothing\", \"nothing\", \"nothing\", \"nothing\"], " +
                "[\"nothing\", \"black\", \"nothing\", \"black\"]]," +
                "\"king\":[[\"false\", \"false\", \"false\", \"false\"], " +
                "[\"false\", \"false\", \"false\", \"false\"], " +
                "[\"false\", \"false\", \"false\", \"false\"], " +
                "[\"false\", \"false\", \"false\", \"false\"]]}";
        Assert.assertTrue(snapString.equals(snap.toString()));

    }
}
