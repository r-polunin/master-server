package testingUtils;

import database.UserDataSet;
import database.UserModel;

/**
 * Created by Ruslan on 18.04.2014.
 */
public class TestingUtils {
    public static UserDataSet getUserDataSet(int id,String nick,int rating,int wins, int loses) {
        UserModel model = new UserModel();
        model.setId(id);
        model.setNickname(nick);
        model.setRating(rating);
        model.setWinQuantity(wins);
        model.setLoseQuantity(loses);
        return new UserDataSet(model);
    }
}
