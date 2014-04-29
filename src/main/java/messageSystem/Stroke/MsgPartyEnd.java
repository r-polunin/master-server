package messageSystem.Stroke;

import messageSystem.Address;
import frontend.UserData;
import messageSystem.datebase.MsgToUserData;

public class MsgPartyEnd extends MsgToUserData{
	int winId, loseId;
	
	public MsgPartyEnd(Address from, Address to, int winId, int loseId){
		super(from,to);
		this.winId = winId;
		this.loseId = loseId;
	}
	
	public void exec(UserData userData){
		userData.partyEnd(winId, loseId);
	}
}
