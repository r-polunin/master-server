package frontend;

import database.UserDataSet;
import messageSystem.Abonent;
import messageSystem.Address;

public interface UserData extends Runnable, Abonent{
	public void updateUserId(String sessionId,UserDataSet user);
	public boolean partyEnd(int winId, int loseId);
}
