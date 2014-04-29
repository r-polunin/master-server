package messageSystem.datebase;

import database.UserDataSet;
import messageSystem.Address;
import frontend.UserData;
import messageSystem.datebase.MsgToUserData;


public class MsgUpdateUser extends MsgToUserData{
	final private String sessionId;
	final private UserDataSet uds;

	public MsgUpdateUser(Address from, Address to, String sessionId, UserDataSet uds){
		super(from,to);
		this.sessionId=sessionId;
		this.uds=uds;
	}

    @Override
	public void exec(UserData userData){
		userData.updateUserId(sessionId, uds);
	}
}