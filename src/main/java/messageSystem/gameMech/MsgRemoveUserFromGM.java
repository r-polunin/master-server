package messageSystem.gameMech;

import messageSystem.Address;
import gameMechanic.GameMechanic;

public class MsgRemoveUserFromGM extends MsgToGameMechanic{
	final private String sessionId;
	
	public MsgRemoveUserFromGM(Address from, Address to, String sessionId){
		super(from, to);
		this.sessionId=sessionId;
	}

    @Override
	public void exec(GameMechanic gameMechanic){
		gameMechanic.removeUser(sessionId);
	}
}
