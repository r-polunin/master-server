import gameClasses.Field;
import gameMechanic.GameSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.VFS;

/**
 * Created by dmitry on 24.04.14.
 */
public class GameSessionTest {
    @Test
    public void testIsDescRightInit(){
        Field[][] handMadeDesc = handInit4x4();
        GameSession gameSession = new GameSession(1,2,4,1);
        Field[][] autoDesc = gameSession.getDesc();
        Assert.assertTrue(equals(autoDesc,handMadeDesc));

    }

    @Test
    public void testIsKingCanEat(){
        GameSession gameSession = new GameSession(1,2,6,0);
        gameSession.getField(5,5).setType(Field.checker.white);
        gameSession.getField(5,5).makeKing();
        gameSession.getField(1,1).setType(Field.checker.black);
        gameSession.getField(3,3).setType(Field.checker.black);
        Assert.assertTrue(gameSession.checkKingOtherEating(5,5,3,3));
    }

    @Test
    public void testIsPawnMoved(){
        GameSession gameSession = new GameSession(1,2,4,1);
        gameSession.move(0,0,1,1);
        Assert.assertTrue(
                (gameSession.getField(0,0).isEmpty()) &&
                        (!gameSession.getField(1,1).isEmpty()) &&
                        (gameSession.getField(1,1).getType() == Field.checker.white)
        );
    }

    @Test
    public void testIsEatingStrokeWorkingProperly(){
        GameSession gameSession = new GameSession(1,2,8,1);
        gameSession.getField(5,5).setType(Field.checker.white);
        gameSession.getField(6,6).setType(Field.checker.black);
        gameSession.getField(7,7).setType(Field.checker.nothing);
        gameSession.getField(2,6).setType(Field.checker.white);
        gameSession.getField(2,4).setType(Field.checker.white);
        boolean whiteStroke = !gameSession.makeEatingStroke(5,5,7,7);
        boolean blackStroke = gameSession.makeEatingStroke(1,7,3,5);
        Assert.assertTrue(
                (whiteStroke) &&
                        (blackStroke) &&
                        (gameSession.getField(5,5).isEmpty()) &&
                        (gameSession.getField(6,6).isEmpty()) &&
                        (gameSession.getField(7,7).isKing()) &&
                        (gameSession.getField(7,7).getType() == Field.checker.white) &&
                        (gameSession.getField(1,7).isEmpty()) &&
                        (gameSession.getField(2,6).isEmpty()) &&
                        (gameSession.getField(3,5).getType() == Field.checker.black)
        );

    }

    @Test
    public void testIsUsualStrokeWorkingProperly(){
        GameSession gameSession = new GameSession(1,2,8,1);
        gameSession.getField(4,2).setType(Field.checker.white);
        gameSession.getField(1,1).setType(Field.checker.black);
        gameSession.getField(2,0).setType(Field.checker.nothing);
        boolean blackStroke = gameSession.makeUsualStroke(1, 1, 2, 0);
        boolean whiteStroke = gameSession.makeUsualStroke(4, 2, 5, 3);
        Assert.assertTrue(
                (whiteStroke) &&
                        (gameSession.getField(4,2).isEmpty()) &&
                        (gameSession.getField(5,3).getType() == Field.checker.white) &&
                        (blackStroke) &&
                        (gameSession.getField(2,0).isKing()) &&
                        (gameSession.getField(1,1).isEmpty())
        );

    }

    @Test
    public void testIsKingCanEatRightNow(){
        GameSession gameSession = new GameSession(1,2,10,0);
        gameSession.getField(3,3).setType(Field.checker.white);
        gameSession.getField(3,3).makeKing();
        gameSession.getField(1,1).setType(Field.checker.black);
        gameSession.getField(5,1).setType(Field.checker.black);
        gameSession.getField(7,3).setType(Field.checker.black);
        gameSession.getField(5,5).setType(Field.checker.black);
        gameSession.getField(5,5).makeKing();
        gameSession.getField(1,5).setType(Field.checker.black);
        Assert.assertTrue((gameSession.kingCanEatLeftDown(3, 3)) &&
                (gameSession.kingCanEatLeftUp(3, 3)) &&
                (gameSession.kingCanEatRightDown(3, 3)) &&
                (gameSession.kingCanEatRightUp(3, 3)) &&
                (gameSession.kingCanEatLeftDown(5, 5)) &&
                (!gameSession.kingCanEatLeftUp(5, 5)) &&
                (!gameSession.kingCanEatRightDown(5, 5)) &&
                (!gameSession.kingCanEatRightUp(5, 5))
        );

    }

    @Test
    public void testIsLogsSaved(){
        GameSession gameSession = new GameSession(1,2,7,0);
        gameSession.saveLog(1);
        String path1 = "/log/AI/"+String.valueOf(gameSession.getGameSessionId())+".txt";
        String path2 = "/log/"+gameSession.getDirForLog()+"/"+String.valueOf(gameSession.getGameSessionId())+".txt";
        Assert.assertTrue((VFS.readFile(path1).equals("white")) &&
                (VFS.isExist(path2))
        );
    }

    private Field[][] handInit4x4() {
        Field[][] handMadeDesc = new Field[4][4];
        handMadeDesc[0][0] = new Field(Field.checker.white);
        handMadeDesc[0][1] = new Field(Field.checker.nothing);
        handMadeDesc[0][2] = new Field(Field.checker.white);
        handMadeDesc[0][3] = new Field(Field.checker.nothing);
        handMadeDesc[1][0] = new Field(Field.checker.nothing);
        handMadeDesc[1][1] = new Field(Field.checker.nothing);
        handMadeDesc[1][2] = new Field(Field.checker.nothing);
        handMadeDesc[1][3] = new Field(Field.checker.nothing);
        handMadeDesc[2][0] = new Field(Field.checker.nothing);
        handMadeDesc[2][1] = new Field(Field.checker.nothing);
        handMadeDesc[2][2] = new Field(Field.checker.nothing);
        handMadeDesc[2][3] = new Field(Field.checker.nothing);
        handMadeDesc[3][0] = new Field(Field.checker.nothing);
        handMadeDesc[3][1] = new Field(Field.checker.black);
        handMadeDesc[3][2] = new Field(Field.checker.nothing);
        handMadeDesc[3][3] = new Field(Field.checker.black);
        return handMadeDesc;
    }
    private boolean equals (Field[][] autoDesc, Field[][] handMadeDesc) {
        for (int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                if (!autoDesc[i][j].equals(handMadeDesc[i][j])) return false;
            }
        }
        return true;
    }

}
