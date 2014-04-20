package messageSystem;

import gameMechanic.GameMechanicImpl;


public abstract class MsgToGameMechanic extends Msg{

	public MsgToGameMechanic(Address from, Address to){
		super(from,to);
	}

	public void exec(GameMechanicImpl abonent){
		if (abonent instanceof GameMechanicImpl){
			exec((GameMechanicImpl)abonent);
		}
	}
	//public abstract void exec(GameMechanicImpl gameMechanic);
}