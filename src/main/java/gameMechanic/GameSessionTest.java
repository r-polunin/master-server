package gameMechanic;

import gameClasses.Field;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dmitry on 24.04.14.
 */
public class GameSessionTest {
    /*@Test
    public void testIsDescRightInit(){
        Field[][] handMadeDesc = handInit4x4();
        GameSession gameSession = new GameSession(1,2,4,1);
        Field[][] autoDesc = gameSession.getDesc();
        Assert.assertTrue(equals(autoDesc,handMadeDesc));

    }*/
    @Test
    public void testCheckStroke(){

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
