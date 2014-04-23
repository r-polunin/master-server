package messageSystem;

import frontend.UserData;


public abstract class MsgToUserData extends Msg{

	public MsgToUserData(Address from, Address to){
		super(from,to);
	}

	public void exec(Address abonent){
		if (abonent instanceof UserData){
			exec((UserData)abonent);
		}
	}
	public abstract void exec(UserData frontend);
}