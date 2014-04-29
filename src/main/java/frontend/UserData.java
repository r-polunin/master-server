package frontend;

import database.UserDataSet;
import messageSystem.Address;

public interface UserData extends Runnable{
	public void updateUserId(String sessionId,UserDataSet user);
	public boolean partyEnd(int winId, int loseId);
    public Address getAddress();
}
