package messageSystem.gameMech;

import chat.GameChat;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.Msg;


public abstract class MsgToGameChat extends Msg {

	public MsgToGameChat(Address from, Address to){
		super(from,to);
	}

    @Override
    public void exec(Abonent abonent){
        if (abonent instanceof GameChat){
            exec((GameChat)abonent);
        }
    }
	public abstract void exec(GameChat gameChat);
}