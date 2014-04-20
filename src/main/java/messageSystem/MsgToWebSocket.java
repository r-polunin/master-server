package messageSystem;

import messageSystem.Address;
import frontend.WebSocket;


public abstract class MsgToWebSocket extends Msg{

	public MsgToWebSocket(Address from, Address to){
		super(from,to);
	}

	public void exec(Address abonent){
		if (abonent instanceof WebSocket){
			exec((WebSocket)abonent);
		}
	}
	public abstract void exec(WebSocket webSocket);
}