package messageSystem.datebase;

import frontend.UserData;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.Msg;


public abstract class MsgToUserData extends Msg {

	public MsgToUserData(Address from, Address to){
		super(from,to);
	}

    @Override
	public void exec(Abonent abonent){
		if (abonent instanceof UserData){
			exec((UserData)abonent);
		}
	}
	public abstract void exec(UserData frontend);
}