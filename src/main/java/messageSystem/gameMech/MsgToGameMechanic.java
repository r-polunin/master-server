package messageSystem.gameMech;

import gameMechanic.GameMechanic;
import messageSystem.Abonent;
import messageSystem.Address;
import messageSystem.Msg;


public abstract class MsgToGameMechanic extends Msg {

	public MsgToGameMechanic(Address from, Address to){
		super(from,to);
	}

    @Override
	public void exec(Abonent abonent){
		if (abonent instanceof GameMechanic){
			exec((GameMechanic)abonent);
		}
	}
	public abstract void exec(GameMechanic gameMechanic);
}