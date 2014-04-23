package messageSystem;

import chat.GameChat;


public abstract class MsgToGameChat extends Msg{

	public MsgToGameChat(Address from, Address to){
		super(from,to);
	}

	public void exec(Msg abonent){
		if (abonent instanceof GameChat){
			exec((GameChat)abonent);
		}
	}
	public abstract void exec(GameChat gameChat);
}