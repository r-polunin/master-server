package messageSystem.gameMech;

import messageSystem.Address;
import chat.GameChat;

public class MsgCreateChat extends MsgToGameChat{
	final private String sessionId1,sessionId2;
	
	public MsgCreateChat(Address from, Address to, String sessionId1, String sessionId2){
		super(from,to);
		this.sessionId1=sessionId1;
		this.sessionId2=sessionId2;
	}

    @Override
	public void exec(GameChat gameChat){
		gameChat.createChat(sessionId1, sessionId2);
	}
}
