package messageSystem.gameMech;

import messageSystem.Abonent;
import messageSystem.Address;
import frontend.WebSocket;
import messageSystem.Msg;


public abstract class MsgToWebSocket extends Msg {

	public MsgToWebSocket(Address from, Address to){
		super(from,to);
	}

    @Override
	public void exec(Abonent abonent){
		if (abonent instanceof WebSocket){
			exec((WebSocket)abonent);
		}
	}
	public abstract void exec(WebSocket webSocket);
}