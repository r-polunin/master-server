package messageSystem.frontend;

import datebase.UserDataSet;
import messageSystem.Address;
import frontend.UserData;
import messageSystem.MsgToUserData;


public class MsgUpdateUser extends MsgToUserData{
	final private String sessionId;
	final private UserDataSet uds;

	public MsgUpdateUser(Address from, Address to, String sessionId, UserDataSet uds){
		super(from,to);
		this.sessionId=sessionId;
		this.uds=uds;
	}

	public void exec(UserData userData){
		userData.updateUserId(sessionId, uds);
	}
}