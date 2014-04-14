package frontend;

import datebase.UserDataSet;
import messagesystem.Abonent;

public interface UserData extends Abonent,Runnable{
	public void updateUserId(String sessionId,UserDataSet user);
	public void partyEnd(int winId, int loseId);
}
