package messageSystem.gameMech;

import java.util.Map;

import messageSystem.Address;
import frontend.WebSocket;


public class MsgUpdateColors extends MsgToWebSocket{
	final private Map<String,String> usersToColors;

	public MsgUpdateColors(Address from, Address to, Map<String, String> data){
		super(from,to);
		usersToColors=data;
	}

    @Override
	public void exec(WebSocket webSocket){
		webSocket.updateUsersColor(usersToColors);
	}
}