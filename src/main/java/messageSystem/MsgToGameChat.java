package messageSystem;

import base.Abonent;
import base.GameChat;


public abstract class MsgToGameChat extends Msg{

	public MsgToGameChat(Address from, Address to){
		super(from,to);
	}

	public void exec(Abonent abonent){
		if (abonent instanceof GameChat){
			exec((GameChat)abonent);
		}
	}
	public abstract void exec(GameChat gameChat);
}